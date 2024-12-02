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

package interfaceadapter.displaytiles;

import java.util.List;
import mahjong.BaseTile;

/**
 * State for property change listening for tile list display panels (both hand
 * and dora).
 */
public class TilesDisplayState {
  private List<BaseTile> idList;
  private List<String> nameList;
  private List<String> iconList;
  private String playerName;
  private String errorMsg;

  /**
   * Default constructor.
   */
  public TilesDisplayState() {

  }

  /**
   * Constructor with parameters.
   *
   * @param idList   List of tile IDs.
   * @param nameList List of tile names.
   * @param iconList List of tile icons.
   */
  public TilesDisplayState(List<BaseTile> idList, List<String> nameList, List<String> iconList) {
    this.idList = idList;
    this.nameList = nameList;
    this.iconList = iconList;
  }

  /**
   * Gets the list of tile IDs.
   *
   * @return List of tile IDs.
   */
  public List<BaseTile> getIdList() {
    return idList;
  }

  /**
   * Sets the list of tile IDs.
   *
   * @param idList List of tile IDs.
   */
  public void setIdList(List<BaseTile> idList) {
    this.idList = idList;
  }

  /**
   * Gets the list of tile names.
   *
   * @return List of tile names.
   */
  public List<String> getNameList() {
    return nameList;
  }

  /**
   * Sets the list of tile names.
   *
   * @param nameList List of tile names.
   */
  public void setNameList(List<String> nameList) {
    this.nameList = nameList;
  }

  /**
   * Gets the list of tile icons.
   *
   * @return List of tile icons.
   */
  public List<String> getIconList() {
    return iconList;
  }

  /**
   * Sets the list of tile icons.
   *
   * @param iconList List of tile icons.
   */
  public void setIconList(List<String> iconList) {
    this.iconList = iconList;
  }

  /**
   * Gets the player name.
   *
   * @return Player name.
   */
  public String getPlayerName() {
    return playerName;
  }

  /**
   * Sets the player name.
   *
   * @param playerName Player name.
   */
  public void setPlayerName(String playerName) {
    this.playerName = playerName;
  }

  /**
   * Gets the error message.
   *
   * @return Error message.
   */
  public String getErrorMsg() {
    return errorMsg;
  }

  /**
   * Sets the error message.
   *
   * @param errorMsg Error message.
   */
  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }
}
