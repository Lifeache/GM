package com.lifeache.og;
import android.app.*;
import android.content.res.*;
import java.io.*;
import com.lifeache.gamecore.*;
import com.lifeache.gamecore.ui.Showable;
import com.lifeache.gamecore.ui.*;
import android.text.*;

public class DefaultDispatcher extends Dispatcher
{
	MainActivity activity;
	Game g = new Game();
	private static DefaultDispatcher dd = new DefaultDispatcher();
	private DefaultDispatcher(){
	}

	public void setMainActivity(MainActivity activity)
	{
		this.activity = activity;
	}

	public MainActivity getMainActivity()
	{
		return activity;
	}
	
	public Game getGame(){
		return g;
	}
	@Override
	public void append(String t)
	{
		activity.append(t);
	}

	@Override
	public void setText(CharSequence t)
	{
		activity.setText(t);
	}
	
	@Override
	public void setText(Scene sb)
	{
		SpannableStringBuilder ssb = new SpannableStringBuilder();
		for (int i = 0; i < sb.size();i++){
			com.lifeache.gamecore.ui.Showable s = sb.get(i);
			switch(s.getType()){
				case Showable.TYPE_TEXT :
					ssb.append(s.toString());
					break;
				case Showable.TYPE_BUTTON :
					Button b = (Button)s;
					Clickable c = new Clickable(b.toString(),b.getId());
					ssb.append(c);
					break;
			}
		}
		activity.setText(ssb);
	}
	
	
	public static DefaultDispatcher getInstance(){
		return dd;
	}
	
	public InputStream openStream(String fileName){
		AssetManager am = getMainActivity().getAssets();
		try
		{
			return am.open(fileName);
		}
		catch (IOException e)
		{}
		return null;
	}
	
	
}
