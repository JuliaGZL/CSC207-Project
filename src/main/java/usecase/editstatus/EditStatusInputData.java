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

package usecase.editstatus;

/**
 * The input data for the Edit Status Use Case.
 */
public class EditStatusInputData {
  private String playerName;
  private String winType = "Tsumo";
  private String roundWind = "East";
  private String seatWind = "East";
  private int numAkadora = 0;
  private final Boolean[] attributes; // Attributes of the hand in this round

  /**
   * Constructs an EditStatusInputData object with the specified attributes.
   *
   * @param attributes the attributes of the hand in this round
   * @param numAkadora the number of Akadora
   * @param seatWind   the seat wind
   * @param roundWind  the round wind
   * @param winType    the type of win
   * @param playerName the name of the player
   */
  public EditStatusInputData(
      Boolean[] attributes, int numAkadora,
      String seatWind, String roundWind, String winType,
      String playerName) {
    this.attributes = attributes;
    this.numAkadora = numAkadora;
    this.seatWind = seatWind;
    this.roundWind = roundWind;
    this.winType = winType;
    this.playerName = playerName;
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
   * Gets the type of win.
   *
   * @return the win type
   */
  public String getWinType() {
    return winType;
  }

  /**
   * Gets the round wind.
   *
   * @return the round wind
   */
  public String getRoundWind() {
    return roundWind;
  }

  /**
   * Gets the seat wind.
   *
   * @return the seat wind
   */
  public String getSeatWind() {
    return seatWind;
  }

  /**
   * Gets the number of Akadora.
   *
   * @return the number of Akadora
   */
  public int getNumAkadora() {
    return numAkadora;
  }

  /**
   * Gets the attributes of the hand in this round.
   *
   * @return the attributes of the hand
   */
  public Boolean[] getAttributes() {
    return attributes;
  }
}
