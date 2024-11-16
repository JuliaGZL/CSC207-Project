package entity;

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
     Player create(String name, int score, List<Tile> hand);
}
