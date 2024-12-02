package interfaceadapter.playerevents;

/**
 * State for the player events panel.
 */
public class PlayerEventsState {
  private String playerName = "default";
  private int score = 0;
  private String message = "";

  /**
   * Constructs a new PlayerEventsState with default values.
   */
  public PlayerEventsState() {

  }

  /**
   * Gets the player's name.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Sets the player's name.
   *
   * @param playerName the new player's name
   */
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  /**
   * Gets the player's score.
   *
   * @return the player's score
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets the player's score.
   *
   * @param score the new player's score
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Gets the message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message.
   *
   * @param message the new message
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
