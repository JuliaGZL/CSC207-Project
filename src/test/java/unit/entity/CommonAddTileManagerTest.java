package unit.entity;

import entity.CommonAddTileManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for CommonAddTileManager.
 */
public class CommonAddTileManagerTest {
    String name = "test";
    String addTileType = "Hand";

    CommonAddTileManager manager1;
    CommonAddTileManager manager2;

    @BeforeEach
    void setUp() {
        manager1 = new CommonAddTileManager(name);
        manager2 = new CommonAddTileManager(name, addTileType);
    }

    @Test
    void testGetAddTileType() {
        assertEquals("Hand", manager1.getAddTileType());
        assertEquals("Hand", manager2.getAddTileType());
    }

    @Test
    void testSetAddTileType() {
        manager1.setAddTileType("Dora");
        manager2.setAddTileType("Dora");

        assertEquals("Dora", manager1.getAddTileType());
        assertEquals("Dora", manager2.getAddTileType());
    }
}
