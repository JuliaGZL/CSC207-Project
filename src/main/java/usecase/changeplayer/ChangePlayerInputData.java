package usecase.changeplayer;

/**
 * Input data format for the change player use case.
 */
public class ChangePlayerInputData {
  private final String playerName;

  /**
   * Constructs a ChangePlayerInputData with the specified player name.
   *
   * @param playerName the name of the player
   */
  public ChangePlayerInputData(String playerName) {
    this.playerName = playerName;
  }

  /**
   * Retrieves the name of the player.
   *
   * @return the name of the player as a String.
   */
  public String getPlayerName() {
    return playerName;
  }
}
