package com.lifeache.og.ui;
import org.json.*;
import com.lifeache.gamecore.*;
import android.graphics.*;

/**
* @brief OM项目的设计模块的负责类。当前采用单例模式。
**/
public class OMDesignerChief
{
    private static final OMDesignerChief chief = new OMDesignerChief();
    
    final String[] files = new String[]{
        "/storage/emulated/0/AppProjects/gm/OnlyGame/app/src/main/assets/equipments.json",
        "/storage/emulated/0/AppProjects/gm/OnlyGame/app/src/main/assets/monster.json",
        "/storage/emulated/0/AppProjects/gm/OnlyGame/app/src/main/assets/abilities.json"
    };
    /**
    * 当前操作的可设计对象的索引。
    **/
    int designableIndex;
    /**
    * 当前操作的选项的索引。
    **/
    int optionIndex;
    
    public static final int UnitType = 1;
    public static final int AbilityType = 2;
    public static final int ItemType = 0;
    /**
    * 当前操作的可设计对象类型。
    **/
    int currentType = ItemType;
  
    /**
    * 当前管理的可设计对象类型的数目。
    * 分别是：单位类型(UnitType),技能类型(AbilityType),物品类型(Item)。
    **/
    private static final int DESOBJTYPE_CNT = 3;
    
    
    /**
     * 所有可设计对象的数据文件的记录。当前使用JSONArray[]存储。
     **/
    JSONArray[] designableData = new JSONArray[DESOBJTYPE_CNT];
    
    /**
    * 文件路径
    **/
    String filePath;
    
    private OMDesignerChief(){
        
    }

    public void setCurrentType(int currentType)
    {
        this.currentType = currentType;
    }

    public int getCurrentType()
    {
        return currentType;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setDesignableIndex(int designableIndex)
    {
        this.designableIndex = designableIndex;
    }

    public int getDesignableIndex()
    {
        return designableIndex;
    }

    public void setOptionIndex(int optionIndex)
    {
        this.optionIndex = optionIndex;
    }

    public int getOptionIndex()
    {
        return optionIndex;
    }
    
    /**
    * @brief 获得唯一的OMDesignableChief实例。
    **/
    public static OMDesignerChief getInstance(){
        return chief;
    }
    
    /**
    * @brief 登记可设计对象的数据。以后可以通过本实例来查询。
    * @para type:将要登记的类型。
    * @para data:将要登记的数据。
    **/
    public void register(int type,JSONArray data){
        designableData[type] = data;
    }
    
    /**
    * @brief 得到已登记的某一数据。
    * @para type:将要得到的可设计对象类型。
    * @para index:将要得到的对象数据所在的索引位置。
    **/
    public JSONObject get(int type,int index){
        JSONObject jObj = null;
        try
        {
            jObj = designableData[type].getJSONObject(index);
        }
        catch (JSONException e)
        {}
        return jObj;
    }
    
    /**
    * @brief 获取某一可设计对象类型的全部数据
    * @para type:可设计对象类型。
    **/
    public JSONArray getDesignableData(int type){
        return designableData[type];
    }
    
    /**
     * @brief 更新已登记的某一数据。
     * @para type:将要更新的可设计对象类型。
     * @para index:将要更新的对象所在的索引位置。
     * @para value:将要更新的对象的数据。
     **/
    public void update(int type,int index,JSONObject value){
        try
        {
            designableData[type].put(index,value);
        }
        catch (JSONException e)
        {}
    }
    
    /**
     * @brief 得到已登记的某一数据。
     * @para type:将要得到的可设计对象类型。
     * @para index:将要得到的对象数据所在的索引位置。
     **/
    public JSONObject get(){
        JSONObject jObj = null;
        try
        {
            jObj = designableData[currentType].getJSONObject(designableIndex);
        }
        catch (JSONException e)
        {}
        return jObj;
    }

    /**
     * @brief 更新已登记的某一数据。
     * @para type:将要更新的可设计对象类型。
     * @para index:将要更新的对象所在的索引位置。
     * @para value:将要更新的对象的数据。
     **/
    public void update(JSONObject value){
        try
        {
            designableData[currentType].put(designableIndex,value);
        }
        catch (JSONException e)
        {e.printStackTrace();}
        }
        
        /**
        * @brief 添加一个可设计对象。
        * @para value: 将要添加的数据。
        **/
        public void add(JSONObject value){
            designableData[currentType].put(value);
        }
    
        /**
        * @brief 保存到文件
        **/
     public void save(){
         StringFileManager sfm = new StringFileManager();
         try
         {
             sfm.stringTo(filePath, designableData[currentType].toString(2));
         }
         catch (JSONException e)
         {}
     }
     
     /**
     * @brief 切换可设计对象类型的数据。
     * @para type: 将要切换到的类型。
     **/
     public void exchange(int type){
         currentType = type;
         filePath = files[type];
         designableIndex = 0;
         optionIndex = 0;
     }
    
}
