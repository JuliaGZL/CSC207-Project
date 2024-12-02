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

package interfaceadapter.readhand;

/**
 * The state information about reading the hand.
 */
public class ReadHandState {
  private String playerName = "default";
  private String handInfo = "";

  public String getHandInfo() {
    return handInfo;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setHandInfo(String handInfo) {
    this.handInfo = handInfo;
  }

  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }
}
