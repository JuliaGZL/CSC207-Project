package usecase.cleartiles;

/**
 * Output data format for the clear_tiles use case.
 */
public class ClearTilesOutputData {
  private final boolean failed;
  private final String playerName;

  /**
   * Constructs a ClearTilesOutputData object.
   *
   * @param failed whether the operation failed
   * @param playerName the name of the player
   */
  public ClearTilesOutputData(boolean failed, String playerName) {
    this.failed = failed;
    this.playerName = playerName;
  }

  /**
   * Returns whether the operation failed.
   *
   * @return true if the operation failed, false otherwise
   */
  public boolean isFailed() {
    return this.failed;
  }

  /**
   * Returns the name of the player.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }
}
