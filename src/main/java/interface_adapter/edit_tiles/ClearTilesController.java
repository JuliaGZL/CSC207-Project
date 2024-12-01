package interface_adapter.edit_tiles;

import mahjong.BaseTile;
import usecase.clear_tiles.ClearTilesInputBoundary;
import usecase.clear_tiles.ClearTilesInputData;

public class ClearTilesController {
    private final ClearTilesInputBoundary interactor;

    public ClearTilesController(ClearTilesInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String playerName) {
        interactor.execute(new ClearTilesInputData(playerName));
    }
}
