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

package usecase.changeplayer;

/**
 * Output data format for the change player use case.
 */
public class ChangePlayerOutputData {
  private final String playerName;
  private final int score;

  /**
   * Constructs a ChangePlayerOutputData instance.
   *
   * @param playerName the name of the player
   * @param score      the score of the player
   */
  public ChangePlayerOutputData(String playerName, int score) {
    this.playerName = playerName;
    this.score = score;
  }

  /**
   * Gets the name of the player.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Gets the score of the player.
   *
   * @return the player's score
   */
  public int getScore() {
    return score;
  }
}
