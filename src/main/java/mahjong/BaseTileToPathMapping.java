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

package mahjong;

import java.util.HashMap;
import java.util.Map;

/**
 * This class provides a mapping from BaseTile to their corresponding image
 * paths.
 */
public class BaseTileToPathMapping {
  // Blank or disabled tiles
  public static final String BLANK_TILE = "/mahjong-tiles/Front.png";

  private static final Map<BaseTile, String> BASE_TILES_STRING_MAP = new HashMap<>();

  static {
    // Mappings for Man tiles
    BASE_TILES_STRING_MAP.put(BaseTile._1m, "/mahjong-tiles/Man1.png");
    BASE_TILES_STRING_MAP.put(BaseTile._2m, "/mahjong-tiles/Man2.png");
    BASE_TILES_STRING_MAP.put(BaseTile._3m, "/mahjong-tiles/Man3.png");
    BASE_TILES_STRING_MAP.put(BaseTile._4m, "/mahjong-tiles/Man4.png");
    BASE_TILES_STRING_MAP.put(BaseTile._5m, "/mahjong-tiles/Man5.png");
    BASE_TILES_STRING_MAP.put(BaseTile._6m, "/mahjong-tiles/Man6.png");
    BASE_TILES_STRING_MAP.put(BaseTile._7m, "/mahjong-tiles/Man7.png");
    BASE_TILES_STRING_MAP.put(BaseTile._8m, "/mahjong-tiles/Man8.png");
    BASE_TILES_STRING_MAP.put(BaseTile._9m, "/mahjong-tiles/Man9.png");

    // Mappings for Pin tiles
    BASE_TILES_STRING_MAP.put(BaseTile._1p, "/mahjong-tiles/Pin1.png");
    BASE_TILES_STRING_MAP.put(BaseTile._2p, "/mahjong-tiles/Pin2.png");
    BASE_TILES_STRING_MAP.put(BaseTile._3p, "/mahjong-tiles/Pin3.png");
    BASE_TILES_STRING_MAP.put(BaseTile._4p, "/mahjong-tiles/Pin4.png");
    BASE_TILES_STRING_MAP.put(BaseTile._5p, "/mahjong-tiles/Pin5.png");
    BASE_TILES_STRING_MAP.put(BaseTile._6p, "/mahjong-tiles/Pin6.png");
    BASE_TILES_STRING_MAP.put(BaseTile._7p, "/mahjong-tiles/Pin7.png");
    BASE_TILES_STRING_MAP.put(BaseTile._8p, "/mahjong-tiles/Pin8.png");
    BASE_TILES_STRING_MAP.put(BaseTile._9p, "/mahjong-tiles/Pin9.png");

    // Mappings for Sou tiles
    BASE_TILES_STRING_MAP.put(BaseTile._1s, "/mahjong-tiles/Sou1.png");
    BASE_TILES_STRING_MAP.put(BaseTile._2s, "/mahjong-tiles/Sou2.png");
    BASE_TILES_STRING_MAP.put(BaseTile._3s, "/mahjong-tiles/Sou3.png");
    BASE_TILES_STRING_MAP.put(BaseTile._4s, "/mahjong-tiles/Sou4.png");
    BASE_TILES_STRING_MAP.put(BaseTile._5s, "/mahjong-tiles/Sou5.png");
    BASE_TILES_STRING_MAP.put(BaseTile._6s, "/mahjong-tiles/Sou6.png");
    BASE_TILES_STRING_MAP.put(BaseTile._7s, "/mahjong-tiles/Sou7.png");
    BASE_TILES_STRING_MAP.put(BaseTile._8s, "/mahjong-tiles/Sou8.png");
    BASE_TILES_STRING_MAP.put(BaseTile._9s, "/mahjong-tiles/Sou9.png");

    // Mappings for honor tiles
    BASE_TILES_STRING_MAP.put(BaseTile._1z, "/mahjong-tiles/Ton.png"); // East wind
    BASE_TILES_STRING_MAP.put(BaseTile._2z, "/mahjong-tiles/Nan.png"); // South wind
    BASE_TILES_STRING_MAP.put(BaseTile._3z, "/mahjong-tiles/Shaa.png"); // West wind
    BASE_TILES_STRING_MAP.put(BaseTile._4z, "/mahjong-tiles/Pei.png"); // North wind
    BASE_TILES_STRING_MAP.put(BaseTile._5z, "/mahjong-tiles/Haku.png"); // White dragon
    BASE_TILES_STRING_MAP.put(BaseTile._6z, "/mahjong-tiles/Hatsu.png"); // Green dragon
    BASE_TILES_STRING_MAP.put(BaseTile._7z, "/mahjong-tiles/Chun.png"); // Red dragon
  }

  /**
   * Returns the image path corresponding to the given BaseTile.
   *
   * @param baseTile the BaseTile for which the image path is to be retrieved
   * @return the image path corresponding to the given BaseTile
   */
  public static String getTilePath(BaseTile baseTile) {
    return BASE_TILES_STRING_MAP.get(baseTile);
  }
}
