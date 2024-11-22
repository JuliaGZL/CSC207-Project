package usecase.clear_tiles;

/**
 * Output data format for the add_tile use case.
 */
public class ClearTilesOutputData {
    private final boolean failed;
    private final String playerName;

    public ClearTilesOutputData(boolean failed, String playerName) {
        this.failed = failed;
        this.playerName = playerName;
    }

    public boolean isFailed() {
        return this.failed;
    }

    public String getPlayerName() {
        return playerName;
    }
}
