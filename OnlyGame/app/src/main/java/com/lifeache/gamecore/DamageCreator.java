package com.lifeache.gamecore;

public class DamageCreator
{
	public static Damage createDamage(Unit origin,int value,int type){
		return new Damage(origin,value,type);
	}
}
