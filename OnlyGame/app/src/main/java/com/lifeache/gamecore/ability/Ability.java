package com.lifeache.gamecore.ability;
import com.lifeache.gamecore.*;
import com.lifeache.gamecore.ui.*;

public interface Ability
{
    public void register(Unit unit);
    public void unregister(Unit unit);
    public void getId();
    public ShowableBuilder description();
}
