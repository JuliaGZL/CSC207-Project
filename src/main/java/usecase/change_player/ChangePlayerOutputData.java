package usecase.change_player;

/**
 * Output data format for the change player use case.
 */
public class ChangePlayerOutputData {
    private final String playerName;
    private final int score;

    public ChangePlayerOutputData(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }
}
