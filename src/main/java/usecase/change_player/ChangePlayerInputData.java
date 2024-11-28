package usecase.change_player;

/**
 * Input data format for the change player use case.
 */
public class ChangePlayerInputData {
    private final String playerName;

    public ChangePlayerInputData(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
