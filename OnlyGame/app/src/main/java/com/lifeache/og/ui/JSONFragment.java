package com.lifeache.og.ui;
import android.app.*;
import android.os.*;
import android.view.*;
import com.lifeache.og.*;
import android.widget.*;
import java.util.*;
import android.support.v4.widget.*;
import android.text.*;
import android.support.design.widget.*;
import com.lifeache.gamecore.*;
import org.json.*;
import android.util.*;
import java.io.*;
import android.view.View.*;

public class JSONFragment extends Fragment
{

    JSONArray data;
    Button addButton;
    Button itemButton;
    Button unitButton;
    Button abilityButton;
    ListView optionsList ;
    ListView designablesList;
    FileAdapter fileAdapter;
    JSONObject[] temps;
    int typeIndex;
    /**
    * 用于显示的属性的名称。
    **/
    String dispAtrriName = "名称";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.json_frag,container,false);
        optionsList  = (ListView)v.findViewById(R.id.jsonfragListView1);
        designablesList = (ListView)v.findViewById(R.id.jsonfragListView2);
        addButton = (Button)v.findViewById(R.id.jsonfragButton4);
        itemButton = (Button)v.findViewById(R.id.jsonfragButton1);
        unitButton = (Button)v.findViewById(R.id.jsonfragButton2);
        abilityButton = (Button)v.findViewById(R.id.jsonfragButton3);
        DefaultDispatcher.jf = this;
        init();
        show(0);
        showDirectory();
        DesignableTypeExchangeListener dtel = new DesignableTypeExchangeListener();
        itemButton.setOnClickListener(dtel);
        unitButton.setOnClickListener(dtel);
        abilityButton.setOnClickListener(dtel);
        
        /*
        Vector<String> list2 = getList2();
        fileAdapter = new FileAdapter(getContext(),R.layout.file_list_option,list2);
        designablesList.setAdapter(fileAdapter);
        */
        DrawerLayout d = (DrawerLayout)v.findViewById(R.id.jsonfragDrawerLayout1);
        //d.openDrawer(Gravity.LEFT);
        d.setDrawerListener(new DrawerLayout.DrawerListener(){

                @Override
                public void onDrawerSlide(View p1, float p2)
                {
                    // TODO: Implement this method
                }

                @Override
                public void onDrawerOpened(View p1)
                {
                    p1.setClickable(true);
                }

                @Override
                public void onDrawerClosed(View p1)
                {
                    // TODO: Implement this method
                }

                @Override
                public void onDrawerStateChanged(int p1)
                {
                    // TODO: Implement this method
                }

            
        });
        
        addButton.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View p1)
                {
                    try
                    {
                        fileAdapter.add(temps[typeIndex].getString(dispAtrriName));
                        OMDesignerChief chief = OMDesignerChief.getInstance();
                        chief.add(new JSONObject(temps[typeIndex].toString()));
                    }
                    catch (JSONException e)
                    {}
                }

            
        });
        return v;
    }
    
    /**
    * @brief 一些初始操作。加载并注册了数据。
    **/
    private void init(){
        StringFileManager sfm = new StringFileManager();
        String s1 = sfm.stringFromExtern("/storage/emulated/0/AppProjects/gm/OnlyGame/app/src/main/assets/equipments.json");
        String s2 = sfm.stringFromExtern("/storage/emulated/0/AppProjects/gm/OnlyGame/app/src/main/assets/monster.json");
        String s3 = sfm.stringFromExtern("/storage/emulated/0/AppProjects/gm/OnlyGame/app/src/main/assets/abilities.json");
        JSONArray d2 = null;
        JSONArray d3 = null;
        
        try
        {
            data = new JSONArray(s1);
            d2 = new JSONArray(s2);
            d3 = new JSONArray(s3);
        }
        catch (JSONException i)
        {}
        OMDesignerChief chief = OMDesignerChief.getInstance();
        chief.register(OMDesignerChief.ItemType,data);
        chief.register(OMDesignerChief.UnitType,d2);
        chief.register(OMDesignerChief.AbilityType,d3);
        temps = new JSONObject[3];
        temps[0] = DesignableTemplate.loadTemplate(DesignableTemplate.itemTemplate);
        temps[1] = DesignableTemplate.loadTemplate(DesignableTemplate.unitTemplate);
        temps[2] = DesignableTemplate.loadTemplate(DesignableTemplate.abilityTemplate);
    }
    
    public Vector<JSONOption> getList(int index){
        Vector<JSONOption> list = new Vector<JSONOption>();
        try
        {
            JSONObject jo = data.getJSONObject(index);
             JSONArray names =  jo.names();
            JSONArray jsa =  jo.toJSONArray(names);
            for(int i = 0; i < jsa.length();i++){
                Object obj = jsa.get(i);
                JSONOption jop = new JSONOption();
                jop.setName(names.get(i).toString());
                jop.setType(obj.getClass());
              //  jop.setValue(obj.getClass().getSimpleName()+" : "+obj.toString());
                jop.setValue(obj.toString());
                list.add(jop);
            }
        }
        catch (JSONException i)
        {}
        return list;
    }
    
    public Vector<String> getList2(){
        Vector<String> list = new Vector<String>();
        try
        {
            for(int i = 0; i < data.length();i++){
                JSONObject jo = data.getJSONObject(i);
                list.add(jo.getString(dispAtrriName));
            }
        }
        catch (JSONException i)
        {}

        return list;
    }
   
    public void show(final int index){
        Vector<JSONOption> list = getList(index);
        JSONAdapter ja = new JSONAdapter(getContext(),R.layout.json_list_option,list);
        optionsList.setAdapter(ja);
        
    }
    
    /**
    * @brief 显示当前可设计对象类型数据的索引目录。
    **/
    public void showDirectory(){
        Vector<String> list = getList2();
        fileAdapter = new FileAdapter(getContext(),R.layout.file_list_option,list);
        designablesList.setAdapter(fileAdapter);
    }
    
    /**
    * @brief 监听可设计对象类型的切换。当前采用按钮来切换。
    * 该类实现OnClickListener类。
    **/
    class DesignableTypeExchangeListener implements OnClickListener
    {

        @Override
        public void onClick(View p1)
        {
            
            OMDesignerChief chief = OMDesignerChief.getInstance();
            int type = 0;
            if (p1 == itemButton){
                type = OMDesignerChief.ItemType;
                typeIndex = 0;
            } else if(p1 == unitButton){
                type = OMDesignerChief.UnitType;
                typeIndex = 1;
            } else if (p1 == abilityButton){
                type = OMDesignerChief.AbilityType;
                typeIndex = 2;
            }
            chief.exchange(type);
            data = chief.getDesignableData(type);
            showDirectory();
            show(0);
        }

        
    }
}
