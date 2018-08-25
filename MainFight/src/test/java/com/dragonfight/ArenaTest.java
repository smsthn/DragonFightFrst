package com.dragonfight;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dragonfight.Arena.Arena;
import com.dragonfight.Arena.ArenaFactory;
import com.dragonfight.Arena.Rules;
import com.dragonfight.Character.CharacterFactory;
import com.dragonfight.Character.CharacterType;
import com.dragonfight.Character.ICharacter;
import com.dragonfight.Misc.MsLocation;
import com.dragonfight.weapons.WeaponFactory;
import com.dragonfight.weapons.types.WeaponType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArenaTest{

    private Arena mArena;
    private ICharacter mPlayer;
    private ICharacter mDragon;

    @BeforeEach
    public void setUp(){
        mArena = ArenaFactory.CreateArena(20, 20);
        mPlayer = CharacterFactory.createPlayer("Massoud", 500);
        mDragon = CharacterFactory.createCharacter("Dragon", 700, CharacterType.Enemy);
        mPlayer.setCurrentEnemy(mDragon);
        WeaponFactory.createAndAttachWeapon(mDragon, WeaponType.Fire);
    }

    @Test
    public void PrimeryCheck(){
        assertNotNull(mArena);
        assertNotNull(mPlayer);
        assertNotNull(mDragon);
        assertNotNull(mPlayer.getWeapon("Sword"));
        assertNotNull(mPlayer.getWeapon("Arrow"));
        assertNotNull(mDragon.getWeapon("Fire"));
        assertNotNull(mPlayer.getCurrentEnemy());
        assertTrue(mPlayer.getCurrentEnemy().getName() == "Dragon");
        assertNull(mArena.cellAt(new MsLocation(50,50)));
        assertNotNull(mArena.cellAt(new MsLocation(24,24)));
    }

    @Test
    public void AddPlayerToArena(){
        
    }
}