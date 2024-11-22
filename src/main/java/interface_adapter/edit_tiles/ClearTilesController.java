package interface_adapter.edit_tiles;

import mahjong.mahjong.BaseTile;
import use_case.clear_tiles.ClearTilesInputBoundary;
import use_case.clear_tiles.ClearTilesInputData;

public class ClearTilesController {
    private final ClearTilesInputBoundary interactor;
    public ClearTilesController(ClearTilesInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String playerName) {
        interactor.execute(new ClearTilesInputData(playerName));
    }
}
