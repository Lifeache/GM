package com.lifeache.og;
import android.text.*;

public class Clickable extends SpannableString
{
	int id;
	public Clickable(String t,int id){
		super(t);
		setSpan(new TextClicker(id),0,t.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
		this.id = id;
	}

	public void setId(int id)
	{
		this.id = id;
		
	}

	public int getId()
	{
		return id;
	}
}
