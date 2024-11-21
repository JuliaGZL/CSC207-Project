package entity;

import java.util.List;

/**
 * Class that represents a player.
 *  - name: Name of the player, must be unique.
 *  - score: Score obtained by the player.
 *  - hand: List of tiles that the player is holding.
 */
public class Player {
    private final String name;
    private int score;
    private List<Tile> hand;
    private Boolean[] attributes = new Boolean[9];

    public Player(String name, int score, List<Tile> hand) {
        this.name = name;
        this.score = score;
        this.hand = hand;
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

    public Boolean[] getAttributes() {
        return attributes;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHand(List<Tile> hand) {
        this.hand = hand;
    }

    public void setAttributes(Boolean[] attributes) {
        this.attributes = attributes;
    }
}