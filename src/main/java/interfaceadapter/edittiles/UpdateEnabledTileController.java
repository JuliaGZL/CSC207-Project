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

import usecase.updateenabledtiles.UpdateEnabledTileInputData;
import usecase.updateenabledtiles.UpdateEnabledTilesInputBoundary;

/**
 * Controller for updating enabled tiles.
 */
public class UpdateEnabledTileController {
  private final UpdateEnabledTilesInputBoundary interactor;

  /**
   * Constructs an UpdateEnabledTileController with the given interactor.
   *
   * @param interactor the interactor to handle the update enabled tile use case
   */
  public UpdateEnabledTileController(UpdateEnabledTilesInputBoundary interactor) {
    this.interactor = interactor;
  }

  /**
   * Executes the update enabled tile use case.
   *
   * @param playerName the name of the player
   * @param target     the target tile to be updated
   */
  public void execute(String playerName, String target) {
    interactor.execute(new UpdateEnabledTileInputData(playerName, target));
  }
}
