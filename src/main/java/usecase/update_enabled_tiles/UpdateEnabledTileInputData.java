package usecase.update_enabled_tiles;

/**
 * Input data structure for update enabled tile use case.
 */
public class UpdateEnabledTileInputData {
    private final String playerName;
    private final String target;

    public UpdateEnabledTileInputData(String playerName, String target) {
        this.playerName = playerName;
        this.target = target;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getTarget() {
        return target;
    }
}
