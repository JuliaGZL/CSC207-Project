package interface_adapter.edit_tiles;

import mahjong.BaseTiles;
import use_case.add_tile.AddTileInputBoundary;
import use_case.add_tile.AddTileInputData;

/**
 * Controller for the add_tile use case.
 */
public class AddTileController {
    private final AddTileInputBoundary addTileInteractor;

    public AddTileController(AddTileInputBoundary addTileInteractor) {
        this.addTileInteractor = addTileInteractor;
    }

    public void execute(BaseTiles tileId, String playerName) {
        final AddTileInputData inputData = new AddTileInputData(tileId, playerName);
        addTileInteractor.execute(inputData);
    }
}
