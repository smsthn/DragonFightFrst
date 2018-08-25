package com.dragonfight;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.dragonfight.Arena.Arena;
import com.dragonfight.Arena.CellType;
import com.dragonfight.Arena.ICell;
import com.dragonfight.Character.Character;
import com.dragonfight.Character.CharacterFactory;
import com.dragonfight.Character.CharacterType;
import com.dragonfight.Character.ICharacter;
import com.dragonfight.Misc.MsLocation;
import com.dragonfight.weapons.IWeapon;
import com.dragonfight.weapons.WeaponFactory;
import com.dragonfight.weapons.types.WeaponType;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import javafx.beans.binding.When;

public class WeaponsTest{
    private static ICharacter character1;
    private static ICharacter character2;
    private static IWeapon sword;
    private  static IWeapon arrow;
    private  static IWeapon fire;

    ICell cell1;
    ICell cell2;

    private Arena arena;

    @BeforeAll
    public static void setUp1(){
     
    }

    @BeforeEach
    public void setUp(){
        character1 = CharacterFactory.createPlayer("Massoud", 500);
        character2 = CharacterFactory.createCharacter("Char", 500,CharacterType.Enemy);
        character1.setCurrentEnemy(character2);

        sword = WeaponFactory.createAndAttachWeapon(character1, WeaponType.Sword);
        arrow = WeaponFactory.createAndAttachWeapon(character1, WeaponType.Arrow);
        fire =  WeaponFactory.createAndAttachWeapon(character2, WeaponType.Fire);

        cell1 = Mockito.mock(ICell.class,Mockito.RETURNS_DEEP_STUBS);
        cell2 = Mockito.mock(ICell.class,Mockito.RETURNS_DEEP_STUBS);
        
        Mockito.when(cell1.getType()).thenReturn(CellType.Empty);
        Mockito.when(cell2.getType()).thenReturn(CellType.Empty);
        character1.setCell(cell1);
        character2.setCell(cell2);

        arena = new Arena(20, 20);
        Mockito.when(cell1.getArena()).thenReturn(arena);
        Mockito.when(cell2.getArena()).thenReturn(arena);
    }

    @Test
    public void WeaponNotNull(){
        //assertNotNull(sword);
        //assertNotNull(arrow);
        assertNotNull(fire);
        
        //assertTrue(character2.getAllWeapons().isEmpty(),character2.getAllWeapons().get(0).getName());
    }
    @Test
    public void checkDuplicate(){
        IWeapon wpn = WeaponFactory.createAndAttachWeapon(character1, WeaponType.Sword);
        assertNull(wpn);
    }

    @Test
    public void checkDeleted(){
        WeaponFactory.removeAndDetachWeapon(fire);
        assertNull(WeaponFactory.removeAndDetachWeapon(fire));
    }

    @Test
    public void checkSword(){
        Mockito.when(cell1.getLocation()).thenReturn(new MsLocation(5,1));
        Mockito.when(cell2.getLocation()).thenReturn(new MsLocation(5,15));
        IWeapon sword = character1.getWeapon("Sword");
        assertNotNull(sword);
        assertNotNull(character1.getCell());
        assertFalse(sword.Condition());
    }
    @Test
    
    public void checkSword2(){
        Mockito.when(cell1.getLocation()).thenReturn(new MsLocation(5,1));
        Mockito.when(cell2.getLocation()).thenReturn(new MsLocation(5,3));
        IWeapon sword = character1.getWeapon("Sword");
        assertNotNull(sword);
        assertNotNull(character1.getCell());
        assertTrue(sword.Condition());
    }
    @Test
    
    public void CheckArrow(){
        for (MsLocation[] loc : data()) {
            
        
        Mockito.when(cell1.getLocation()).thenReturn(loc[0]);
        Mockito.when(cell2.getLocation()).thenReturn(loc[1]);
        IWeapon arrow = character1.getWeapon("Arrow");
        assertNotNull(arrow);
        assertNotNull(character1.getCell());
        assertTrue(arrow.Condition());
    }
    }
    private static List<MsLocation[]> data(){
        List<MsLocation[]> loc = Arrays.asList(new MsLocation[][]{
            {new MsLocation(1, 5),new MsLocation(5, 10)},
            {new MsLocation(8, 3),new MsLocation(8, 3)},
            {new MsLocation(6, 13),new MsLocation(8, 3)},
            {new MsLocation(8, 3),new MsLocation(1, 20)},
            {new MsLocation(2, 18),new MsLocation(2, 18)},
            {new MsLocation(0, 11),new MsLocation(8, 3)},
            {new MsLocation(8, 3),new MsLocation(5, 10)},
        });
        return loc;
    }
    
}