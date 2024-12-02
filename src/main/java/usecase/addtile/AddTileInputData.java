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

package usecase.addtile;

import mahjong.BaseTile;

/**
 * Input data format for the add_tile use case.
 */
public class AddTileInputData {
  private final BaseTile tileId;
  private final String playerName;

  /**
   * Constructs an AddTileInputData instance.
   *
   * @param tileId     the tile identifier
   * @param playerName the name of the player
   */
  public AddTileInputData(BaseTile tileId, String playerName) {
    this.tileId = tileId;
    this.playerName = playerName;
  }

  public String getPlayerName() {
    return playerName;
  }

  public BaseTile getTileId() {
    return tileId;
  }
}
