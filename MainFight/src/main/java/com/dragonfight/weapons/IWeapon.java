package com.dragonfight.weapons;

import com.dragonfight.Arena.Arena;
import com.dragonfight.Character.ICharacter;

public interface IWeapon{

    int getId();

    String getName();

    int getDamage();
    void setOffset(int offsetPercentag);

    ICharacter getCharacter();
    void setCharacter(ICharacter character);

    default Arena getArena(){
        return this.getCharacter().getArena();
    }

    boolean Condition();
    String getCondtitionMsg();
}