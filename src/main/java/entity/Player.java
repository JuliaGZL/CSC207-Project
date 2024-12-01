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
   *
   * @param name the player's name
   */
  public Player(String name) {
    this.name = name;
  }

  /**
   * Creates a fully augmented player.
   *
   * @param name    the player's name
   * @param score   the player's score
   * @param hand    the player's hand tiles
   * @param dora    the player's dora tiles
   * @param uradora the player's uradora tiles
   */
  public Player(String name, int score, List<Tile> hand, List<Tile> dora, List<Tile> uradora) {
    this.name = name;
    this.score = score;
    this.hand = hand;
    this.dora = dora;
    this.uradora = uradora;
  }

  /**
   * Creates a player with additional attributes.
   *
   * @param name       the player's name
   * @param score      the player's score
   * @param hand       the player's hand tiles
   * @param attributes the player's attributes
   * @param numAkaDora the number of aka dora tiles
   * @param roundWind  the round wind
   * @param seatWind   the seat wind
   */
  public Player(
      String name, int score, List<Tile> hand,
      Boolean[] attributes, int numAkaDora,
      String roundWind, String seatWind) {
    this.name = name;
    this.score = score;
    this.hand = hand;
    this.attributes = attributes;
    this.numAkaDora = numAkaDora;
    this.roundWind = roundWind;
    this.seatWind = seatWind;
  }

  /**
   * Gets the player's name.
   *
   * @return the player's name
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the player's score.
   *
   * @return the player's score
   */
  public int getScore() {
    return score;
  }

  /**
   * Gets the player's hand tiles.
   *
   * @return the player's hand tiles
   */
  public List<Tile> getHand() {
    return hand;
  }

  /**
   * Gets the player's attributes.
   *
   * @return the player's attributes
   */
  public Boolean[] getAttributes() {
    return attributes;
  }

  /**
   * Gets the number of aka dora tiles.
   *
   * @return the number of aka dora tiles
   */
  public int getNumAkaDora() {
    return numAkaDora;
  }

  /**
   * Gets the win type.
   *
   * @return the win type
   */
  public String getWinType() {
    return winType;
  }

  /**
   * Gets the round wind.
   *
   * @return the round wind
   */
  public String getRoundWind() {
    return roundWind;
  }

  /**
   * Gets the seat wind.
   *
   * @return the seat wind
   */
  public String getSeatWind() {
    return seatWind;
  }

  /**
   * Sets the player's score.
   *
   * @param score the new score
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Sets the player's hand tiles.
   *
   * @param hand the new hand tiles
   */
  public void setHand(List<Tile> hand) {
    this.hand = hand;
  }

  /**
   * Gets the player's dora tiles.
   *
   * @return the player's dora tiles
   */
  public List<Tile> getDora() {
    return dora;
  }

  /**
   * Sets the player's dora tiles.
   *
   * @param dora the new dora tiles
   */
  public void setDora(List<Tile> dora) {
    this.dora = dora;
  }

  /**
   * Gets the player's uradora tiles.
   *
   * @return the player's uradora tiles
   */
  public List<Tile> getUradora() {
    return uradora;
  }

  /**
   * Sets the player's uradora tiles.
   *
   * @param uradora the new uradora tiles
   */
  public void setUradora(List<Tile> uradora) {
    this.uradora = uradora;
  }

  /**
   * Sets the player's attributes.
   *
   * @param attributes the new attributes
   */
  public void setAttributes(Boolean[] attributes) {
    this.attributes = attributes;
  }

  /**
   * Sets the win type.
   *
   * @param winType the new win type
   */
  public void setWinType(String winType) {
    this.winType = winType;
  }

  /**
   * Sets the number of aka dora tiles.
   *
   * @param numAkaDora the new number of aka dora tiles
   */
  public void setNumAkaDora(int numAkaDora) {
    this.numAkaDora = numAkaDora;
  }

  /**
   * Sets the round wind.
   *
   * @param roundWind the new round wind
   */
  public void setRoundWind(String roundWind) {
    this.roundWind = roundWind;
  }

  /**
   * Sets the seat wind.
   *
   * @param seatWind the new seat wind
   */
  public void setSeatWind(String seatWind) {
    this.seatWind = seatWind;
  }
}