package unit.entity;

import entity.AddTileManager;
import entity.CommonAddTileManagerFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for CommonAddTileManagerFactory.
 */
public class CommonAddTileManagerFactoryTest {
    @Test
    void testCreate() {
        String dummyType = "dummyType";
        CommonAddTileManagerFactory factory = new CommonAddTileManagerFactory();
        AddTileManager manager = factory.create(dummyType);
        assertEquals(dummyType, manager.getAddTileType());
    }
}
