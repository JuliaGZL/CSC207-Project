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

package view;

import javax.swing.Icon;
import javax.swing.JButton;
import mahjong.BaseTile;

/**
 * A TileButton is simply a JButton which with an extra property indicating the
 * associated Tile ID.
 */
public class TileButton extends JButton {
  private final BaseTile tileId;

  /**
   * Constructs a TileButton with the specified icon and tile ID.
   *
   * @param icon   the icon to be displayed on the button
   * @param tileId the tile ID associated with this button
   */
  public TileButton(Icon icon, BaseTile tileId) {
    super(icon);
    this.tileId = tileId;
  }

  /**
   * Retrieves the tile identifier associated with this TileButton.
   *
   * @return the tile identifier of type BaseTile
   */
  public BaseTile getTileId() {
    return tileId;
  }
}
