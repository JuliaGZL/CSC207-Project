package entity;

import mahjong.BaseTile;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating Player objects.
 */
public class PlayerFactory implements UserFactory {
    @Override
    public Player create(String name, int score, List<BaseTile> hand) {
        List handTiles = new ArrayList<Tile>();
        for(BaseTile t : hand) {
            handTiles.add(new Tile(t, false, false, false));
        }
        return new Player(name, score, handTiles);
    }

    @Override
    public Player createEmpty(String name) {
        return create(name, 0, new ArrayList<BaseTile>());
    }
}
