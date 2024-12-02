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

package usecase.removetile;

import mahjong.BaseTile;

/**
 * Input data format for the remove_tile use case.
 */
public class RemoveTileInputData {
  private final BaseTile tileId;
  private final String playerName;

  /**
   * Constructs a RemoveTileInputData object.
   *
   * @param tileId     the ID of the tile to be removed
   * @param playerName the name of the player requesting the tile removal
   */
  public RemoveTileInputData(BaseTile tileId, String playerName) {
    this.tileId = tileId;
    this.playerName = playerName;
  }

  /**
   * Gets the name of the player requesting the tile removal.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Gets the ID of the tile to be removed.
   *
   * @return the tile ID
   */
  public BaseTile getTileId() {
    return tileId;
  }
}
