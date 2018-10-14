package com.lifeache.og;
import android.text.*;
import android.text.style.*;
import com.lifeache.gamecore.*;
import java.io.*;
import org.json.*;
import com.lifeache.gamecore.ui.*;

public class Game
{
	Scene currentScene;

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
						case 4:
							return test();
					}
					return ssb;
				}

			
		});
		currentScene = ssb;
		
		ssb.append("你要做什么？\n");
		Button s0= new Button("战斗",1);
		Button s1 = new Button("查看",2);
		Button s2 = new Button("测试",4);
		ssb.append(s0);
		ssb.append("\n");
		ssb.append(s1);
		ssb.append("\n");
		ssb.append(s2);
		ssb.append("\n");
		return ssb;
	}
	
	private Scene test(){
		Scene ssb = new Scene();
		UnitCreator uc = new UnitCreator();
		DefaultDispatcher dd = DefaultDispatcher.getInstance();
		StringFileManager sfm = new StringFileManager();
		String s = sfm.getMonsterDatasheet();
		ssb.appendln(s);
		/*
		try
		{
			JSONArray j = new JSONArray(s);
			ssb.appendln(j.toString());
		}
		catch (JSONException e)
		{}
		*/
		
		/*
		InputStream in = dd.openStream("gzj.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;
		try
		{
			while((str = br.readLine()) != null){
				ssb.append(str + "\n");
			}
		}
		catch (IOException e)
		{}
		Unit u = uc.createUnit(ssb.toString());
		ssb.append(u.toString() + "\n");
		*/
		return ssb;
	}
	private Scene battle(){
		
		UnitPool pool = new UnitPool();
		StringFileManager sfm = new StringFileManager();
		pool.addUnits(sfm.getPlayerHeroDatasheet());
		pool.addUnits(sfm.getMonsterDatasheet());
		Unit gzj = pool.get(0);
		Unit guai = pool.get(1);
		Force f1 = new Force(gzj.getAttribute("name").toString());
		Force f2 = new Force(guai.getAttribute("name").toString());
		f1.addUnit(gzj);
		f2.addUnit(guai);
		Battle battle = new Battle(f1,f2);
		return battle.begin();
		}
}
