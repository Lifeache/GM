package com.lifeache.gamecore;
import java.util.*;
import com.lifeache.og.*;
import com.lifeache.gamecore.ability.*;

public class Unit
{
    boolean isDeath;
	HashMap atrMap = new HashMap<String,Object>();
    HashMap staMap = new HashMap<String,Object>();
    Vector<Ability> abilities = new Vector<Ability>();
    Vector<Equipment> equipments = new Vector<Equipment>();
    /**
    * 单位身上全部的装备槽。
    **/
    EquipmentSlot[] equipmentSlots;
    /**
    * 单位身当前的装备槽数目。
    **/
    int equipmentSlotCnt = 0;
    /**
    * 单位身上最大允许的装备槽数目。
    **/
    int equipmentSlotMaxCnt = 13;
    public void setEquipments(Vector<Equipment> equipments)
    {
        this.equipments = equipments;
    }

    public Vector<Equipment> getEquipments()
    {
        return equipments;
    }
    public boolean isDeath()
    {
        return isDeath;
    }
	
	public Object getAttribute(String name){
		return atrMap.get(name);
	}
	
	public void addAttribute(String name, Object value){
		atrMap.put(name,value);
	}
	
	public void setAttribute(String name, Object value){
		atrMap.put(name,value);
	}
	
	public double dealDamage(Damage damage,Unit target){
       
		return target.takeDamge(damage);
	}
	
	public double takeDamge(Damage damage){
        int  amr = getAttribute(A.armor);
        int hp = getAttribute(A.life);
        double r = amr * 6 / (float)(100 + amr * 6);
        double tv = (1 - r)  * damage.getDamageValue();
        hp = (int) (hp - tv);
        setAttribute(A.life,hp);
        if (hp < 0){
            deathReady();
        }
		return tv;
	}
	
	public void deathReady(){
		die();
	}
	
	public void die(){
		isDeath = true;
	}
    
    public void addAbility(Ability a){
        abilities.add(a);
        a.register(this);
    }
    
    public void removeAbility(int index ){
        abilities.get(index).unregister(this);
        abilities.remove(index);
    }

	@Override
	public String toString()
	{
		
		return "[" + getAttribute(A.name).toString()+","+
		getAttribute(A.attack).toString()+"]";
	}
	
	
	
	public Unit(){
		
	}
    
    public String getName(){
        return (String)getAttribute(A.name);
    }
    
    /**
    * @brief 单位配备装备。如果配备成功,
    * 该方法将使得武器的属性作用在单位。
    * @para equipment:将要配备的装备。
    * @para slotNum:将要配备的装备槽的编号
    * @return 装备成功则返回true,否则返回false。
    **/
    public boolean equip(Equipment equipment,int slotNum){
        if (equipmentSlotCnt == 0 || equipmentSlotCnt - 1 < slotNum){
            return false;
        }
        if (equipmentSlots[slotNum] != null){
            return false;
        }
        
        return true;
    }

}
