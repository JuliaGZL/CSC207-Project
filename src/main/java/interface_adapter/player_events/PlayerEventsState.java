package interface_adapter.player_events;

/**
 * state for the player events panel.
 */
public class PlayerEventsState {
    private String playerName = "default";
    private int score = 0;
    private String message = "";

    public PlayerEventsState() {

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
