package unit.entity;

import entity.Player;
import entity.PlayerFactory;
import entity.Tile;
import mahjong.BaseTile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for PlayerFactory.
 */
public class PlayerFactoryTest {
    PlayerFactory playerFactory;

    @BeforeEach
    void setUp() {
        playerFactory = new PlayerFactory();
    }

    @Test
    void testCreate() {
        String name = "test";
        int score = 0;
        List<BaseTile> hand = new ArrayList<>();
        hand.add(BaseTile._1m);
        List<BaseTile> dora = new ArrayList<>();
        dora.add(BaseTile._2m);
        List<BaseTile> uradora = new ArrayList<>();
        uradora.add(BaseTile._3m);

        Player player = playerFactory.create(name, score, hand, dora, uradora);

        assertEquals(name, player.getName());
        assertEquals(score, player.getScore());
        assertEquals(hand.size() + dora.size() +
                uradora.size(), player.getHand().size());
        assertEquals(dora.size(), player.getDora().size());
        assertEquals(uradora.size(), player.getUradora().size());
        assertEquals(hand.get(0), player.getHand().get(0).getBaseTile());
        // assertEquals(dora, player.getDora());
    }

    @Test
    void testCreateEmpty() {
        String name = "test";

        Player player = playerFactory.createEmpty(name);

        assertEquals(name, player.getName());
        assertEquals(0, player.getScore());
        assertEquals(0, player.getHand().size());
        assertEquals(0, player.getDora().size());
        assertEquals(0, player.getUradora().size());
    }
}
