package interface_adapter.edit_tiles;

import mahjong.BaseTile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TileSelectorState {
    // Message for "inform selector" use case.
    private String message;

    // Player to insert tiles
    private String playerName = "default";

    // Only tiles in this set will be enabled for selection.
    private Set<BaseTile> enabledTiles = new HashSet<BaseTile>(List.of(BaseTile.values()));

    public TileSelectorState() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Set<BaseTile> getEnabledTiles() {
        return enabledTiles;
    }

    public void setEnabledTiles(Set<BaseTile> enabledTiles) {
        this.enabledTiles = enabledTiles;
    }
}
