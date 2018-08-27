package com.lifeache.og;
import android.text.*;
import android.text.style.*;
import com.lifeache.gamecore.*;

public class Game
{
	public  CharSequence getStartSence(){
		SpannableStringBuilder ssb = new SpannableStringBuilder();
		ssb.append("你要做什么？\n");
		Showable s0= new Showable("战斗",1);
		Showable s1 = new Showable("查看",2);
		ssb.append(s0);
		ssb.append("\n");
		ssb.append(s1);
		ssb.append("\n");
		return ssb;
	}
	
	public CharSequence getSence(int id){
		switch(id){
			case 1 : 
				return battle();
			case 3:
				return attack();
		}
		return getStartSence();
	}
	Unit gzj = new Unit("公子颉",25,125,0);
	Unit guai = new Unit("丛林漫步者",20,160,0);
	private CharSequence battle(){
		SpannableStringBuilder ssb = new SpannableStringBuilder();
		ssb.append(gzj.getName() + "(生命:" + gzj.getHp() +"/" + gzj.getMaxHp() + ")\n");
		ssb.append("=====================\n");
		Showable s = new Showable("攻击",3);
		ssb.append(s);
		ssb.append(guai.getName() + "  生命: " + guai.getHp() + "/" + guai.getMaxHp() + "\n\n");
		return ssb;
	}
	
	private CharSequence attack(){
		SpannableStringBuilder ssb = new SpannableStringBuilder();
		ssb.append(gzj.getName() + "攻击" + guai.getName() + "\n");
		ssb.append("造成了" + gzj.attack(guai) + "点伤害\n");
		if(guai.getHp() <= 0){
			ssb.append(guai.getName() + "死亡\n");
			ssb.append(gzj.getName() + "获得胜利\n");
		} else {
			ssb.append(guai.getName() + "攻击" + gzj.getName() + "\n");
			ssb.append("造成了" + guai.attack(gzj) + "点伤害\n");
			if(gzj.getHp() <= 0){
				ssb.append(gzj.getName() + "死亡\n");
				ssb.append(gzj.getName() + "失败\n");
			} 
		}
		SpannableStringBuilder ssb2 = new SpannableStringBuilder(battle());
		return ssb2.append(ssb);
	}
}
