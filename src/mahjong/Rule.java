package mahjong;

import java.util.Arrays;
import java.util.List;

import utils.Algorithm;

/**
 * This class contains various rules and utility methods for Mahjong.
 */
public class Rule {

  private static final BaseTile[] shuntsuBadHead = {
      BaseTile._8m, BaseTile._9m,
      BaseTile._8p, BaseTile._9p,
      BaseTile._8s, BaseTile._9s,
      BaseTile._1z, BaseTile._2z,
      BaseTile._3z, BaseTile._4z,
      BaseTile._5z, BaseTile._6z,
      BaseTile._7z };

  /**
   * Checks if the given list of Yaku contains any valid Yaku for winning.
   *
   * @param yakus the list of Yaku to check
   * @return true if there is a valid Yaku for winning, false otherwise
   */
  public static boolean canAgari(List<Yaku> yakus) {
    for (Yaku yaku : yakus) {
      if (yaku != Yaku.None
          && yaku != Yaku.Dora
          && yaku != Yaku.Akadora
          && yaku != Yaku.Uradora
          && yaku != Yaku.Peidora) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the given string represents a Jihai tile.
   *
   * @param s the string to check
   * @return true if the string represents a Jihai tile, false otherwise
   */
  public static boolean isJihai(String s) {
    return s.charAt(1) == TileGroup.markJihai;
  }

  /**
   * Checks if the given string represents a Tai Yaochuuhai tile.
   *
   * @param s the string to check
   * @return true if the string represents a Tai Yaochuuhai tile, false otherwise
   */
  public static boolean isTaiYaochuuhai(String s) {
    if (isJihai(s)) {
      return false;
    }
    if (s.charAt(2) == TileGroup.markKoutsu || s.charAt(2) == TileGroup.markToitsu
        || s.charAt(2) == TileGroup.markKantsu) {
      return s.charAt(0) == '1' || s.charAt(0) == '9';
    }
    if (s.charAt(2) == TileGroup.markShuntsu) {
      return s.charAt(0) == '1' || s.charAt(0) == '7';
    }
    return false;
  }

  /**
   * Checks if the given string represents a Routouhai tile.
   *
   * @param s the string to check
   * @return true if the string represents a Routouhai tile, false otherwise
   */
  public static boolean isRoutouhai(String s) {
    if (isJihai(s)) {
      return false;
    }
    if (s.charAt(2) == TileGroup.markKoutsu || s.charAt(2) == TileGroup.markToitsu
        || s.charAt(2) == TileGroup.markKantsu) {
      return s.charAt(0) == '1' || s.charAt(0) == '9';
    }
    return false;
  }

  /**
   * Checks if the given string represents a Greenpai tile.
   *
   * @param s the string to check
   * @return true if the string represents a Greenpai tile, false otherwise
   */
  public static boolean isGreenpai(String s) {
    String[] greenTypes = new String[] { "2sK", "3sK", "4sK", "2sS", "6sK", "8sK", "6zK",
        "2s:", "3s:", "4s:", "6s:", "8s:", "6z:" };
    return Arrays.asList(greenTypes).contains(s);
  }

  /**
   * Checks if the given string represents a Tai Yaochuuhai or Jihai tile.
   *
   * @param s the string to check
   * @return true if the string represents a Tai Yaochuuhai or Jihai tile, false
   *         otherwise
   */
  public static boolean isTaiYaochuuhaiOrJihai(String s) {
    return isJihai(s) || isTaiYaochuuhai(s);
  }

  /**
   * Checks if the given string represents a Yakuhai Toitsu tile.
   *
   * @param s             the string to check
   * @param selfWind      the player's wind
   * @param prevalentWind the prevalent wind
   * @return the number of Yakuhai Toitsu cases
   */
  public static int isYakuhaiToitsu(String s, String selfWind, String prevalentWind) {
    if (s.charAt(2) != TileGroup.markToitsu) {
      return 0;
    }
    if (s.charAt(1) != TileGroup.markJihai) {
      return 0;
    }

    int cases = 0;
    if (s.equals(selfWind)) {
      cases++;
    }
    if (s.equals(selfWind)) {
      cases++;
    }
    if (s.charAt(0) - '1' >= 4) {
      cases++;
    }
    return cases;
  }

  /**
   * Checks if the given tile is a bad head for Shuntsu.
   *
   * @param tile the tile to check
   * @return true if the tile is a bad head for Shuntsu, false otherwise
   */
  public static boolean isShuntsuBadHead(BaseTile tile) {
    for (BaseTile t : shuntsuBadHead) {
      if (t == tile) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks if the given list of tiles forms a Kokushi shape.
   *
   * @param tiles the list of tiles to check
   * @return true if the tiles form a Kokushi shape, false otherwise
   */
  public static boolean isKokushiShape(List<BaseTile> tiles) {
    if (tiles.size() != 14) {
      return false;
    }
    BaseTile[] raw = { BaseTile._1m, BaseTile._9m, BaseTile._1p, BaseTile._9p, BaseTile._1s,
        BaseTile._9s, BaseTile._1z, BaseTile._2z, BaseTile._3z, BaseTile._4z, BaseTile._5z,
        BaseTile._6z, BaseTile._7z };
    for (int i = 0; i < 13; i++) {
      if (tiles.get(i) != raw[i]) {
        return false;
      }
    }
    return Algorithm.arrayContains(raw, tiles.get(13));
  }

  /**
   * Checks if the given list of tiles forms a 7 Toitsu shape.
   *
   * @param tiles the list of tiles to check
   * @return true if the tiles form a 7 Toitsu shape, false otherwise
   */
  public static boolean is7ToitsuShape(List<BaseTile> tiles) {
    if (tiles.size() != 14) {
      return false;
    }
    tiles.sort(null);
    if (tiles.get(0) == tiles.get(1)
        && tiles.get(1) != tiles.get(2)
        && tiles.get(2) == tiles.get(3)
        && tiles.get(3) != tiles.get(4)
        && tiles.get(4) == tiles.get(5)
        && tiles.get(5) != tiles.get(6)
        && tiles.get(6) == tiles.get(7)
        && tiles.get(7) != tiles.get(8)
        && tiles.get(8) == tiles.get(9)
        && tiles.get(9) != tiles.get(10)
        && tiles.get(10) == tiles.get(11)
        && tiles.get(11) != tiles.get(12)
        && tiles.get(12) == tiles.get(13)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the given list of tiles forms a Churen shape.
   *
   * @param tiles         the list of tiles to check
   * @param chinitsuColor the color of the tiles
   * @return true if the tiles form a Churen shape, false otherwise
   */
  public static boolean isChurenShape(List<BaseTile> tiles, int chinitsuColor) {
    if (tiles.size() != 14) {
      return false;
    }
    if (chinitsuColor == -1 || chinitsuColor == 3) {
      return false;
    }
    Integer[] pureChuren = { 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9 };

    for (int i = 0; i < 13; ++i) {
      if (tiles.get(i).ordinal() != (pureChuren[i] + 9 * chinitsuColor - 1)) {
        // 确定有序的前提下，逐张对比
        return false;
      }
    }
    // 通过所有判断
    int t = tiles.get(13).ordinal() + 1 - 9 * chinitsuColor;
    return Algorithm.arrayContains(pureChuren, t);
  }

  /**
   * Gets the color of the Chinitsu tiles.
   *
   * @param tiles the list of tiles to check
   * @return the color of the Chinitsu tiles, or -1 if the tiles are not of the
   *         same color
   */
  public static int getChinitsuColor(List<BaseTile> tiles) {
    int color = tiles.get(0).ordinal() / 9;
    for (BaseTile tile : tiles) {
      if (tile.ordinal() / 9 != color) {
        return -1;
      }
    }
    return color;
  }
}
