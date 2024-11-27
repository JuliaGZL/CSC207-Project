package interface_adapter.read_hand;

import entity.Tile;

import java.util.List;

/**
 * The state information about reading the hand.
 */
public class ReadHandState {
    private String playerName = "default";
    private String handInfo = "";

    public String getHandInfo() {
        return handInfo;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setHandInfo(String handInfo) {
        this.handInfo = handInfo;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
