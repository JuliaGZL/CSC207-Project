package interface_adapter.player_events;

/**
 * state for the player events panel.
 */
public class PlayerEventsState {
    private String playerName = "default";
    private int score = 0;

    public PlayerEventsState(){

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
}
