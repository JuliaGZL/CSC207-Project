package mahjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a group of tiles in Mahjong, which can be of type TOITSU (pair),
 * SHUNTSU (sequence), or KOUTSU (triplet).
 */
public class TileGroup implements Comparable<TileGroup> {
  /**
   * Enumeration of possible tile group types.
   */
  public enum Type {
    TOITSU, SHUNTSU, KOUTSU
  }

  /**
   * The type of this tile group.
   */
  private Type type;

  /**
   * The list of tiles in this group.
   */
  private List<BaseTile> tiles;

  /** Mark for Toitsu (pair) tile group. */
  static final char markToitsu = ':';
  /** Mark for Shuntsu (sequence) tile group. */
  static final char markShuntsu = 'S';
  /** Mark for Koutsu (triplet) tile group. */
  static final char markKoutsu = 'K';
  /** Mark for Kantsu (quad) tile group. */
  static final char markKantsu = '|';
  /** Mark for Ankan (concealed quad). */
  static final char markAnkan = '+';
  /** Mark for Minkan (open quad). */
  static final char markMinkan = '-';
  /** Mark for Fuuro (open meld). */
  static final char markFuuro = '-';
  /** Mark for first Tsumo (self-draw). */
  static final char markTsumo1st = '!';
  /** Mark for second Tsumo (self-draw). */
  static final char markTsumo2nd = '@';
  /** Mark for third Tsumo (self-draw). */
  static final char markTsumo3rd = '#';
  /** Mark for first Ron (discard win). */
  static final char markRon1st = '$';
  /** Mark for second Ron (discard win). */
  static final char markRon2nd = '%';
  /** Mark for third Ron (discard win). */
  static final char markRon3rd = '^';
  /** Mark for Manzu. */
  static final char markManzu = 'm';
  /** Mark for Pinzu. */
  static final char markPinzu = 'p';
  /** Mark for Souzu. */
  static final char markSouzu = 's';
  /** Mark for Jihai(honor tile). */
  static final char markJihai = 'z';

  /**
   * Creates a tile group representation with the given tile and mark.
   *
   * @param t    the tile
   * @param mark the mark character
   * @return a string representing the tile group
   */
  public static String makeTileGroup(BaseTile t, char mark) {
    String ret = new String(new char[] { t.toString().charAt(0), t.toString().charAt(1), mark });
    return ret;
  }

  /**
   * Creates a tile group representation with the given tile and two marks.
   *
   * @param t     the tile
   * @param mark1 the first mark character
   * @param mark2 the second mark character
   * @return a string representing the tile group
   */
  public static String makeTileGroup(BaseTile t, char mark1, char mark2) {
    String ret = new String(
        new char[] { t.toString().charAt(0), t.toString().charAt(1), mark1, mark2 }
    );
    return ret;
  }

  /**
   * Creates a Toitsu (pair) tile group.
   *
   * @param t the tile
   * @return a string representing the Toitsu tile group
   */
  public static String makeToitsu(BaseTile t) {
    return makeTileGroup(t, markToitsu);
  }

  /**
   * Creates a Shuntsu (sequence) tile group.
   *
   * @param t the tile
   * @return a string representing the Shuntsu tile group
   */
  public static String makeShuntsu(BaseTile t) {
    return makeTileGroup(t, markShuntsu);
  }

  /**
   * Creates a Shuntsu Fuuro (open sequence) tile group.
   *
   * @param t the tile
   * @return a string representing the Shuntsu Fuuro tile group
   */
  public static String makeShuntsuFuuro(BaseTile t) {
    return makeTileGroup(t, markShuntsu, markFuuro);
  }

  /**
   * Creates a Koutsu (triplet) tile group.
   *
   * @param t the tile
   * @return a string representing the Koutsu tile group
   */
  public static String makeKoutsu(BaseTile t) {
    return makeTileGroup(t, markKoutsu);
  }

  /**
   * Creates a Koutsu Fuuro (open triplet) tile group.
   *
   * @param t the tile
   * @return a string representing the Koutsu Fuuro tile group
   */
  public static String makeKoutsuFuuro(BaseTile t) {
    return makeTileGroup(t, markKoutsu, markFuuro);
  }

  /**
   * Creates an Ankan (concealed quad) tile group.
   *
   * @param t the tile
   * @return a string representing the Ankan tile group
   */
  public static String makeAnkan(BaseTile t) {
    return makeTileGroup(t, markKantsu, markAnkan);
  }

  /**
   * Creates a Minkan (open quad) tile group.
   *
   * @param t the tile
   * @return a string representing the Minkan tile group
   */
  public static String makeMinkan(BaseTile t) {
    return makeTileGroup(t, markKantsu, markMinkan);
  }

  /**
   * Constructs a TileGroup with the specified type.
   *
   * @param type the type of the tile group
   */
  public TileGroup(Type type) {
    this.type = type;
    this.tiles = new ArrayList<>();
  }

  /**
   * Sets the tiles of this group and sorts them.
   *
   * @param tiles the list of tiles to set
   */
  public void setTiles(List<BaseTile> tiles) {
    this.tiles = new ArrayList<>(tiles);
    this.sortTiles();
  }

  /**
   * Returns the list of tiles in this group.
   *
   * @return the list of tiles
   */
  public List<BaseTile> getTiles() {
    return tiles;
  }

  /**
   * Returns the type of this tile group.
   *
   * @return the type of the tile group
   */
  public Type getType() {
    return type;
  }

  /**
   * Sorts the tiles in this group.
   */
  public void sortTiles() {
    Collections.sort(tiles);
  }

  /**
   * Finds and returns a tile in the group that equals the specified tile.
   *
   * @param baseTile the tile to search for
   * @return the matching tile, or null if not found
   */
  public BaseTile find(BaseTile baseTile) {
    for (BaseTile tile : tiles) {
      if (tile.equals(baseTile)) {
        return tile;
      }
    }
    return null;
  }

  /**
   * Returns a string representation of the tile group.
   *
   * @return a string representation of the tile group
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("[");
    for (BaseTile tile : tiles) {
      sb.append(tile.toString());
    }
    sb.append("]");
    return sb.toString();
  }

  /**
   * Compares this TileGroup with another for order.
   *
   * @param other the TileGroup to compare with
   * @return a negative integer, zero, or a positive integer as this TileGroup
   *         is less than, equal to, or greater than the specified TileGroup
   */
  @Override
  public int compareTo(TileGroup other) {
    if (this.type.ordinal() < other.type.ordinal()) {
      return -1;
    }
    if (this.type.ordinal() > other.type.ordinal()) {
      return 1;
    }
    if (this.tiles.isEmpty() || other.tiles.isEmpty()) {
      return 0;
    }
    return this.tiles.get(0).compareTo(other.tiles.get(0));
  }

  /**
   * Indicates whether some other object is equal to this one.
   *
   * @param obj the reference object with which to compare
   * @return true if this object is the same as the obj argument; false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    TileGroup other = (TileGroup) obj;
    return this.type == other.type && this.tiles.equals(other.tiles);
  }

  /**
   * Returns a comparator for TileGroup objects.
   *
   * @return a comparator for TileGroup instances
   */
  public static Comparator<TileGroup> comparator() {
    return (g1, g2) -> {
      int typeComparison = g1.type.compareTo(g2.type);
      if (typeComparison != 0) {
        return typeComparison;
      }
      return g1.tiles.get(0).compareTo(g2.tiles.get(0));
    };
  }

  /**
   * Generates possible tile group strings for given completed tiles.
   *
   * @param ct          the completed tiles
   * @param callgroups  the list of call groups
   * @param tsumo       whether the winning tile was self-drawn
   * @param lastTile    the last tile
   * @return a list of possible tile group strings
   */
  public ArrayList<ArrayList<String>> generateTileGroupStrings(
      CompletedTiles ct, List<CallGroup> callgroups,
      boolean tsumo, BaseTile lastTile) {
    ArrayList<String> rawTileGroupString = new ArrayList<>();

    if (!ct.getHead().getTiles().isEmpty()) {
      rawTileGroupString.add(makeToitsu(ct.getHead().getTiles().get(0)));
    } else if (ct.getBody().size() != 7) {
      throw new RuntimeException("Unknown CompletedTiles object. No Head and not 7-toitsu");
    } else {
      for (int i = 0; i < 7; i++) {
        rawTileGroupString.add(makeToitsu(ct.getBody().get(i).getTiles().get(0)));
      }
    }

    for (CallGroup callgroup : callgroups) {
      switch (callgroup.getType()) {
        case CHI:
          rawTileGroupString.add(makeShuntsuFuuro(callgroup.getTiles().get(0).getBaseTile()));
          break;
        case PON:
          rawTileGroupString.add(makeKoutsuFuuro(callgroup.getTiles().get(0).getBaseTile()));
          break;
        case DAIMINKAN:
        case KAKAN:
          rawTileGroupString.add(makeMinkan(callgroup.getTiles().get(0).getBaseTile()));
          break;
        case ANKAN:
          rawTileGroupString.add(makeAnkan(callgroup.getTiles().get(0).getBaseTile()));
          break;
        default:
          break; // should not reach here
      }
    }

    for (TileGroup tilegroup : ct.getBody()) {
      switch (tilegroup.getType()) {
        case SHUNTSU:
          rawTileGroupString.add(makeShuntsu(tilegroup.getTiles().get(0)));
          break;
        case KOUTSU:
          rawTileGroupString.add(makeKoutsu(tilegroup.getTiles().get(0)));
          break;
        default:
          break;
      }
    }
    
    ArrayList<ArrayList<String>> tileGroupStrings = new ArrayList<>();
    String lastTileString = lastTile.toString();
    for (String tileGroup : rawTileGroupString) {
      char attr = tileGroup.charAt(tileGroup.length() - 1);

      if (attr == markKoutsu || attr == markToitsu) {
        if (tileGroup.substring(0, 2).equals(lastTileString.substring(0, 2))) {
          if (tsumo) {
            tileGroup += markTsumo1st;
          } else {
            tileGroup += markRon1st;
          }
          tileGroupStrings.add(new ArrayList<>(rawTileGroupString));
          tileGroup = tileGroup.substring(0, tileGroup.length() - 2);
        }
      } else if (attr == markShuntsu) {
        if (tileGroup.substring(0, 2).equals(lastTileString.substring(0, 2))) {
          if (tsumo) {
            tileGroup += markTsumo1st;
          } else {
            tileGroup += markRon1st;
          }
          tileGroupStrings.add(new ArrayList<>(rawTileGroupString));
          tileGroup = tileGroup.substring(0, tileGroup.length() - 2);
        } else if (tileGroup.charAt(0) + 1 == lastTileString.charAt(0)
            && tileGroup.charAt(1) == lastTileString.charAt(1)) {
          if (tsumo) {
            tileGroup += markTsumo2nd;
          } else {
            tileGroup += markRon2nd;
          }
          tileGroupStrings.add(new ArrayList<>(rawTileGroupString));
          tileGroup = tileGroup.substring(0, tileGroup.length() - 2);
        } else if (tileGroup.charAt(0) + 2 == lastTileString.charAt(0)
            && tileGroup.charAt(1) == lastTileString.charAt(1)) {
          if (tsumo) {
            tileGroup += markTsumo3rd;
          } else {
            tileGroup += markRon3rd;
          }
          tileGroupStrings.add(new ArrayList<>(rawTileGroupString));
          tileGroup = tileGroup.substring(0, tileGroup.length() - 2);
        }
      }
    }

    Collections.sort(tileGroupStrings, (a, b) -> a.toString().compareTo(b.toString()));
    ArrayList<ArrayList<String>> uniqueTileGroupStrings = new ArrayList<>();
    for (ArrayList<String> group : tileGroupStrings) {
      if (!uniqueTileGroupStrings.contains(group)) {
        uniqueTileGroupStrings.add(group);
      }
    }

    return uniqueTileGroupStrings;
  }

  
  /**
   * Removes Fuuro (open melds) and winning tile information, and creates a copy.
   *
   * @param strs the list of tile group strings
   * @return a new list with the specified information removed
   */
  public static ArrayList<String> remove4(ArrayList<String> strs) {
    ArrayList<String> retstr = new ArrayList<>(strs);

    for (int i = 0; i < retstr.size(); i++) {
      String ret = retstr.get(i);
      if (ret.length() == 4) {
        ret = ret.substring(0, 3);
      }
      if (ret.charAt(2) == '|') {
        ret = ret.substring(0, 2) + markKoutsu + ret.substring(3);
      }
      retstr.set(i, ret);
    }
    return retstr;
  }
}