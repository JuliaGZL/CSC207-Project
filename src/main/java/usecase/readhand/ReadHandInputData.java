package usecase.readhand;

/**
 * Represents the input data required to read a player's hand.
 */
public class ReadHandInputData {
  private final String playerName;
  private final String[] attributeNames;

  /**
   * Constructs a new ReadHandInputData instance.
   *
   * @param playerName the name of the player
   * @param attributeNames the names of the attributes
   */
  public ReadHandInputData(String playerName, String[] attributeNames) {
    this.playerName = playerName;
    this.attributeNames = attributeNames;
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
   * Gets the names of the attributes.
   *
   * @return an array of attribute names
   */
  public String[] getAttributeNames() {
    return attributeNames;
  }
}
