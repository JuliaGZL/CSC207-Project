package usecase.readhand;

/**
 * Output data format for the read hand use case.
 */
public class ReadHandOutputData {
  private final String playerName;
  private final String handInfo;

  /**
   * Constructs a ReadHandOutputData object.
   *
   * @param playerName the name of the player
   * @param handInfo the information about the hand
   */
  public ReadHandOutputData(String playerName, String handInfo) {
    this.playerName = playerName;
    this.handInfo = handInfo;
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
   * Gets the information about the hand.
   *
   * @return the hand information
   */
  public String getHandInfo() {
    return handInfo;
  }
}
