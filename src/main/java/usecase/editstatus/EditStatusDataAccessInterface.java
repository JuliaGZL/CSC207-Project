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

package usecase.editstatus;

import entity.Player;

/**
 * The interface of the DAO for the Edit Status Use Case.
 */
public interface EditStatusDataAccessInterface {

  /**
   * Updates the system to record this player's gameplay attributes.
   *
   * @param player   the player whose gameplay attributes are to be updated
   * @param attributes the new attributes of the player
   */
  void changeAttributes(Player player, Boolean[] attributes);

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
  //
  // /**
  // * Updates the system to record this user's red dora states.
  // * @param player the player whose red dora states are to be updated
  // */
  // void editRedDora(Player player);
  //
  // /**
  // * Updates the system to record this user's dora tiles.
  // * @param player the player whose dora tiles are to be updated
  // */
  // void editDora(Player player);
}
