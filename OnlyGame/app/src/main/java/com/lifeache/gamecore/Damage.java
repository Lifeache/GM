package com.lifeache.gamecore;

public class Damage
{
	/**
	*伤害。可以产生生命值的变化。
	*/
	//伤害来源
	Unit sourceUnit;
	//伤害值
	int damage;
	
	int damageType;
	
	public final static int unknow_type = 0;
	public final static int physical_type = 1;
	public final static int magic_type = 2;

	public Damage(Unit sourceUnit,int damage,int type)
	{
		this.sourceUnit = sourceUnit;
		this.damage = damage;
		this.damageType = type;
	}

	public Unit getSourceUnit()
	{
		return sourceUnit;
	}
    
    public float getDamageValue(){
        return damage;
    }
}
