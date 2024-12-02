package usecase.cleartiles;

/**
 * Input data format for the clear_tiles use case.
 */
public class ClearTilesInputData {
  private final String playerName;

  /**
   * Constructs a new ClearTilesInputData instance.
   *
   * @param playerName the name of the player
   */
  public ClearTilesInputData(String playerName) {
    this.playerName = playerName;
  }

  /**
   * Gets the name of the player.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }
}
