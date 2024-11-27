package usecase.read_hand;

/**
 * Output data format for the read hand use case.
 */
public class ReadHandOutputData {
    private final String playerName;
    private final String handInfo;

    public ReadHandOutputData(String playerName, String handInfo) {
        this.playerName = playerName;
        this.handInfo = handInfo;
    }

    // Getters
    public String getPlayerName() {
        return playerName;
    }

    public String getHandInfo() {
        return handInfo;
    }
}
