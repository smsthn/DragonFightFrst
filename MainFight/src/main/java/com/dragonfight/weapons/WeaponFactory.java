package com.dragonfight.weapons;

import java.util.ArrayList;
import java.util.List;

import com.dragonfight.Character.ICharacter;
import com.dragonfight.weapons.types.CloseRangeWeapon;
import com.dragonfight.weapons.types.LongRangWeapon;
import com.dragonfight.weapons.types.WeaponType;

public abstract class WeaponFactory {
    private static List<IWeapon> allWeapons = new ArrayList<>();

    public static IWeapon createAndAttachWeapon(ICharacter owner, WeaponType type){
        IWeapon weapon;
        switch (type) {
                
            case Sword:
                weapon = new CloseRangeWeapon(owner, "Sword", 30, 50, 0);
                break;
            case Arrow:
                weapon= new LongRangWeapon(owner, "Arrow", 10, 50, 90);
                break;
            case Fire:
                weapon= new LongRangWeapon(owner, "Fire", 20, 50, 90);
                break;
            default:
                return null;                
        }
        if(owner.weaponExists(weapon.getName()))return null;

        allWeapons.add(weapon);
        owner.addWeapon(weapon);
        return weapon;
    }

    public static IWeapon removeAndDetachWeapon(ICharacter owner, String weaponName){
        if(!owner.weaponExists(weaponName))return null;
        IWeapon wpn = owner.removeWeapon(weaponName);
        allWeapons.remove(wpn);
        return wpn;
    }
    public static IWeapon removeAndDetachWeapon(IWeapon weapon){
        IWeapon wpn = weapon.getCharacter().removeWeapon(weapon);
        allWeapons.remove(wpn);
        return wpn;
    }

}