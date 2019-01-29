package com.lifeache.gamecore.ability;
import com.lifeache.gamecore.*;
import org.json.*;
import java.util.*;

public class AbilityLibrary
{
    private static AbilityLibrary abilityLib;
    Vector<JSONObject> lib = new Vector<JSONObject>();
    private AbilityLibrary(String path){
        StringFileManager sfm = new StringFileManager();
       String libJson = sfm.stringFrom(path);
        try
        {
            JSONArray ja = new JSONArray(libJson);
            for(int i = 0; i < ja.length(); i++){
                lib.add(ja.getJSONObject(i));
            }
        }
        catch (JSONException e)
        {}
    }
    
    public static AbilityLibrary getInstance(){ 
        if (abilityLib == null){
            abilityLib = new AbilityLibrary("abilities.json");
        }
        return abilityLib;
    }
    
    public JSONObject getJSONAbility(String id){
        try
        {
            for(JSONObject jo : lib){
               if (id.equals( jo.getString("id"))){
                   return jo;
               }
             }
        }
        catch (JSONException e){}
        return null;
    }
    
}
