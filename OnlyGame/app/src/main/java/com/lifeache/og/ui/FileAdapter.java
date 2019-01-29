package com.lifeache.og.ui;
import android.widget.*;
import android.content.*;
import java.util.*;
import android.view.*;
import com.lifeache.og.*;

public class FileAdapter extends ArrayAdapter<String>
{
    int id;
    public FileAdapter(Context context,int id,List<String> list){
        super(context,id,list);
        this.id = id;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent)
    {
       View v = LayoutInflater.from(getContext()).inflate(id,null);
       TextView tv = (TextView)v.findViewById(R.id.filelistoptionTextView1);
       tv.setTag(position);
      tv.setText( getItem(position));
       tv.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View p1)
                {
                   /*
                    Vector<String> list = new Vector<String>();
                    list.add(p1.getTag() + "../");
                    list.add("进攻");
                    FileAdapter fd = new FileAdapter(getContext(),R.layout.file_list_option,list);
                    ListView lv = (ListView)parent;
                    lv.setAdapter(fd);
                    */
                    Integer p = (Integer)p1.getTag();
                    DefaultDispatcher.jf.show(p);
                    OMDesignerChief chief = OMDesignerChief.getInstance();
                    chief.setDesignableIndex(p);
                }

           
       });
     
        return v;
    }
    
    
}
