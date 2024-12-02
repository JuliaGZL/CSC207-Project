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

import entity.Player;

/**
 * The output data for the edit status use case.
 */
public class EditStatusOutputData {
  private String playerName;

  private String winType;
  private String roundWind;
  private String seatWind;
  private int numAkadora;
  private final Boolean[] attributes;

  private final boolean useCaseFailed;

  // TODO: Should I keep these?
  private int doraCount = 0;

  /**
   * Constructs an EditStatusOutputData object with the specified parameters.
   *
   * @param attributes the attributes of the player
   * @param numAkadora the number of Akadora
   * @param seatWind   the seat wind
   * @param roundWind  the round wind
   * @param winType    the win type
   * @param playerName the name of the player
   */
  public EditStatusOutputData(
      Boolean[] attributes, int numAkadora,
      String seatWind, String roundWind, String winType,
      String playerName) {
    this.attributes = attributes;
    this.numAkadora = numAkadora;
    this.seatWind = seatWind;
    this.roundWind = roundWind;
    this.winType = winType;
    this.playerName = playerName;
    this.useCaseFailed = false; // This use case never fails.
  }

  /**
   * Constructs an EditStatusOutputData object from a Player object.
   *
   * @param player the player object
   */
  public EditStatusOutputData(Player player) {
    this.attributes = player.getAttributes();
    this.numAkadora = player.getNumAkaDora();
    this.seatWind = player.getSeatWind();
    this.roundWind = player.getRoundWind();
    this.winType = player.getWinType();
    this.playerName = player.getName();
    this.useCaseFailed = false; // This use case never fails.
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
   * Gets the win type.
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
   * Gets the attributes of the player.
   *
   * @return the attributes of the player
   */
  public Boolean[] getAttributes() {
    return attributes;
  }

  /**
   * Checks if the use case failed.
   *
   * @return false, as this use case never fails
   */
  public boolean isUseCaseFailed() {
    return useCaseFailed;
  }

  /**
   * Gets the Dora counts.
   *
   * @return null, as this method is not implemented
   */
  public Boolean[][] getDoraCounts() {
    return null;
  }
}
