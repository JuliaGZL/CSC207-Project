package interfaceadapter.edittiles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import mahjong.BaseTile;

/**
 * Represents the state of the tile selector in the game.
 */
public class TileSelectorState {
  // Message for "inform selector" use case.
  private String message;

  // Player to insert tiles
  private String playerName = "default";

  // Only tiles in this set will be enabled for selection.
  private Set<BaseTile> enabledTiles = new HashSet<BaseTile>(List.of(BaseTile.values()));

  /**
   * Constructs a new TileSelectorState with default values.
   */
  public TileSelectorState() {

  }

  /**
   * Gets the message for the tile selector.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message for the tile selector.
   *
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets the name of the player.
   *
   * @return the player name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Sets the name of the player.
   *
   * @param playerName the player name to set
   */
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  /**
   * Gets the set of enabled tiles for selection.
   *
   * @return the set of enabled tiles
   */
  public Set<BaseTile> getEnabledTiles() {
    return enabledTiles;
  }

  /**
   * Sets the set of enabled tiles for selection.
   *
   * @param enabledTiles the set of enabled tiles to set
   */
  public void setEnabledTiles(Set<BaseTile> enabledTiles) {
    this.enabledTiles = enabledTiles;
  }
}
