package usecase.removetile;

import mahjong.BaseTile;

/**
 * Input data format for the remove_tile use case.
 */
public class RemoveTileInputData {
  private final BaseTile tileId;
  private final String playerName;

  /**
   * Constructs a RemoveTileInputData object.
   *
   * @param tileId the ID of the tile to be removed
   * @param playerName the name of the player requesting the tile removal
   */
  public RemoveTileInputData(BaseTile tileId, String playerName) {
    this.tileId = tileId;
    this.playerName = playerName;
  }

  /**
   * Gets the name of the player requesting the tile removal.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Gets the ID of the tile to be removed.
   *
   * @return the tile ID
   */
  public BaseTile getTileId() {
    return tileId;
  }
}
