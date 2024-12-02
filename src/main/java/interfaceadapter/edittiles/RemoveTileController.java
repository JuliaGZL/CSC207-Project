package interfaceadapter.edittiles;

import mahjong.BaseTile;
import usecase.removetile.RemoveTileInputBoundary;
import usecase.removetile.RemoveTileInputData;

/**
 * Controller for removing a tile from the game.
 */
public class RemoveTileController {
  private final RemoveTileInputBoundary interactor;

  /**
   * Constructs a RemoveTileController with the given interactor.
   *
   * @param interactor the interactor to handle the remove tile use case
   */
  public RemoveTileController(RemoveTileInputBoundary interactor) {
    this.interactor = interactor;
  }

  /**
   * Executes the remove tile use case.
   *
   * @param tileId the tile to be removed
   * @param playerName the name of the player removing the tile
   */
  public void execute(BaseTile tileId, String playerName) {
    interactor.execute(new RemoveTileInputData(tileId, playerName));
  }
}
