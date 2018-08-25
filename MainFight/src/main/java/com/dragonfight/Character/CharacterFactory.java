package com.dragonfight.Character;

import java.util.ArrayList;
import java.util.List;

import com.dragonfight.Character.CharHelperClasses.CharacterData;
import com.dragonfight.Character.CharHelperClasses.Inventory;
import com.dragonfight.weapons.IWeapon;
import com.dragonfight.weapons.WeaponFactory;
import com.dragonfight.weapons.types.WeaponType;

public class CharacterFactory{
    private static final List<ICharacter> allNPCs = new ArrayList<>();
    private static ICharacter mPlayer = null;

    public static ICharacter createPlayer(String name, int hp){
        if(mPlayer != null) return mPlayer;
        CharacterData data = new CharacterData();
        data.name = name;
        data.hp = hp;
        data.symbol = 'P';
        data.type = CharacterType.Player;
        data.status = CharacterStatus.Free;
        ICharacter player = new Character(data);
        WeaponFactory.createAndAttachWeapon(player, WeaponType.Sword);
        WeaponFactory.createAndAttachWeapon(player, WeaponType.Arrow);
        return mPlayer = player;
    }
    public static ICharacter createCharacter(String name, int hp, CharacterType type){
        if(type == CharacterType.Player)return null;
        if(mPlayer == null)return null;
        CharacterData data = new CharacterData();
        data.name = name;
        data.hp = hp;
        data.symbol = name.charAt(0);
        data.type = type;
        data.status = CharacterStatus.Free;
        ICharacter chtr = new Character(data);
        allNPCs.add(chtr);
        if(type == CharacterType.Ally){
            mPlayer.addAlly(chtr);
            chtr.addAlly(mPlayer);
        }
        if(type == CharacterType.Enemy){
            mPlayer.addEnemy(chtr);
            chtr.addEnemy(mPlayer);
        }
        return chtr;
    }

    public static void removeCharacter(ICharacter character){
        if(character.getCharacterType() == CharacterType.Player)return;
        if(character.getCharacterType() == CharacterType.Ally)mPlayer.removeAlly(character.getId());
        if(character.getCharacterType()  == CharacterType.Enemy)mPlayer.removeEnemy(character.getId());

        allNPCs.remove(character);
    }
    public static List<ICharacter> getAllNpcs(){
        List<ICharacter> temp = allNPCs;
        return temp;
    }
    public static ICharacter getPlayer(){
        return mPlayer;
    }
}