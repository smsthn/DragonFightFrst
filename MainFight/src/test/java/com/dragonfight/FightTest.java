package com.dragonfight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.util.List;

import com.dragonfight.Arena.Arena;
import com.dragonfight.Arena.CellType;
import com.dragonfight.Arena.ICell;
import com.dragonfight.Arena.IRule;
import com.dragonfight.Arena.Rules;
import com.dragonfight.Misc.MsLocation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

public class FightTest {
    static Arena arena;


    @BeforeAll
    public static void setUp() {

        arena = new Arena(15, 15);

    }

    @Test
    @DisplayName("Check Arena")
    public void checkArena() {
        arena.initializeAreana(null);
        for (int y = 0; y < arena.getHeight(); y++) {
            for (int x = 0; x < arena.getWidth(); x++) {
                assertNotNull(arena.cellAt(new MsLocation(x, y)), "Cell (" + x + "," + y + ") was Empty");
            }
        }
    }

    @Test
    public void checkEmpty() {
        arena.initializeAreana(null);
        for (int y = 0; y < arena.getHeight(); y++) {
            for (int x = 0; x < arena.getWidth(); x++) {
                assertEquals(arena.cellAt(new MsLocation(x, y)).getType(), CellType.Empty);
            }
        }
    }

    @Test
    public void CheckWalls() {
        Rules rules = new Rules();
        arena.initializeAreana(rules.getRules());
        for (int y = 0; y < arena.getHeight(); y++) {
            for (int x = 0; x < arena.getWidth(); x++) {
                if(((IRule)rules.getRules().values().toArray()[0]).check(arena.cellAt(new MsLocation(x,y)))){
                assertEquals(arena.cellAt(new MsLocation(x, y)).getType(), CellType.StaticWall);
                }
            }
        }
    }

}
