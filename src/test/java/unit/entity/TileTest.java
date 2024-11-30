package unit.entity;

import entity.Tile;
import mahjong.BaseTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for Tile.
 */
public class TileTest {
    BaseTile tile = BaseTile._2s;
    boolean isRedDora = true;
    boolean isUraDora = false;
    boolean isDora = true;

    Tile testTile;

    @BeforeEach
    void setUp() {
        testTile = new Tile(tile, isRedDora, isUraDora, isDora);
    }

    @Test
    void testIsRedDora() {
        assertEquals(isRedDora, testTile.isRedDora());
    }

    @Test
    void testIsUraDora() {
        assertEquals(isUraDora, testTile.isUraDora());
    }

    @Test
    void testIsDora() {
        assertEquals(isDora, testTile.isDora());
    }

    @Test
    void testGetTile() {
        assertEquals(tile, testTile.getTile());
    }
}
