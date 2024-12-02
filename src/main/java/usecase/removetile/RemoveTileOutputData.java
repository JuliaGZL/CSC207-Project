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

import java.util.List;
import mahjong.BaseTile;

/**
 * Output data format for the remove_tile use case.
 */
public class RemoveTileOutputData {
  private final boolean failed;
  private final String playerName;
  private final List<BaseTile> idList;
  private final List<String> nameList;
  private final List<String> iconList;

  /**
   * Constructs a RemoveTileOutputData object.
   *
   * @param failed     whether the operation failed
   * @param playerName the name of the player
   * @param idList     the list of tile IDs
   * @param nameList   the list of tile names
   * @param iconList   the list of tile icons
   */
  public RemoveTileOutputData(
      boolean failed, String playerName,
      List<BaseTile> idList, List<String> nameList, List<String> iconList) {
    this.failed = failed;
    this.playerName = playerName;
    this.idList = idList;
    this.nameList = nameList;
    this.iconList = iconList;
  }

  /**
   * Returns whether the operation failed.
   *
   * @return true if the operation failed, false otherwise
   */
  public boolean isFailed() {
    return this.failed;
  }

  /**
   * Returns the name of the player.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Returns the list of tile IDs.
   *
   * @return the list of tile IDs
   */
  public List<BaseTile> getIdList() {
    return idList;
  }

  /**
   * Returns the list of tile names.
   *
   * @return the list of tile names
   */
  public List<String> getNameList() {
    return nameList;
  }

  /**
   * Returns the list of tile icons.
   *
   * @return the list of tile icons
   */
  public List<String> getIconList() {
    return iconList;
  }
}
