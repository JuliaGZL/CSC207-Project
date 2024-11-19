package interface_adapter.edit_tiles;

public class TileSelectorState {
    // This determines whether clicking on a tile will add to hand or to dora.
    private String target;

    public TileSelectorState(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
