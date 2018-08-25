package com.dragonfight.Character.CharHelperClasses;

import java.util.ArrayList;

import com.dragonfight.weapons.IWeapon;

public class Inventory{
    public ArrayList<IWeapon> weapons;
    public IWeapon currentWeapon;

    public Inventory(){
        weapons = new ArrayList<>();
        currentWeapon = null;
    }
}