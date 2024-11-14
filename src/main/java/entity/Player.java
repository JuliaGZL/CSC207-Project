package entity;

import java.util.List;

/**
 * Class that represents a player.
 *  - id: ID of the player.
 *  - score: Score obtained by the player.
 *  - name: Display name of the player.
 *  - hand: List of Cards that the player is holding.
 */
public class Player {
    private int id;
    private int score;
    private String name;
    private List<Card> hand;

    public Player(int id, int score, String name, List<Card> hand) {
        this.id = id;
        this.score = score;
        this.name = name;
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }
}
