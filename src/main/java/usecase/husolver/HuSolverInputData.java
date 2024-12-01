package usecase.husolver;

/**
 * Input data format for the Hu solver.
 */
public class HuSolverInputData {
  private final String playerName;

  /**
   * Constructs a new HuSolverInputData instance.
   *
   * @param playerName the name of the player
   */
  public HuSolverInputData(String playerName) {
    this.playerName = playerName;
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
