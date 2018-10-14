package com.lifeache.gamecore;
import java.util.*;

public class Unit
{
    boolean isDeath;
	HashMap atrMap = new HashMap<String,Object>();

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
	
	public float dealDamage(Damage damage,Unit target){
		return target.takeDamge(damage);
	}
	
	public float takeDamge(Damage damage){
        int  amr = getAttribute("armor");
        int hp = getAttribute("hp");
        float r = amr * 6 / (float)(100 + amr * 6);
        float tv = (1 - r)  * damage.getDamageValue();
        hp = (int) (hp - tv);
        setAttribute("hp",hp);
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

	@Override
	public String toString()
	{
		
		return "[" + getAttribute("name").toString()+","+
		getAttribute("attack").toString()+"]";
	}
	
	
	
	public Unit(){
		
	}

}
