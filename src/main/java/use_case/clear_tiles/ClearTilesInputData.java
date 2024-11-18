package use_case.clear_tiles;

/**
 * Input data format for the add_tile use case.
 */
public class ClearTilesInputData {
    private final String playerName;

    public ClearTilesInputData(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
