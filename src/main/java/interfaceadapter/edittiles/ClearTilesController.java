package interfaceadapter.edittiles;

import usecase.cleartiles.ClearTilesInputBoundary;
import usecase.cleartiles.ClearTilesInputData;

/**
 * Controller for clearing tiles.
 */
public class ClearTilesController {
  private final ClearTilesInputBoundary interactor;

  /**
   * Constructs a ClearTilesController with the given interactor.
   *
   * @param interactor the interactor to handle the clear tiles use case
   */
  public ClearTilesController(ClearTilesInputBoundary interactor) {
    this.interactor = interactor;
  }

  /**
   * Executes the clear tiles use case for the given player.
   *
   * @param playerName the name of the player
   */
  public void execute(String playerName) {
    interactor.execute(new ClearTilesInputData(playerName));
  }
}
