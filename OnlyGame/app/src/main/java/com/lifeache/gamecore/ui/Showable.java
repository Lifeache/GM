package com.lifeache.gamecore.ui;

public abstract class Showable
{
	public static final int TYPE_TEXT = 1;
	public static final int TYPE_BUTTON = 2;
	public static final int TYPE_IMAGE = 4;
	public abstract int getType();

	@Override
	public abstract String toString();
	
}
