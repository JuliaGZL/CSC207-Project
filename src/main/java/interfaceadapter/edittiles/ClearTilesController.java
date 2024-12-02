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

import usecase.cleartiles.ClearTilesInputBoundary;
import usecase.cleartiles.ClearTilesInputData;

/**
 * Controller for clearing tiles.
 */
public class ClearTilesController {
  private final ClearTilesInputBoundary interactor;

  /**
   * Constructs a ClearTilesController with the given interactor.
   *
   * @param interactor the interactor to handle the clear tiles use case
   */
  public ClearTilesController(ClearTilesInputBoundary interactor) {
    this.interactor = interactor;
  }

  /**
   * Executes the clear tiles use case for the given player.
   *
   * @param playerName the name of the player
   */
  public void execute(String playerName) {
    interactor.execute(new ClearTilesInputData(playerName));
  }
}
