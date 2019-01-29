package com.lifeache.og;

import com.lifeache.gamecore.*;
import java.io.*;
import org.json.*;
import com.lifeache.gamecore.ui.*;
import com.lifeache.gamecore.ability.*;
import com.lifeache.*;
import android.nfc.tech.*;
import java.util.*;

public class Game
{
	Scene currentScene;
    
    JSONArray equips;

	public void setCurrentScene(Scene currentScene)
	{
		this.currentScene = currentScene;
	}

	public Scene getCurrentScene()
	{
		return currentScene;
	}
	public  Scene getStartScene(){
		final Scene ssb = new Scene();
		ssb.setSceneTransfer(new SceneTransfer(){
			
				@Override
				public Scene transferTo(int id)
				{
					switch(id){
						case 1 : 
							return battle();
                        case 2:
                            return equip();
						case 4:
							return test();
                         case 5:
                             gss[0].create();
                             return gss[0];
					}
					return ssb;
				}

			
		});
		currentScene = ssb;
		
		ssb.append("你要做什么？\n");
		Button s0= new Button("战斗",1);
		Button s1 = new Button("查看",2);
		Button s2 = new Button("测试",4);
        Button s3 = new Button("小河村",5);
		ssb.append(s0);
		ssb.append("\n");
		ssb.append(s1);
		ssb.append("\n");
		ssb.append(s2);
		ssb.append("\n");
        ssb.append(s3);
		ssb.append("\n");
		return ssb;
	}
	
	private Scene test(){
		Scene ssb = new Scene();
        String p = "/storage/emulated/0/aMySpace";
		NameFactory nameFactory = new NameFactory(p);
        ssb.appendln("你遇到了：");
        for (int i = 0; i < 7*2; i++) {
            ssb.appendln("    " +nameFactory.getRandomName());
		}
        ssb.append(new Button("刷新",0));
        ssb.appendln();
        ssb.append(new Button("返回",1));
        ssb.setSceneTransfer(new SceneTransfer(){

                @Override
                public Scene transferTo(int id)
                {
                    if (id == 0){
                        return test();
                    } else {
                        return getCurrentScene();
                    }
                }

            
        });
		return ssb;
	}
	private Scene battle(){
		
		UnitPool pool = new UnitPool();
		StringFileManager sfm = new StringFileManager();
		pool.addUnits(sfm.getPlayerHeroDatasheet());
		pool.addUnits(sfm.getMonsterDatasheet());
		Unit gzj = pool.get(0);
		Unit guai = pool.get(1);
        Enhance enhance = new Enhance(A.attack,100);
        gzj.addAbility(enhance);
		Force f1 = new Force(gzj.getAttribute(A.name).toString());
		Force f2 = new Force(guai.getAttribute(A.name).toString());
		f1.addUnit(gzj);
		f2.addUnit(guai);
		Battle battle = new Battle(f1,f2);
		return battle.begin();
		}
        
        private Scene equip(){
            Scene scene = new Scene();
            scene.appendln("这里有装备：");
            for(int i = 0;i < equips.length();i ++){
                try
                {
                  JSONObject jo =  equips.getJSONObject(i);
                 String name = jo.getString("名称");
                 Button b = new Button(name,0);
                 scene.appendln();
                 scene.append(b);
                 scene.appendln();
                 
                }
                catch (JSONException e)
                {}
            }
            return scene;
        }
        
        GameScene[] gss;
        
        private GameScene getGameSceneById(int id){
            if (id == 0){
                return null;
            }
            for(int i = 0; i< gss.length; i++){
                if (gss[i].getId() == id){
                    return gss[i];
                }
            }
            return null;
        }
        
        private void mapInit(){
            String p = "/storage/emulated/0/aMySpace";
            NameFactory nf = new NameFactory(p);
            StringFileManager sfm = new StringFileManager();
            String s1 = sfm.stringFromExtern("/storage/emulated/0/AppProjects/gm/OnlyGame/app/src/main/assets/map.json");
            try
            {
                JSONArray ja = new JSONArray(s1);
                gss = new GameScene[ja.length()];
                for(int i = 0; i < ja.length(); i++){
                    JSONObject jo = ja.getJSONObject(i);
                    String n = jo.getString("名称");
                    int id =jo.getInt("ID");
                    String ms = jo.getString("描述");
                    GameScene gs = new GameScene();
                    gs.setName(n);
                    gs.setId(id);
                    gs.setText(ms);
                    gss[i] = gs;
                    JSONArray mons = jo.getJSONArray("野生单位");
                   if (mons.length() > 0){
                    String fs = mons.getString(0);
                    if (fs.startsWith("@RA_UNIT")){
                        int c = Integer.valueOf( mons.getString(1));
                        Vector<String> mms = new Vector<String>();
                        for(int j = 0; j < c; j++){
                            mms.add(nf.getRandomName());
                        }
                        gs.setMonsters(mms);
                        } else {
                            Vector<String> mms = new Vector<String>();
                            for(int j = 0; j < mons.length(); j++){
                                mms.add(mons.getString(j));
                            }
                            gs.setMonsters(mms);
                        }
                    }
                    JSONArray npcs = jo.getJSONArray("NPC单位");
                    Vector<String> mms = new Vector<String>();
                    for(int j = 0; j < npcs.length(); j++){
                        mms.add(npcs.getString(j));
                    }
                    gs.setNpcs(mms);
                }
                for(int i = 0; i < ja.length(); i++){
                    JSONObject jo = ja.getJSONObject(i);
                    JSONArray dirs = jo.getJSONArray("方向");
                    GameScene[] dirs0 = new GameScene[4];
                    for (int j = 0; j < 4; j++){
                        GameScene gs1 =getGameSceneById( dirs.getInt(j));
                        if (gs1 != null){
                            dirs0[j] = gs1;
                        }
                    }
                    gss[i].setDirsScene(dirs0);
                 }
            }
            catch (JSONException e)
            {}
        }
        
        public void init(){
            StringFileManager sfm = new StringFileManager();
            String s1 = sfm.stringFromExtern("/storage/emulated/0/AppProjects/gm/OnlyGame/app/src/main/assets/equipments.json");
            try
            {
                equips = new JSONArray(s1);
            }
            catch (JSONException e)
            {}
            try{
            
            mapInit();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
}
