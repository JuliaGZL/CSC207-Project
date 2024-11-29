package entity;

import java.util.List;

/**
 * The representation of a user (player) in our program.
 */
public interface User {

    /**
     * Returns the name of the user.
     * @return the name of the user.
     */
    String getName();

    /**
     * Returns the score of the user.
     * @return the score of the user.
     */
    int getScore();

    /**
     * Returns the hand of the user.
     * @return the hand of the user.
     */
    List<Tile> getHand();
}
