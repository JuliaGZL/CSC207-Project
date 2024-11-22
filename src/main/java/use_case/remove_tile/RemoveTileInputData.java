package use_case.remove_tile;

import mahjong.mahjong.BaseTile;

/**
 * Input data format for the remove_tile use case.
 */
public class RemoveTileInputData {
    private final BaseTile tileId;
    private final String playerName;

    public RemoveTileInputData(BaseTile tileId, String playerName) {
        this.tileId = tileId;
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public BaseTile getTileId() {
        return tileId;
    }
}
