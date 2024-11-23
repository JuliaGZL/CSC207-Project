package interface_adapter.edit_tiles;

import mahjong.BaseTile;

import java.util.HashSet;
import java.util.Set;

public class TileSelectorState {
    // This determines whether clicking on a tile will add to hand or to dora/uradora.
    private String target = "hand";

    // Player to insert tiles
    private String playerName = "default";

    // Only tiles in this set will be enabled for selection.
    private Set<BaseTile> enabledTiles = new HashSet<BaseTile>();

    public TileSelectorState() {

    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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
