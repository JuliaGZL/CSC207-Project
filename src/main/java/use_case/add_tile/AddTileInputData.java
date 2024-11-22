package use_case.add_tile;

import mahjong.mahjong.BaseTile;

/**
 * Input data format for the add_tile use case.
 */
public class AddTileInputData {
    private final BaseTile tileId;
    private final String playerName;

    public AddTileInputData(BaseTile tileId, String playerName) {
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
