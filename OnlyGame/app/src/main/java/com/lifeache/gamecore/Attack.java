package com.lifeache.gamecore;
import com.lifeache.gamecore.ui.*;

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
	
	public ShowableBuilder attack(){
        ShowableBuilder sb = new ShowableBuilder();
		int sAtk = attacker.getAttribute("attack");
        String sName = (String)attacker.getAttribute("name");
        String dName = (String)target.getAttribute("name");
        
		Damage damage = DamageCreator.createDamage(attacker,sAtk,Damage.physical_type);
		float r = attacker.dealDamage(damage,target);
        sb.appendln(sName + "发起攻击，对" + dName
        + "造成了" + r + "点伤害。");
        return sb;
	}
	
}
