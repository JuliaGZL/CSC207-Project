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
import usecase.removetile.RemoveTileInputBoundary;
import usecase.removetile.RemoveTileInputData;

/**
 * Controller for removing a tile from the game.
 */
public class RemoveTileController {
  private final RemoveTileInputBoundary interactor;

  /**
   * Constructs a RemoveTileController with the given interactor.
   *
   * @param interactor the interactor to handle the remove tile use case
   */
  public RemoveTileController(RemoveTileInputBoundary interactor) {
    this.interactor = interactor;
  }

  /**
   * Executes the remove tile use case.
   *
   * @param tileId     the tile to be removed
   * @param playerName the name of the player removing the tile
   */
  public void execute(BaseTile tileId, String playerName) {
    interactor.execute(new RemoveTileInputData(tileId, playerName));
  }
}
