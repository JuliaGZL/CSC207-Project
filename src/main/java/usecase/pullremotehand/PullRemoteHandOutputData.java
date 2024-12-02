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

package usecase.pullremotehand;

import java.util.List;
import mahjong.BaseTile;

/**
 * Output data for the pull hand from discord use case.
 */
public class PullRemoteHandOutputData {
  private final String playerName;
  private final List<BaseTile> playerHand;
  private final List<String> nameList;
  private final List<String> iconList;

  /**
   * Constructs a PullRemoteHandOutputData object.
   *
   * @param playerName the name of the player
   * @param playerHand the hand of the player
   * @param nameList   the list of names
   * @param iconList   the list of icons
   */
  public PullRemoteHandOutputData(String playerName, List<BaseTile> playerHand,
      List<String> nameList,
      List<String> iconList) {
    this.playerName = playerName;
    this.playerHand = playerHand;
    this.nameList = nameList;
    this.iconList = iconList;
  }

  public String getPlayerName() {
    return playerName;
  }

  public List<BaseTile> getPlayerHand() {
    return playerHand;
  }

  public List<String> getNameList() {
    return nameList;
  }

  public List<String> getIconList() {
    return iconList;
  }
}
