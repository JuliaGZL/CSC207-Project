package mahjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a group of tiles in Mahjong, which can be of type TOITSU, SHUNTSU,
 * or KOUTSU.
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

  /**
   * Creates a tile group representation with the given tile string and mark.
   *
   * @param t    the tile string
   * @param mark the mark character
   * @return a character array representing the tile group
   */
  public static char[] makeTileGroup(String t, char mark) {
    char[] ret = new char[] { t.charAt(0), t.charAt(1), mark };
    return ret;
  }

  /**
   * Creates a tile group representation with the given tile string and two marks.
   *
   * @param t     the tile string
   * @param mark1 the first mark character
   * @param mark2 the second mark character
   * @return a character array representing the tile group
   */
  public static char[] makeTileGroup(String t, char mark1, char mark2) {
    char[] ret = new char[] { t.charAt(0), t.charAt(1), mark1, mark2 };
    return ret;
  }

  /**
   * Creates a Toitsu (pair) tile group.
   *
   * @param t the tile string
   * @return a character array representing the Toitsu tile group
   */
  public static char[] makeToitsu(String t) {
    return makeTileGroup(t, markToitsu);
  }

  /**
   * Creates a Shuntsu (sequence) tile group.
   *
   * @param t the tile string
   * @return a character array representing the Shuntsu tile group
   */
  public static char[] makeShuntsu(String t) {
    return makeTileGroup(t, markShuntsu);
  }

  /**
   * Creates a Shuntsu Fuuro (open sequence) tile group.
   *
   * @param t the tile string
   * @return a character array representing the Shuntsu Fuuro tile group
   */
  public static char[] makeShuntsufuuro(String t) {
    return makeTileGroup(t, markShuntsu, markFuuro);
  }

  /**
   * Creates a Koutsu (triplet) tile group.
   *
   * @param t the tile string
   * @return a character array representing the Koutsu tile group
   */
  public static char[] makeKoutsu(String t) {
    return makeTileGroup(t, markKoutsu);
  }

  /**
   * Creates a Koutsu Fuuro (open triplet) tile group.
   *
   * @param t the tile string
   * @return a character array representing the Koutsu Fuuro tile group
   */
  public static char[] makeKoutsuFuuro(String t) {
    return makeTileGroup(t, markKoutsu, markFuuro);
  }

  /**
   * Creates an Ankan (concealed quad) tile group.
   *
   * @param t the tile string
   * @return a character array representing the Ankan tile group
   */
  public static char[] makeAnkan(String t) {
    return makeTileGroup(t, markKantsu, markAnkan);
  }

  /**
   * Creates a Minkan (open quad) tile group.
   *
   * @param t the tile string
   * @return a character array representing the Minkan tile group
   */
  public static char[] makeMinkan(String t) {
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
}