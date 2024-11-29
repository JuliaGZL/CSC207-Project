package entity;

import mahjong.BaseTile;

import java.util.List;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new Player.
     * @param name the name of the new player
     * @param score the score of the new Player
     * @param hand the hand of the new Player
     * @return the new player
     */
     Player create(String name, int score, List<BaseTile> hand);
     Player createEmpty(String name);
}
