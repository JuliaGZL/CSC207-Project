package usecase.editstatus;


/**
 * The input data for the Edit Status Use Case.
 */
public class EditStatusInputData {
  private String playerName;
  private String winType = "Tsumo";
  private String roundWind = "East";
  private String seatWind = "East";
  private int numAkadora = 0;
  private final Boolean[] attributes; // Attributes of the hand in this round

  /**
   * Constructs an EditStatusInputData object with the specified attributes.
   *
   * @param attributes the attributes of the hand in this round
   * @param numAkadora the number of Akadora
   * @param seatWind   the seat wind
   * @param roundWind  the round wind
   * @param winType    the type of win
   * @param playerName the name of the player
   */
  public EditStatusInputData(
      Boolean[] attributes, int numAkadora,
      String seatWind, String roundWind, String winType,
      String playerName) {
    this.attributes = attributes;
    this.numAkadora = numAkadora;
    this.seatWind = seatWind;
    this.roundWind = roundWind;
    this.winType = winType;
    this.playerName = playerName;
  }

  // Getters

  /**
   * Gets the name of the player.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Gets the type of win.
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
   * Gets the number of Akadora.
   *
   * @return the number of Akadora
   */
  public int getNumAkadora() {
    return numAkadora;
  }

  /**
   * Gets the attributes of the hand in this round.
   *
   * @return the attributes of the hand
   */
  public Boolean[] getAttributes() {
    return attributes;
  }
}
