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

package interfaceadapter.edittiles;

import interfaceadapter.ViewModel;
import mahjong.BaseTile;

/**
 * ViewModel for selecting tiles in the tile selector interface.
 */
public class TileSelectorViewModel extends ViewModel<TileSelectorState> {
  // Tiles to display defines here.
  /**
   * Man tiles row.
   */
  private static final BaseTile[] tilesRow1 = {
      BaseTile._1m, BaseTile._2m, BaseTile._3m,
      BaseTile._4m, BaseTile._5m, BaseTile._6m,
      BaseTile._7m, BaseTile._8m, BaseTile._9m,
  };
  /**
   * Pin tiles row.
   */
  private static final BaseTile[] tilesRow2 = {
      BaseTile._1p, BaseTile._2p, BaseTile._3p,
      BaseTile._4p, BaseTile._5p, BaseTile._6p,
      BaseTile._7p, BaseTile._8p, BaseTile._9p,
  };
  /**
   * Sou tiles row.
   */
  private static final BaseTile[] tilesRow3 = {
      BaseTile._1s, BaseTile._2s, BaseTile._3s,
      BaseTile._4s, BaseTile._5s, BaseTile._6s,
      BaseTile._7s, BaseTile._8s, BaseTile._9s,
  };
  /**
   * Wind tiles and dragons row.
   */
  private static final BaseTile[] tilesRow4 = {
      BaseTile.windTon, BaseTile.windNan, BaseTile.windShaa, BaseTile.windPei,
      BaseTile._5z, BaseTile._6z, BaseTile._7z
  };

  /**
   * Array of all tile rows.
   */
  public static final BaseTile[][] tileRows = {
      tilesRow1, tilesRow2, tilesRow3, tilesRow4
  };

  /**
   * Width of each tile.
   */
  public static final int TILE_WIDTH = 60;
  /**
   * Height of each tile.
   */
  public static final int TILE_HEIGHT = 80;

  /**
   * Constructs a TileSelectorViewModel with the default state.
   */
  public TileSelectorViewModel() {
    super("tile selector");
    setState(new TileSelectorState());
  }
}
