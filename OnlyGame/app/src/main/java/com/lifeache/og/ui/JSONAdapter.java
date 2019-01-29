package com.lifeache.og.ui;
import android.widget.*;
import android.content.*;
import java.util.*;
import android.view.*;
import com.lifeache.og.*;
import android.text.*;
import org.json.*;

public class JSONAdapter extends ArrayAdapter<JSONOption>
{
    int id;
   public JSONAdapter(Context context,int id,List<JSONOption> list){
       super(context,id,list);
      this.id = id;
   }

   @Override
   public View getView(final int position, View convertView, ViewGroup parent)
   {
       View v = LayoutInflater.from(getContext()).inflate(id,null);
       TextView t = (TextView)v.findViewById(R.id.jsonlistoptionTextView1);
        final  EditText e = (EditText)v.findViewById(R.id.jsonlistoptionEditText1);
      
        JSONOption jo = getItem(position);
       t.setText(jo.getName());
       e.setText(jo.getValue());
       e.setTag(jo);
       e.addTextChangedListener(new TextWatcher(){

               @Override
               public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
               {
                   // TODO: Implement this method
               }

               @Override
               public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
               {
                   
               }

               @Override
               public void afterTextChanged(Editable p1)
               {
                   // TODO: Implement this method
               }

           
       });
       e.setOnFocusChangeListener(new View.OnFocusChangeListener(){

               @Override
               public void onFocusChange(View p1, boolean hasFocus)
               {
                   if(!hasFocus){
                       OMDesignerChief chief = OMDesignerChief.getInstance();
                       JSONObject jobj = chief.get();
                       JSONOption jop = (JSONOption)(p1.getTag());
                       Class type = jop.getType();
                       Object obj = type2Obj(type,e.getText().toString());
                       try
                       {
                           jobj.put(jop.getName(), obj);
                       }
                       catch (JSONException e)
                       {}
                       chief.update(jobj);
                       chief.save();
                       /*
                     JSONFragment jf =   DefaultDispatcher.getInstance().jf;
                     jf.setOption(position,e.getText().toString());
                     */
                   }
               }


           });
       return v;
   }
   
   private Object type2Obj(Class type,String v){
       Object obj = v;
     if(type == Integer.class){
         obj = Integer.valueOf(v);
     } else if (type == JSONArray.class){
         try
         {
             obj = new JSONArray(v);
         }
         catch (JSONException e)
         {}
     } else if (type == Double.class){
         obj = Double.valueOf(v);
     } else if (type == Boolean.class){
         obj = Boolean.valueOf(v);
     }
     return obj;
   }
}
