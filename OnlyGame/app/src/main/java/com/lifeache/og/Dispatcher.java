package com.lifeache.og;
import com.lifeache.gamecore.ui.*;
import java.io.*;

public abstract class Dispatcher
{
	private static Dispatcher dspcr;
	
	public abstract void append(String t);
	public abstract void setText(CharSequence t);
	public abstract void setText(Scene sb);
	public abstract InputStream openStream(String fileName);
    public abstract Game getGame();
	
	public static Dispatcher getInstance(){
		return dspcr;
	}
	
	public static void setDefaultDispatcher(Dispatcher dspcr){
		Dispatcher.dspcr = dspcr;
	}
}
