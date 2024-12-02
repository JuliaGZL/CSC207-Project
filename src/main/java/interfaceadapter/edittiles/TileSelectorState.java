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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import mahjong.BaseTile;

/**
 * Represents the state of the tile selector in the game.
 */
public class TileSelectorState {
  // Message for "inform selector" use case.
  private String message;

  // Player to insert tiles
  private String playerName = "default";

  // Only tiles in this set will be enabled for selection.
  private Set<BaseTile> enabledTiles = new HashSet<BaseTile>(List.of(BaseTile.values()));

  /**
   * Constructs a new TileSelectorState with default values.
   */
  public TileSelectorState() {

  }

  /**
   * Gets the message for the tile selector.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message for the tile selector.
   *
   * @param message the message to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Gets the name of the player.
   *
   * @return the player name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Sets the name of the player.
   *
   * @param playerName the player name to set
   */
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  /**
   * Gets the set of enabled tiles for selection.
   *
   * @return the set of enabled tiles
   */
  public Set<BaseTile> getEnabledTiles() {
    return enabledTiles;
  }

  /**
   * Sets the set of enabled tiles for selection.
   *
   * @param enabledTiles the set of enabled tiles to set
   */
  public void setEnabledTiles(Set<BaseTile> enabledTiles) {
    this.enabledTiles = enabledTiles;
  }
}
