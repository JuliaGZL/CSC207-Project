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

import mahjong.BaseTile;
import usecase.addtile.AddTileInputBoundary;
import usecase.addtile.AddTileInputData;

/**
 * Controller for the add_tile use case.
 */
public class AddTileController {
  private final AddTileInputBoundary addTileInteractor;

  /**
   * Constructs an AddTileController with the specified interactor.
   *
   * @param addTileInteractor the interactor to handle the add tile use case
   */
  public AddTileController(AddTileInputBoundary addTileInteractor) {
    this.addTileInteractor = addTileInteractor;
  }

  /**
   * Executes the add tile use case with the given tile and player name.
   *
   * @param tileId     the tile to be added
   * @param playerName the name of the player adding the tile
   */
  public void execute(BaseTile tileId, String playerName) {
    final AddTileInputData inputData = new AddTileInputData(tileId, playerName);
    addTileInteractor.execute(inputData);
  }
}
