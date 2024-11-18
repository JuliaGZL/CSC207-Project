package entity;

import java.util.List;

/**
 * Factory for creating Player objects.
 */
public class PlayerFactory implements UserFactory {

    @Override
    public Player create(String name, int score, List<Tile> hand) {
        return new Player(name, score, hand);
    }
}
