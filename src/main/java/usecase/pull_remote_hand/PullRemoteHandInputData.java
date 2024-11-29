package usecase.pull_remote_hand;

/**
 * Input data for the pull hand from discord use case.
 */
public class PullRemoteHandInputData {
    private final String playerName;

    public PullRemoteHandInputData(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
