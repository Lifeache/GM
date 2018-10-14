package com.lifeache.gamecore;
import java.io.*;
import com.lifeache.og.*;

public class StringFileManager
{
	String playerHeroDatasheet = "gzj.json";
	String monsterDatasheet = "monster.json";

	public void setPlayerHeroDatasheet(String playerHeroDatasheet)
	{
		this.playerHeroDatasheet = playerHeroDatasheet;
	}

	public String getPlayerHeroDatasheet()
	{
		return stringFrom(playerHeroDatasheet);
	}

	public void setMonsterDatasheet(String monsterDatasheet)
	{
		this.monsterDatasheet = monsterDatasheet;
	}

	public String getMonsterDatasheet()
	{
		return stringFrom(monsterDatasheet);
	}
	
	public String stringFrom(String filePath){
		DefaultDispatcher dd = DefaultDispatcher.getInstance();
		StringBuilder sb = new StringBuilder();
		InputStream in = dd.openStream(filePath);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String str;
		try
		{
			while((str = br.readLine()) != null){
				sb.append(str + "\n");
			}
		}
		catch (IOException e)
		{}
		return sb.toString();
	}
}
