package com.dragonfight.Character;

import java.util.ArrayList;
import java.util.List;

import com.dragonfight.Arena.Arena;
import com.dragonfight.Arena.ICell;
import com.dragonfight.Misc.MsLocation;
import com.dragonfight.weapons.IWeapon;

public interface ICharacter{
    
    int getId();

    String getName();
    void setName(String name);

    char getSymbol();
    void setSymbol(char Symbol);

    CharacterType getCharacterType();
    void setCharacterType(CharacterType type);

    ICell getCell();
    void setCell(ICell cell);

    default Arena getArena(){
        return this.getCell().getArena();
    }

    int getHp();
    void takeDamage(int takeDamage);
    boolean isAlive();

    IWeapon getWeapon(String weaponName);
    IWeapon getWeapon(int WeaponIndex);
    IWeapon getRandomWeapon();
    IWeapon addWeapon(IWeapon weapon);
    List<IWeapon> getAllWeapons();
    boolean weaponExists(String name);
    boolean weaponExists(IWeapon weapon);
    IWeapon removeWeapon(String name);
    IWeapon removeWeapon(IWeapon weapon);
    void attack(IWeapon weapon);

    ICharacter getCurrentEnemy();
    ICharacter setCurrentEnemy(ICharacter enemy);
    List<ICharacter> getAllEmemies();
    List<ICharacter> getAllAllies();
    void addEnemy(ICharacter character);
    void addAlly(ICharacter character);
    void removeEnemy(int characterId);    
    void removeAlly(int characterId);
    
    MsLocation getLocation();
    boolean move(MsLocation location);

    CharacterStatus gCharacterStatus();
    void setCharacterStatus(CharacterStatus status, int roundCount);

}