package com.lifeache.gamecore;

import com.lifeache.og.*;

public class Damage
{
	/**
	*伤害。可以产生生命值的变化。
	*/
	//伤害来源
	Unit sourceUnit;
	//伤害值
	int damage;

	public Damage(Unit sourceUnit)
	{
		this.sourceUnit = sourceUnit;
	}

	public Unit getSourceUnit()
	{
		return sourceUnit;
	}
}
