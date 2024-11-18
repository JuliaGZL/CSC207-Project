package use_case.remove_tile;

import mahjong.BaseTiles;

/**
 * Input data format for the remove_tile use case.
 */
public class RemoveTileInputData {
    private final BaseTiles tileId;
    private final String playerName;

    public RemoveTileInputData(BaseTiles tileId, String playerName) {
        this.tileId = tileId;
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public BaseTiles getTileId() {
        return tileId;
    }
}
