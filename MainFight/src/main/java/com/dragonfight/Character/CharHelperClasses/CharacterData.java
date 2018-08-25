package com.dragonfight.Character.CharHelperClasses;

import java.util.ArrayList;
import java.util.List;

import com.dragonfight.Character.CharacterStatus;
import com.dragonfight.Character.CharacterType;
import com.dragonfight.Character.ICharacter;

public class CharacterData{
    private static int idCounter = 0;
    public int roundCount;
    public int round;
    public final int id;
    public String name;
    public int hp;
    public char symbol;
    public CharacterType type;
    public CharacterStatus status;
    public ICharacter currentEnemy;
    public List<ICharacter> allEnemies;
    public List<ICharacter> allAllies;


    public CharacterData(){
        this.id = ++idCounter;
        currentEnemy = null;
        allEnemies = new ArrayList<>();
        round = 0;
        roundCount = 0;
        allEnemies = new ArrayList<>();
        allAllies = new ArrayList<>();

    }

    public CharacterData(CharacterType type,CharacterStatus status, String name, int hp, char symbol){
        this();
        this.name = name;
        this.hp = hp;
        this.symbol = symbol;
        this.type = type;
        this.status = status;
        allEnemies = new ArrayList<>();
        allAllies = new ArrayList<>();
    }

   
}