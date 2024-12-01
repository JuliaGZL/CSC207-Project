package interfaceadapter.edittiles;

import mahjong.BaseTile;
import usecase.addtile.AddTileInputBoundary;
import usecase.addtile.AddTileInputData;

/**
 * Controller for the add_tile use case.
 */
public class AddTileController {
  private final AddTileInputBoundary addTileInteractor;

  /**
   * Constructs an AddTileController with the specified interactor.
   *
   * @param addTileInteractor the interactor to handle the add tile use case
   */
  public AddTileController(AddTileInputBoundary addTileInteractor) {
    this.addTileInteractor = addTileInteractor;
  }

  /**
   * Executes the add tile use case with the given tile and player name.
   *
   * @param tileId the tile to be added
   * @param playerName the name of the player adding the tile
   */
  public void execute(BaseTile tileId, String playerName) {
    final AddTileInputData inputData = new AddTileInputData(tileId, playerName);
    addTileInteractor.execute(inputData);
  }
}
