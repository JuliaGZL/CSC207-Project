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

package usecase.updateenabledtiles;

import java.util.Set;
import mahjong.BaseTile;

/**
 * Output data structure for update enabled tile use case.
 */
public class UpdateEnabledTileOutputData {
  private final Set<BaseTile> enabledTiles;

  /**
   * Constructs an UpdateEnabledTileOutputData with the specified enabled tiles.
   *
   * @param enabledTiles the set of enabled tiles
   */
  public UpdateEnabledTileOutputData(Set<BaseTile> enabledTiles) {
    this.enabledTiles = enabledTiles;
  }

  /**
   * Returns the set of enabled tiles.
   *
   * @return the set of enabled tiles
   */
  public Set<BaseTile> getEnabledTiles() {
    return enabledTiles;
  }
}
