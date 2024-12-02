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

import entity.Player;

/**
 * DAI for the add tile use case.
 */
public interface ClearTilesDataAccessInterface {
  /**
   * Checks if a player with the given name exists.
   *
   * @param playerName the name to look for
   * @return true if a player with the given name exists; false otherwise
   */
  boolean existsByName(String playerName);

  /**
   * Saves the player.
   *
   * @param player the player to save
   */
  void savePlayer(Player player);

  /**
   * Returns the player with the given name.
   *
   * @param playerName the username to look up
   * @return the user with the given username
   */
  Player getPlayer(String playerName);
}
