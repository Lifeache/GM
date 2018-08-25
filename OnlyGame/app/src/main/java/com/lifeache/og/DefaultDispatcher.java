package com.lifeache.og;
import android.app.*;

public class DefaultDispatcher implements Dispatcher
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
	
	public static DefaultDispatcher getInstance(){
		return dd;
	}
	
}
