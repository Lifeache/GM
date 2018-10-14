package com.lifeache.gamecore;
import org.json.*;
import java.util.*;

public class UnitPool
{
	Vector<JSONObject> jsonUnits = new Vector<JSONObject>();
	public void addUnits(String json){
		try
		{
			JSONArray j = new JSONArray(json);
			for(int i = 0; i < j.length(); i++){
				JSONObject jo = j.getJSONObject(i);
				jsonUnits.add(jo);
			}
		}
		catch (JSONException e)
		{}
	}
	
	public Unit get(int index){
		UnitCreator uc = new UnitCreator();
		return uc.createUnit(jsonUnits.get(index));
	}
}
