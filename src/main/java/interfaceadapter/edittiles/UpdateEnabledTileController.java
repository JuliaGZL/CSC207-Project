package interfaceadapter.edittiles;

import usecase.updateenabledtiles.UpdateEnabledTileInputData;
import usecase.updateenabledtiles.UpdateEnabledTilesInputBoundary;

/**
 * Controller for updating enabled tiles.
 */
public class UpdateEnabledTileController {
  private final UpdateEnabledTilesInputBoundary interactor;

  /**
   * Constructs an UpdateEnabledTileController with the given interactor.
   *
   * @param interactor the interactor to handle the update enabled tile use case
   */
  public UpdateEnabledTileController(UpdateEnabledTilesInputBoundary interactor) {
    this.interactor = interactor;
  }

  /**
   * Executes the update enabled tile use case.
   *
   * @param playerName the name of the player
   * @param target the target tile to be updated
   */
  public void execute(String playerName, String target) {
    interactor.execute(new UpdateEnabledTileInputData(playerName, target));
  }
}
