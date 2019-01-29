package com.lifeache.og;
import java.util.*;

public class InstanceManager
{
    HashMap<String,Object> map = new HashMap<String,Object>();
    private static final InstanceManager im = new InstanceManager();
    
    private InstanceManager(){
        
    }
    
    public static InstanceManager getInstance(){
        return im;
    }
    
    public void put(String tag,Object obj){
        map.put(tag,obj);
    }
    
    public Object get(String key){
        return map.get(key);
    }
}
