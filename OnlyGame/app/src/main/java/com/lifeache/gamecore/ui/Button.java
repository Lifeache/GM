package com.lifeache.gamecore.ui;

public class Button extends Showable
{
	int id;
	String str;
	public Button(String str,int id){
		this.str = str;
		this.id = id;
	}

	public int getId()
	{
		return id;
	}

	@Override
	public String toString()
	{
		return this.str;
	}


	@Override
	public int getType()
	{
		return Showable.TYPE_BUTTON;
	}
	
}
