package entity;

import java.util.List;

/**
 * Class that represents a player.
 */
public class Player {
    // username
    private final String name;

    // accumulated score
    private int score;

    // tile lists
    private List<Tile> hand;
    private List<Tile> dora;
    private List<Tile> uradora;

    /**
     * For a new player with name only.
     * @param name literally
     */
    public Player(String name) {
        this.name = name;
    }

    // creates a fully augmented player.
    public Player(String name,
                  int score,
                  List<Tile> hand,
                  List<Tile> dora,
                  List<Tile> uradora) {
        this.name = name;
        this.score = score;
        this.hand = hand;
        this.dora = dora;
        this.uradora = uradora;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public List<Tile> getHand() {
        return hand;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHand(List<Tile> hand) {
        this.hand = hand;
    }

    public List<Tile> getDora() {
        return dora;
    }

    public void setDora(List<Tile> dora) {
        this.dora = dora;
    }

    public List<Tile> getUradora() {
        return uradora;
    }

    public void setUradora(List<Tile> uradora) {
        this.uradora = uradora;
    }
}