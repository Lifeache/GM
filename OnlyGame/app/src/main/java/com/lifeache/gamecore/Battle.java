package com.lifeache.gamecore;

import com.lifeache.og.*;
import com.lifeache.gamecore.ui.*;

public class Battle
{
	Force force1;
	Force force2;
    Unit u1;
    Unit u2;
	Scene ssb = new Scene();
    
	public Battle(Force force1,Force force2){
		this.force1 = force1;
		this.force2 = force2;
        this.u1 = force1.getFirstUnit();
        this.u2 = force2.getFirstUnit();
	}
	
	public Scene begin(){
		Unit gzj = force1.getFirstUnit();
		Unit guai = force2.getFirstUnit();
        ssb = new Scene();
        ssb.setSceneTransfer(new SceneTransfer(){

                @Override
                public Scene transferTo(int id)
                {
                    switch(id){
                        case 1 :
                          Scene s = attack();
                          if (u1.isDeath() || u2.isDeath()){
                               s.removeSceneTransfer();
                           }   
                           return s;
                    }
                    return ssb;
                }

            
        });
		ssb.append(gzj.getAttribute("name") + "(生命:" + gzj.getAttribute("hp") + "/" + gzj.getAttribute("hp") + ")\n");
		ssb.append("=====================\n");
		Button s = new Button("攻击",1);
		ssb.append(s);
		ssb.append(guai.getAttribute("name") + "  生命: " + guai.getAttribute("hp") + "/" + guai.getAttribute("hp") + "\n\n");
		return ssb;
	}
    
    public Scene attack(){
        Scene newScene;
        ShowableBuilder sb = new ShowableBuilder();
        Attack atker = new Attack(u1,u2);
        sb.append(atker.attack());
        if (u2.isDeath()){
            sb.appendln(u2 + "死亡。");
            sb.appendln("获得胜利！");
            newScene = begin();
            newScene.append(sb);
            return newScene;
        }
        atker = new Attack(u2,u1);
        sb.append(atker.attack());
        if (u1.isDeath()){
            sb.appendln(u1 + "死亡。");
            sb.appendln("战斗失败！");
            newScene = begin();
            newScene.append(sb);
            return newScene;
        }
        newScene = begin();
        newScene.append(sb);
        return newScene;
    }
	
}
