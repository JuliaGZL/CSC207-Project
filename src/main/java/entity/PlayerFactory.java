package entity;

import mahjong.BaseTile;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating Player objects.
 */
public class PlayerFactory {
    public Player create(String name,
                         int score,
                         List<BaseTile> hand,
                         List<BaseTile> dora,
                         List<BaseTile> uradora) {
        List<Tile> handTiles = new ArrayList<Tile>();
        for(BaseTile t : hand) {
            handTiles.add(new Tile(t, false, false, false));
        }

        List<Tile> doraTiles = new ArrayList<Tile>();
        for(BaseTile t : dora) {
            handTiles.add(new Tile(t, false, false, false));
        }

        List<Tile> uradoraTiles = new ArrayList<Tile>();
        for(BaseTile t : uradora) {
            handTiles.add(new Tile(t, false, false, false));
        }

        return new Player(name, score, handTiles, doraTiles, uradoraTiles);
    }

    public Player createEmpty(String name) {
        List<Tile> handTiles = new ArrayList<Tile>();
        List<Tile> doraTiles = new ArrayList<Tile>();
        List<Tile> uradoraTiles = new ArrayList<Tile>();
        return new Player(name, 0, handTiles, doraTiles, uradoraTiles);
    }
}
