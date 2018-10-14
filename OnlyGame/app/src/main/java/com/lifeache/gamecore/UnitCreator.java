package com.lifeache.gamecore;
import org.json.*;
import android.util.*;

/**
*专门创建单位实体的类。
**/
public class UnitCreator
{
	public Unit createUnit(String jsonStr){
		Unit newUnit = new Unit();
		try
		{
			JSONObject j = new JSONObject(jsonStr);
			newUnit = createUnit(j);
		}
		catch (JSONException e)
		{}
		return newUnit;
	}
	
	public Unit createUnit(JSONObject json){
		Unit newUnit = new Unit();
		JSONArray ja = json.names();
		try
		{
			for (int i = 0; i < ja.length(); i++){
				String name = ja.get(i).toString();
				newUnit.addAttribute(name, json.get(name));
			}
		}
		catch (JSONException e)
		{}
		return newUnit;
	}
}
