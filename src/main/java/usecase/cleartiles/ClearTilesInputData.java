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

package usecase.cleartiles;

/**
 * Input data format for the clear_tiles use case.
 */
public class ClearTilesInputData {
  private final String playerName;

  /**
   * Constructs a new ClearTilesInputData instance.
   *
   * @param playerName the name of the player
   */
  public ClearTilesInputData(String playerName) {
    this.playerName = playerName;
  }

  /**
   * Gets the name of the player.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }
}
