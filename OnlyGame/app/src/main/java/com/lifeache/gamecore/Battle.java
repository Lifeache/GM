package com.lifeache.gamecore;

import com.lifeache.og.*;
import com.lifeache.gamecore.ui.*;

public class Battle
{
    
    public static final int RES_INPROGRESS = 0;
    public static final int RES_WICTORY = 1;
    public static final int RES_LOSE = 2;
    
	Force force1;
	Force force2;
    Unit u1;
    Unit u2;
    int battleStatus = Battle.RES_INPROGRESS;
	Scene ssb;
    
	public Battle(Force force1,Force force2){
		this.force1 = force1;
		this.force2 = force2;
        this.u1 = force1.getFirstUnit();
        this.u2 = force2.getFirstUnit();
	}
    
    public int getBattleStatus(){
        return this.battleStatus;
    }
	
	public Scene begin(){
		
        ssb = new Scene();
        ssb.setSceneTransfer(new SceneTransfer(){

                @Override
                public Scene transferTo(int id)
                {
                    switch(id){
                        case 1 :
                          Scene s = attack();
                           return s;
                          case 2 :
                              return Dispatcher.getInstance().getGame().getStartScene();
                    }
                    return ssb;
                }

            
        });
        ssb.append(head());
		return ssb;
	}
    
    private ShowableBuilder head(){
        Unit gzj = force1.getFirstUnit();
		Unit guai = force2.getFirstUnit();
        ShowableBuilder ssb = new ShowableBuilder();
        ssb.append(gzj.getAttribute(A.name) + "(生命:" + gzj.getAttribute(A.life) + "/" + gzj.getAttribute(A.max_life) + ")\n");
        ssb.append("=====================\n");
        Button s = new Button("攻击",1);
        ssb.append(s);
        ssb.append(guai.getAttribute(A.name) + "  生命: " + guai.getAttribute(A.life) + "/" + guai.getAttribute(A.max_life) + "\n\n");
		return ssb;
    }
    
    public boolean isEnded(){
        return battleStatus == RES_WICTORY 
        || battleStatus == RES_LOSE;
    }
    
    public Scene attack(){
        if (isEnded()){
            return ssb;
        }   
        Scene newScene;
        ShowableBuilder sb = new ShowableBuilder();
        Attack atker = new Attack(u1,u2);
        sb.append(atker.attack());
        if (u2.isDeath()){
            battleStatus = RES_WICTORY;
            sb.appendln(u2.getAttribute(A.name) + "死亡。");
            sb.appendln("获得胜利！");
            newScene = begin();
            newScene.append(sb);
            newScene.appendln();
            newScene.append(new Button("返回",2));
            return newScene;
        }
        atker = new Attack(u2,u1);
        sb.append(atker.attack());
        if (u1.isDeath()){
            battleStatus = RES_LOSE;
            sb.appendln(u1.getAttribute(A.name) + "死亡。");
            sb.appendln("战斗失败！");
            newScene = begin();
            newScene.append(sb);
            newScene.append(new Button("返回",2));
            return newScene;
        }
        newScene = begin();
        newScene.append(sb);
        return newScene;
    }
	
}
