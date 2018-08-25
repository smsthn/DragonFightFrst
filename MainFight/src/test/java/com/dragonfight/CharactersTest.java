package com.dragonfight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.dragonfight.Arena.Arena;
import com.dragonfight.Arena.Rules;
import com.dragonfight.Character.CharacterFactory;
import com.dragonfight.Character.ICharacter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharactersTest{


    private static Arena arena;
    private static ICharacter mPlayer;

    @BeforeAll
    public static void setUp(){
        arena = new Arena(50,50);
        arena.initializeAreana(new Rules().getRules());
    }
    @BeforeEach
    public void setUp2(){
        mPlayer = CharacterFactory.createPlayer("Massoud", 500);
    }

    @Test
    public void CheckPlayer(){
        assertEquals(mPlayer.getName(), "Massoud");
        assertEquals(mPlayer.getHp(), 500);
        
    }
}