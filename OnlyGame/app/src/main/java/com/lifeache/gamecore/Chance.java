package com.lifeache.gamecore;

public class Chance
{
    public  static boolean isHappened(double chance){
        return (Math.random() * 100) <= chance;
    }
}
