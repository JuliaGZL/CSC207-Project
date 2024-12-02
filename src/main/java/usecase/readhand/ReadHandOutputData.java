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

package usecase.readhand;

/**
 * Output data format for the read hand use case.
 */
public class ReadHandOutputData {
  private final String playerName;
  private final String handInfo;

  /**
   * Constructs a ReadHandOutputData object.
   *
   * @param playerName the name of the player
   * @param handInfo   the information about the hand
   */
  public ReadHandOutputData(String playerName, String handInfo) {
    this.playerName = playerName;
    this.handInfo = handInfo;
  }

  // Getters

  /**
   * Gets the name of the player.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Gets the information about the hand.
   *
   * @return the hand information
   */
  public String getHandInfo() {
    return handInfo;
  }
}
