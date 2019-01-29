package com.lifeache.gamecore;
import com.lifeache.gamecore.ability.*;
import java.util.*;

public class Equipment
{
    Vector<Ability> abilitys = new Vector<Ability>();
    
    public void addAbility(Ability ab){
        this.abilitys.add(ab);
    }
    
    public int abilityCount(){
        return abilitys.size();
    }
    
    public Ability getAbility(int index){
        return abilitys.get(index);
    }
}
