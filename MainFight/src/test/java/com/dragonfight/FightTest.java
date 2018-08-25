package com.dragonfight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;

import java.util.List;

import com.dragonfight.Arena.Arena;
import com.dragonfight.Arena.ArenaFactory;
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

        arena = ArenaFactory.CreateArena(20, 20);

    }

    @Test
    @DisplayName("Check Arena")
    public void checkArena() {
        for (int y = 0; y < arena.getHeight(); y++) {
            for (int x = 0; x < arena.getWidth(); x++) {
                assertNotNull(arena.cellAt(new MsLocation(x, y)), "Cell (" + x + "," + y + ") was Empty");
            }
        }
    }

    @Test
    public void checkEmpty() {
        for (int y = 0; y < arena.getHeight(); y++) {
            for (int x = 0; x < arena.getWidth(); x++) {
                if (x != 0 && x != 19 && y != 0 && y != 19) {
                assertEquals(arena.cellAt(new MsLocation(x, y)).getType(), CellType.Empty);
                }
            }
        }
    }

    @Test
    public void CheckWalls() {
        for (int y = 0; y < arena.getHeight(); y++) {
            for (int x = 0; x < arena.getWidth(); x++) {
                if (x == 0 || x == 19 || y == 0 || y == 19) {
                    
                    assertEquals(arena.cellAt(new MsLocation(x, y)).getType() , CellType.StaticWall);
                }
            }
        }
    }

}
