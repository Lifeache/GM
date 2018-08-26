package com.lifeache.gamecore;
import com.lifeache.og.*;
import java.util.*;

public class Force
{
	/**
	*势力，每个单位应当属于一个势力。
	*/
	
	String forceName;
	Vector<Unit> group;
	
	public Force(){
		this("未命名势力");
	}
	
	public Force(String forceName){
		this.forceName = forceName;
	}

	public void setForceName(String forceName)
	{
		this.forceName = forceName;
	}

	public String getForceName()
	{
		return forceName;
	}
	
	public void addUnit(Unit unit){
		group.add(unit);
	}
	
	public void removeUnit(Unit unit){
		group.remove(unit);
	}
	
	}
