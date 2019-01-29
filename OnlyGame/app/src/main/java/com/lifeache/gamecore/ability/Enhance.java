package com.lifeache.gamecore.ability;
import com.lifeache.gamecore.*;
import com.lifeache.gamecore.ui.*;
import com.lifeache.og.*;

public class Enhance implements Ability
{
    Object v;
    String type;
    public Enhance(String type,Object value){
        this.type = type;
        this.v = value;
        
    }
    
   @Override
    public void register(Unit unit)
    {
        switch(type){
            case A.attack :
            case A.armor :
            case A.life :
            case A.mana :
                unit.setAttribute(type,(int)(unit.getAttribute(type)) + (int)v);
                break;
             case A.critical_hit_chance :
             case A.critical_hit_damage :
             case A.evade :
                unit.setAttribute(type,(double)(unit.getAttribute(type)) + (double)v);
                break;

        }
    }

    @Override
    public void unregister(Unit unit)
    {
        switch(type){
            case A.attack :
            case A.armor :
            case A.life :
            case A.mana :
                unit.setAttribute(type,(int)(unit.getAttribute(type)) - (int)v);
                break;
            case A.critical_hit_chance :
            case A.critical_hit_damage :
            case A.evade :
                unit.setAttribute(type,(double)(unit.getAttribute(type)) - (double)v);
                break;

        }
    
    }

    @Override
    public void getId()
    {
        
    }
    
    @Override
    public ShowableBuilder description()
    {
        
        return null;
    }
    
}
