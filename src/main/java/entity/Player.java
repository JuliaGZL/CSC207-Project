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
    private Boolean[] attributes = {
            false, false, false,
            false, false, false,
            false, false, false
    };
    private int numAkaDora = 0;
    private String winType = "Tsumo";
    private String roundWind = "East";
    private String seatWind = "East";


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

    public Player(String name, int score, List<Tile> hand, Boolean[] attributes,
                  int numAkaDora, String roundWind, String seatWind) {
        this.name = name;
        this.score = score;
        this.hand = hand;
        this.attributes = attributes;
        this.numAkaDora = numAkaDora;
        this.roundWind = roundWind;
        this.seatWind = seatWind;
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

    public int getNumAkaDora() {
        return numAkaDora;
    }

    public String getWinType() {
        return winType;
    }

    public String getRoundWind() {
        return roundWind;
    }

    public String getSeatWind() {
        return seatWind;
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

    public void setAttributes(Boolean[] attributes) {
        this.attributes = attributes;
    }

    public void setWinType(String winType) {
        this.winType = winType;
    }

    public void setNumAkaDora(int numAkaDora) {
        this.numAkaDora = numAkaDora;
    }

    public void setRoundWind(String roundWind) {
        this.roundWind = roundWind;
    }

    public void setSeatWind(String seatWind) {
        this.seatWind = seatWind;
    }
}