package interface_adapter.edit_tiles;

import mahjong.BaseTile;
import use_case.remove_tile.RemoveTileInputBoundary;
import use_case.remove_tile.RemoveTileInputData;

public class RemoveTileController {
    private final RemoveTileInputBoundary interactor;
    public RemoveTileController(RemoveTileInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(BaseTile tileId, String playerName) {
        interactor.execute(new RemoveTileInputData(tileId, playerName));
    }
}
