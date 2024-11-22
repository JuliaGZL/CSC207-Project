package interface_adapter.edit_tiles;

import mahjong.BaseTile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The state information representing the dora indicator selections.
 */
public class SelectDoraState {
    private Boolean[][] indicatorSelections = new Boolean[4][9];

    private String selectIndicatorError;

    // Getters and setters
    public Boolean[][] getIndicatorSelections() {
        return indicatorSelections;
    }

    public String getSelectIndicatorError() {
        return selectIndicatorError;
    }

    public void setIndicatorSelections(Boolean[][] indicatorSelections) {
        this.indicatorSelections = indicatorSelections;
    }

    public void setIndicatorSelections(Map<BaseTile, Integer> doraCounts) {
        // Initialize all selections to false
        indicatorSelections = new Boolean[4][9];

        // Set the selections to true for the dora indicators
        for (BaseTile tile : doraCounts.keySet()) {
            int index = tile.ordinal();
            int row = index / 9;
            int col = index % 9;
            indicatorSelections[row][col] = true;
        }
    }

    public void setSelectIndicatorError(String selectIndicatorError) {
        this.selectIndicatorError = selectIndicatorError;
    }

    @Override
    public String toString() {
        List<BaseTile> selectedTiles = new ArrayList<>();

        for (int i = 0; i < indicatorSelections.length; i++) {
            for (int j = 0; j < indicatorSelections[i].length; j++) {
                if (indicatorSelections[i][j]) {
                    selectedTiles.add(BaseTile.values()[i * 9 + j]);
                }
            }
        }

        return "SelectDoraState{" +
                "doraSelections=" + selectedTiles +
                '}';
    }
}
