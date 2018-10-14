package com.lifeache.gamecore.ui;

public class Text extends Showable
{
	String text;
	
	public Text(String text){
		this.text = text;
	}
	
	@Override
	public int getType()
	{
		// TODO: Implement this method
		return Showable.TYPE_TEXT;
	}

	@Override
	public String toString()
	{
		// TODO: Implement this method
		return this.text;
	}

	
}
