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

package interfaceadapter.editstatus;

import mahjong.BaseTile;

/**
 * The state information representing the statuses being edited.
 */
public class EditStatusState {

  private BaseTile[] doraIndicators = new BaseTile[5];
  private String selectIndicatorError;
  private String tileType;

  private String winType = "Tsumo";
  private String seatWind = "East";
  private String roundWind = "East";
  private int numAkadora = 0;
  private Boolean[] attributes = { false, false, false, false, false, false, false, false, false };

  // Setters and getters

  /**
   * Gets the tile type.
   *
   * @return the tile type
   */
  public String getTileType() {
    return tileType;
  }

  /**
   * Sets the tile type.
   *
   * @param tileType the new tile type
   */
  public void setTileType(String tileType) {
    this.tileType = tileType;
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
   * Checks if the win type is Tsumo.
   *
   * @return true if the win type is Tsumo, false otherwise
   */
  public boolean isTsumo() {
    return winType.equals("Tsumo");
  }

  /**
   * Checks if the win type is Ron.
   *
   * @return true if the win type is Ron, false otherwise
   */
  public boolean isRon() {
    return winType.equals("Ron");
  }

  /**
   * Sets the win type.
   *
   * @param winType the new win type
   */
  public void setWinType(String winType) {
    this.winType = winType;
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
   * Sets the seat wind.
   *
   * @param seatWind the new seat wind
   */
  public void setSeatWind(String seatWind) {
    this.seatWind = seatWind;
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
   * Gets the attributes.
   *
   * @return the attributes
   */
  public Boolean[] getAttributes() {
    return attributes;
  }

  /**
   * Gets the dora indicators.
   *
   * @return the dora indicators
   */
  public BaseTile[] getDoraIndicators() {
    return doraIndicators;
  }

  /**
   * Gets the select indicator error.
   *
   * @return the select indicator error
   */
  public String getSelectIndicatorError() {
    return selectIndicatorError;
  }

  /**
   * Gets the number of Akadora.
   *
   * @return the number of Akadora
   */
  public int getNumAkadora() {
    return numAkadora;
  }

  // Setters

  /**
   * Sets the attributes.
   *
   * @param attributes the new attributes
   */
  public void setAttributes(Boolean[] attributes) {
    this.attributes = attributes;
  }

  /**
   * Sets the dora indicators.
   *
   * @param doraIndicators the new dora indicators
   */
  public void setDoraIndicators(BaseTile[] doraIndicators) {
    this.doraIndicators = doraIndicators;
  }

  /**
   * Sets the select indicator error.
   *
   * @param selectIndicatorError the new select indicator error
   */
  public void setSelectIndicatorError(String selectIndicatorError) {
    this.selectIndicatorError = selectIndicatorError;
  }

  /**
   * Sets the round wind.
   *
   * @param roundWind the new round wind
   */
  public void setRoundWind(String roundWind) {
    this.roundWind = roundWind;
  }

  /**
   * Sets the number of Akadora.
   *
   * @param numAkadora the new number of Akadora
   */
  public void setNumAkadora(int numAkadora) {
    this.numAkadora = numAkadora;
  }

  /**
   * Gets the attribute string.
   *
   * @return the attribute string
   */
  public String getAttributeString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int i = 0; i < attributes.length; i++) {
      if (attributes[i]) {
        sb.append(EditStatusViewModel.ATTRIBUTES[i]);
        sb.append(", ");
      }
    }
    sb.append("}");
    return sb.toString();
  }

  /**
   * Returns a string representation of the object.
   *
   * @return a string representation of the object
   */
  @Override
  public String toString() {
    System.out.println(getAttributeString());
    return "EditStatusState{"
        + "selectedTileType=" + tileType
        + ", selectedWinType=" + winType
        + ", seatWind=" + seatWind
        + ", roundWind=" + roundWind
        + ", numAkadora=" + numAkadora
        + ", attributes=" + getAttributeString()
        + '}';
  }
}
