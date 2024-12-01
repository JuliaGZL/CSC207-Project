package entity;

import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;

/**
 * Factory for creating Player objects.
 */
public class PlayerFactory {

    /**
     * Creates a Player object with the specified name, score, hand, dora, and
     * uradora tiles.
     *
     * @param name    the name of the player
     * @param score   the score of the player
     * @param hand    the hand tiles of the player
     * @param dora    the dora tiles of the player
     * @param uradora the uradora tiles of the player
     * @return a new Player object
     */
    public Player create(String name,
            int score,
            List<BaseTile> hand,
            List<BaseTile> dora,
            List<BaseTile> uradora) {
        List<Tile> handTiles = new ArrayList<Tile>();
        for (BaseTile t : hand) {
            handTiles.add(new Tile(t));
        }

        List<Tile> doraTiles = new ArrayList<Tile>();
        for (BaseTile t : dora) {
            handTiles.add(new Tile(t));
            doraTiles.add(new Tile(t));
        }

        List<Tile> uradoraTiles = new ArrayList<Tile>();
        for (BaseTile t : uradora) {
            handTiles.add(new Tile(t));
            uradoraTiles.add(new Tile(t));
        }

        return new Player(name, score, handTiles, doraTiles, uradoraTiles);
    }

    /**
     * Creates an empty Player object with the specified name and a score of 0.
     *
     * @param name the name of the player
     * @return a new Player object with empty hand, dora, and uradora tiles
     */
    public Player createEmpty(String name) {
        List<Tile> handTiles = new ArrayList<Tile>();
        List<Tile> doraTiles = new ArrayList<Tile>();
        List<Tile> uradoraTiles = new ArrayList<Tile>();
        return new Player(name, 0, handTiles, doraTiles, uradoraTiles);
    }
}
