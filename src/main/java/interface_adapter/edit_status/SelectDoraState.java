package interface_adapter.edit_status;

import mahjong.BaseTiles;

import java.util.ArrayList;
import java.util.List;

/**
 * The state information representing the dora indicator selections.
 */
public class SelectDoraState {
    private Boolean[][] doraSelections = new Boolean[4][9];

    // Getters and setters
    public Boolean[][] getDoraSelections() {
        return doraSelections;
    }

    public void setDoraSelections(Boolean[][] doraSelections) {
        this.doraSelections = doraSelections;
    }

    @Override
    public String toString() {
        List<BaseTiles> selectedTiles = new ArrayList<>();

        for (int i = 0; i < doraSelections.length; i++) {
            for (int j = 0; j < doraSelections[i].length; j++) {
                if (doraSelections[i][j]) {
                    selectedTiles.add(BaseTiles.values()[i * 9 + j]);
                }
            }
        }

        return "SelectDoraState{" +
                "doraSelections=" + selectedTiles +
                '}';
    }
}
