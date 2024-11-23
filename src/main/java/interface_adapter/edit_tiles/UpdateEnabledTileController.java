package interface_adapter.edit_tiles;

import usecase.update_enabled_tiles.UpdateEnabledTileInputData;
import usecase.update_enabled_tiles.UpdateEnabledTilesInputBoundary;

/**
 * Controller for update enabled tile -- basically does nothing, tho
 */
public class UpdateEnabledTileController {
    private final UpdateEnabledTilesInputBoundary interactor;

    public UpdateEnabledTileController(UpdateEnabledTilesInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String playerName, String target) {
        interactor.execute(new UpdateEnabledTileInputData(playerName, target));
    }
}
