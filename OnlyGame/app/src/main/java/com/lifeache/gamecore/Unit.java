package com.lifeache.gamecore;

public class Unit
{
	String name;
	int attack;
	int hp;
	int maxHp;
	int defence;

	public Unit(String name, int attack, int hp, int defence)
	{
		this.name = name;
		this.attack = attack;
		this.hp = hp;
		this.maxHp = hp;
		this.defence = defence;
	}

	public void setMaxHp(int maxHp)
	{
		this.maxHp = maxHp;
	}

	public int getMaxHp()
	{
		return maxHp;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setAttack(int attack)
	{
		this.attack = attack;
	}

	public int getAttack()
	{
		return attack;
	}

	public void setHp(int hp)
	{
		this.hp = hp;
	}

	public int getHp()
	{
		return hp;
	}

	public void setDefence(int defence)
	{
		this.defence = defence;
	}

	public int getDefence()
	{
		return defence;
	}
	
	public int attack(Unit u){
		u.setHp(u.getHp() - attack);
		return attack;
	}
}
