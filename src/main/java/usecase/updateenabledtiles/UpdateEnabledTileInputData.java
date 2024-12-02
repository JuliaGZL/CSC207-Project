package usecase.updateenabledtiles;

/**
 * Input data structure for update enabled tile use case.
 */
public class UpdateEnabledTileInputData {
  private final String playerName;
  private final String target;

  /**
   * Constructs an UpdateEnabledTileInputData instance.
   *
   * @param playerName the name of the player
   * @param target the target tile
   */
  public UpdateEnabledTileInputData(String playerName, String target) {
    this.playerName = playerName;
    this.target = target;
  }

  /**
   * Gets the name of the player.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Gets the target tile.
   *
   * @return the target tile
   */
  public String getTarget() {
    return target;
  }
}
