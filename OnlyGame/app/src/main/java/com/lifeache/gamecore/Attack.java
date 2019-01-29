package com.lifeache.gamecore;
import com.lifeache.gamecore.ui.*;
import java.util.*;
import com.lifeache.og.*;

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
		int sAtk = attacker.getAttribute(A.attack);
        String sName = (String)attacker.getAttribute(A.name);
        String dName = (String)target.getAttribute(A.name);
      
        sb.append(sName + "发起了攻击,");
        double dEva = target.getAttribute(A.evade);
        if (! Chance.isHappened(dEva)){
            double f = sAtk + ( Math.random() * 10);
            double c = attacker.getAttribute(A.critical_hit_chance);
            if (Chance.isHappened(c)){
                double d = attacker.getAttribute(A.critical_hit_damage);
                f +=  (f * d / 100);
                sb.append("伤害【暴击】！");
            }
		    Damage damage = DamageCreator.createDamage(attacker,f,Damage.physical_type);
            
		    double r = attacker.dealDamage(damage,target);
            sb.appendln("对" + dName + "造成了" + String.format("%.2f", r) + "点伤害。");
        } else{
            sb.append("却被" + dName + "闪避了。");
        }
        return sb;
	}
	
}
