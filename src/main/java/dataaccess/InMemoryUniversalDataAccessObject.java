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

package dataaccess;

import entity.Player;
import java.util.HashMap;
import java.util.Map;
import usecase.addtile.AddTileDataAccessInterface;
import usecase.changeplayer.ChangePlayerDataAccessInterface;
import usecase.cleartiles.ClearTilesDataAccessInterface;
import usecase.editstatus.EditStatusDataAccessInterface;
import usecase.husolver.HuSolverDataAccessInterface;
import usecase.readhand.ReadHandDataAccessInterface;
import usecase.removetile.RemoveTileDataAccessInterface;
import usecase.updateenabledtiles.UpdateEnabledTileDataAccessInterface;

/**
 * Universal player data storage object for our project.
 * This class implements multiple data access interfaces to provide
 * a unified in-memory storage solution for player data.
 */
public class InMemoryUniversalDataAccessObject implements AddTileDataAccessInterface,
  RemoveTileDataAccessInterface,
  ClearTilesDataAccessInterface,
  UpdateEnabledTileDataAccessInterface,
  ChangePlayerDataAccessInterface,
  EditStatusDataAccessInterface,
  HuSolverDataAccessInterface,
  ReadHandDataAccessInterface {
  private final Map<String, Player> players = new HashMap<String, Player>();
  
  /**
   * Constructs a new InMemoryUniversalDataAccessObject.
   */
  public InMemoryUniversalDataAccessObject() {
  // Constructor
  }

  /**
   * Return whether a player exists.
   *
   * @param name name of the player
   * @return true if the player exists
   */
  @Override
  public boolean existsByName(String name) {
  return players.containsKey(name);
  }

  /**
   * Get a player by name.
   *
   * @param name name of the player.
   * @return the corresponding Player object.
   */
  @Override
  public Player getPlayer(String name) {
  return players.get(name);
  }

  /**
   * Save a player. Append it into the storage if it wasn't already in, otherwise
   * update it.
   *
   * @param player the player to insert or update.
   */
  @Override
  public void savePlayer(Player player) {
  players.put(player.getName(), player);
  }

  /**
   * Updates the system to record this player's gameplay attributes.
   *
   * @param player  the player whose gameplay attributes are to be updated
   * @param newAttributes a boolean array of length 9 that are the new attributes
   *      to be set
   * @throws IllegalArgumentException if the length of newAttributes is not 9
   */
  @Override
  public void changeAttributes(Player player, Boolean[] newAttributes) {
  if (newAttributes.length != 9) {
    throw new IllegalArgumentException("The length of the new attributes must be 9.");
  }
  player.setAttributes(newAttributes);
  }
}
