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

import java.util.List;
import mahjong.BaseTile;

/**
 * Output data format for the add_tile use case.
 */
public class AddTileOutputData {
  private final boolean failed;
  private final String playerName;
  private final List<BaseTile> idList;
  private final List<String> nameList;
  private final List<String> iconList;

  /**
   * Constructs an AddTileOutputData object.
   *
   * @param failed     whether the operation failed
   * @param playerName the name of the player
   * @param idList     the list of tile IDs
   * @param nameList   the list of tile names
   * @param iconList   the list of tile icons
   */
  public AddTileOutputData(
      boolean failed, String playerName,
      List<BaseTile> idList, List<String> nameList,
      List<String> iconList) {
    this.failed = failed;
    this.playerName = playerName;
    this.idList = idList;
    this.nameList = nameList;
    this.iconList = iconList;
  }

  public boolean isFailed() {
    return this.failed;
  }

  public String getPlayerName() {
    return playerName;
  }

  public List<BaseTile> getIdList() {
    return idList;
  }

  public List<String> getNameList() {
    return nameList;
  }

  public List<String> getIconList() {
    return iconList;
  }
}
