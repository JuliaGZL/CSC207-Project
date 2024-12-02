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
 * Represents the input data required to read a player's hand.
 */
public class ReadHandInputData {
  private final String playerName;
  private final String[] attributeNames;

  /**
   * Constructs a new ReadHandInputData instance.
   *
   * @param playerName     the name of the player
   * @param attributeNames the names of the attributes
   */
  public ReadHandInputData(String playerName, String[] attributeNames) {
    this.playerName = playerName;
    this.attributeNames = attributeNames;
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
   * Gets the names of the attributes.
   *
   * @return an array of attribute names
   */
  public String[] getAttributeNames() {
    return attributeNames;
  }
}
