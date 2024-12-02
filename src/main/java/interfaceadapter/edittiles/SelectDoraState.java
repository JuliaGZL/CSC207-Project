/*
 Copyright (c) 2024 YAizhou, li-tianchu, JuliaGZL, TurkeyBilly

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package interfaceadapter.edittiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mahjong.BaseTile;

/**
 * The state information representing the dora indicator selections.
 */
public class SelectDoraState {
  private Boolean[][] indicatorSelections = new Boolean[4][9];

  private String selectIndicatorError;

  /**
   * Gets the current dora indicator selections.
   *
   * @return a 2D array representing the dora indicator selections.
   */
  public Boolean[][] getIndicatorSelections() {
    return indicatorSelections;
  }

  /**
   * Gets the current error message related to dora indicator selection.
   *
   * @return the error message as a String.
   */
  public String getSelectIndicatorError() {
    return selectIndicatorError;
  }

  /**
   * Sets the dora indicator selections.
   *
   * @param indicatorSelections a 2D array representing the new dora indicator
   *                            selections.
   */
  public void setIndicatorSelections(Boolean[][] indicatorSelections) {
    this.indicatorSelections = indicatorSelections;
  }

  /**
   * Sets the dora indicator selections based on the provided dora counts.
   *
   * @param doraCounts a map containing the dora counts.
   */
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

  /**
   * Sets the error message related to dora indicator selection.
   *
   * @param selectIndicatorError the error message as a String.
   */
  public void setSelectIndicatorError(String selectIndicatorError) {
    this.selectIndicatorError = selectIndicatorError;
  }

  /**
   * Returns a string representation of the current dora indicator selections.
   *
   * @return a string representation of the current dora indicator selections.
   */
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

    return "SelectDoraState{"
        + "doraSelections=" + selectedTiles
        + '}';
  }
}
