package usecase.addtile;

import mahjong.BaseTile;

/**
 * Input data format for the add_tile use case.
 */
public class AddTileInputData {
  private final BaseTile tileId;
  private final String playerName;

  /**
   * Constructs an AddTileInputData instance.
   *
   * @param tileId the tile identifier
   * @param playerName the name of the player
   */
  public AddTileInputData(BaseTile tileId, String playerName) {
    this.tileId = tileId;
    this.playerName = playerName;
  }

  public String getPlayerName() {
    return playerName;
  }

  public BaseTile getTileId() {
    return tileId;
  }
}
