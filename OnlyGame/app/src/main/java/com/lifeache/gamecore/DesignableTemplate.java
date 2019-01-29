package com.lifeache.gamecore;
import org.json.*;

/**
* @brief 可设计对象类型的模板。也就是说，
* 该类给出一个可设计对象类型应该包含哪些具体的属性项目。
**/
public class DesignableTemplate
{
    public static final String itemTemplate = "item_template.json";
    public static final String unitTemplate = "unit_template.json";
    public static final String abilityTemplate = "ability_template.json";
    
    
    /**
    * @brief 从文件加载模板。
    * @para path:将要加载的文件的路径。当前为asset路径。
    **/
    public static JSONObject loadTemplate(String path){
        StringFileManager sfm = new StringFileManager();
        String ts = sfm.stringFrom(path);
        JSONObject jobj = new JSONObject();
        try
        {
            JSONArray jarr = new JSONArray(ts);
            for(int i = 0; i < jarr.length(); i ++){
                JSONArray ja = jarr.getJSONArray(i);
                String n = ja.getString(0);
                Object objv = str2Obj(ja.getString(1),ja.getString(2));
                jobj.put(n,objv);
            }
        }
        catch (JSONException e)
        {}
        return jobj;
    }
    
    /**
    * @brief 字符串转对象类型。
    * @para str:类型。
    * @para v:默认值。
    * @return 对应的Object对象。
    **/
    public static Object str2Obj(String str,String v){
        switch (str){
            case "string" :
                return v;
             case "integer" :
                 return Integer.valueOf(v);
              case "array" :
                  JSONArray ja = null;
                try
                {
                    ja = new JSONArray(v);
                }
                catch (JSONException e)
                {}
                return ja;
              case "double" : 
                  return Double.valueOf(v);
               case "boolean" :
                   return Boolean.valueOf(v);
               default : 
                   return v;
        }
    }
}
