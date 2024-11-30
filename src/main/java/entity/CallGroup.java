package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a group of tiles called during a Mahjong game.
 */
public class CallGroup {

  /**
   * Types of call groups in Mahjong.
   */
  public enum Type {
    CHI, PON, DAIMINKAN, KAKAN, ANKAN;
  }

  /**
   * List of tiles in this call group.
   */
  private List<Tile> tiles;

  /**
   * Indicates the position of the "take" tile.
   */
  private int take = 0;

  /**
   * The type of this call group.
   */
  private Type type;

  /**
   * Constructs a CallGroup with the specified type.
   *
   * @param type the type of the call group
   */
  public CallGroup(Type type) {
    this.type = type;
    this.tiles = new ArrayList<>();
  }

  /**
   * Constructs a CallGroup with the specified type, tiles, and take position.
   *
   * @param type the type of the call group
   * @param tiles the list of tiles
   * @param take the position of the "take" tile
   */
  public CallGroup(Type type, List<Tile> tiles, int take) {
    this.type = type;
    this.tiles = tiles;
    this.take = take;
  }

  /**
   * Gets the list of tiles.
   *
   * @return the list of tiles
   */
  public List<Tile> getTiles() {
    return tiles;
  }

  /**
   * Sets the list of tiles.
   *
   * @param tiles the new list of tiles
   */
  public void setTiles(List<Tile> tiles) {
    this.tiles = tiles;
  }

  /**
   * Gets the position of the "take" tile.
   *
   * @return the take position
   */
  public int getTake() {
    return take;
  }

  /**
   * Sets the position of the "take" tile.
   *
   * @param take the new take position
   */
  public void setTake(int take) {
    this.take = take;
  }

  /**
   * Gets the type of this call group.
   *
   * @return the type
   */
  public Type getType() {
    return type;
  }

  /**
   * Sets the type of this call group.
   *
   * @param type the new type
   */
  public void setType(Type type) {
    this.type = type;
  }

  /**
   * Returns a string representation of the call group.
   *
   * @return a string representation
   */
  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    switch (type) {
      case CHI:
      case PON:
      case DAIMINKAN:
        for (int i = 0; i < 3; i++) {
          if (i == take) {
            ret.append("(").append(tiles.get(i).toString()).append(")");
          } else {
            ret.append(tiles.get(i).toString());
          }
        }
        break;

      case KAKAN:
        for (int i = 0; i < 4; i++) {
          if (i == take && i == 3) {
            ret.append("(").append(tiles.get(i).toString()).append(")");
          } else {
            ret.append(tiles.get(i).toString());
          }
        }
        break;

      case ANKAN:
        for (int i = 0; i < 4; i++) {
          if (i == 0 || i == 3) {
            ret.append(tiles.get(i).toString());
          } else {
            ret.append("*");
          }
        }
        break;

      default:
        throw new RuntimeException("Bad call group (fuuro) type.");
    }
    return ret.toString();
  }
}
