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

package interfaceadapter.playerevents;

/**
 * State for the player events panel.
 */
public class PlayerEventsState {
  private String playerName = "default";
  private int score = 0;
  private String message = "";

  /**
   * Constructs a new PlayerEventsState with default values.
   */
  public PlayerEventsState() {

  }

  /**
   * Gets the player's name.
   *
   * @return the player's name
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Sets the player's name.
   *
   * @param playerName the new player's name
   */
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  /**
   * Gets the player's score.
   *
   * @return the player's score
   */
  public int getScore() {
    return score;
  }

  /**
   * Sets the player's score.
   *
   * @param score the new player's score
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * Gets the message.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Sets the message.
   *
   * @param message the new message
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
