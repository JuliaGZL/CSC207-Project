package usecase.changeplayer;

/**
 * Output data format for the change player use case.
 */
public class ChangePlayerOutputData {
  private final String playerName;
  private final int score;

  /**
   * Constructs a ChangePlayerOutputData instance.
   *
   * @param playerName the name of the player
   * @param score the score of the player
   */
  public ChangePlayerOutputData(String playerName, int score) {
    this.playerName = playerName;
    this.score = score;
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
   * Gets the score of the player.
   *
   * @return the player's score
   */
  public int getScore() {
    return score;
  }
}
