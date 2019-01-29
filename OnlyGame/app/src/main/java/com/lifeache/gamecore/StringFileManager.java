package com.lifeache.gamecore;
import java.io.*;
import com.lifeache.og.*;
import android.util.*;
import android.os.*;

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
    
    public String stringFromExtern(String filePath){
        StringBuilder sb = new StringBuilder();
        try{
        InputStream in = new FileInputStream(filePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String str;
        
            while((str = br.readLine()) != null){
                sb.append(str + "\n");
            }
        }
        catch (IOException e)
        {}
        return sb.toString();
	}
    
    public void stringTo(String filePath,String text){
        File f = new File(filePath);
        Log.d("arr",Environment.getExternalStorageDirectory().getAbsolutePath());
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(f);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(text);
            bw.close();
            /*
            StringBuilder sb = new StringBuilder();
            InputStream in = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
                while((str = br.readLine()) != null){
                    sb.append(str + "\n");
                }
            Log.d("arr","<>" + sb.toString());
            */
        }
        catch (IOException e)
        {e.printStackTrace();}
    }
}
