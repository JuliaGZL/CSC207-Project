package interface_adapter.edit_tiles;

public class TileSelectorState {
    // This determines whether clicking on a tile will add to hand or to dora.
    private String target;

    // Player to insert tiles
    private  String playerName;

    public TileSelectorState() {

    }

    public TileSelectorState(String target, String playerName) {
        this.target = target;
        this.playerName = playerName;
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
}
