package use_case.add_tile;

import mahjong.BaseTiles;

/**
 * Input data format for the add_tile use case.
 */
public class AddTileInputData {
    private final BaseTiles tileId;
    private final String playerName;

    public AddTileInputData(BaseTiles tileId, String playerName) {
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
