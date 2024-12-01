package usecase.pullremotehand;

/**
 * Input data for the pull hand from discord use case.
 */
public class PullRemoteHandInputData {
  private final String playerName;

  /**
   * Constructs a new PullRemoteHandInputData with the specified player name.
   *
   * @param playerName the name of the player
   */
  public PullRemoteHandInputData(String playerName) {
    this.playerName = playerName;
  }

  public String getPlayerName() {
    return playerName;
  }
}
