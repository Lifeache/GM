package com.lifeache.gamecore;

public class Attack extends Movement
{
	/**
	*攻击结算。
	*
	*/
	//发起攻击的单位
	Unit attacker;
	//目标单位
	Unit target;

	public Attack(Unit attacker, Unit target)
	{
		this.attacker = attacker;
		this.target = target;
	}
	
}
