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