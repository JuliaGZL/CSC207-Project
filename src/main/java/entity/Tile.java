package entity;

import mahjong.BaseTile;

/**
 * Represents a tile in Mahjong.
 */
public class Tile {
  private BaseTile tile;
  private boolean isRedDora;
  private boolean isUraDora;
  private boolean isDora;

  /**
   * Sets the base tile.
   *
   * @param tile The base tile to set.
   */
  public void setTile(BaseTile tile) {
    this.tile = tile;
  }

  /**
   * Sets whether the tile is a red dora.
   *
   * @param isRedDora True if the tile is a red dora; false otherwise.
   */
  public void setRedDora(boolean isRedDora) {
    this.isRedDora = isRedDora;
  }

  /**
   * Sets whether the tile is an ura dora.
   *
   * @param isUraDora True if the tile is an ura dora; false otherwise.
   */
  public void setUraDora(boolean isUraDora) {
    this.isUraDora = isUraDora;
  }

  /**
   * Sets whether the tile is a dora.
   *
   * @param isDora True if the tile is a dora; false otherwise.
   */
  public void setDora(boolean isDora) {
    this.isDora = isDora;
  }

  /**
   * Initializes the tile with the specified attributes.
   *
   * @param tile      The base tile to set.
   * @param isRedDora True if the tile is a red dora.
   * @param isUraDora True if the tile is an ura dora.
   * @param isDora    True if the tile is a dora.
   */
  public Tile(BaseTile tile, boolean isRedDora, boolean isUraDora, boolean isDora) {
    this.setTile(tile);
    this.setRedDora(isRedDora);
    this.setUraDora(isUraDora);
    this.setDora(isDora);
  }

  /**
   * Tile with default setting.
   * 
   * @param tile The base tile (id).
   */
  public Tile(BaseTile tile) {
    this.setTile(tile);
    this.setRedDora(false);
    this.setUraDora(false);
    this.setDora(false);
  }

  /**
   * Returns a string representation of the tile.
   *
   * @return A string representing the tile.
   */
  @Override
  public String toString() {
    return "Tile [tile=" + tile.toString()
        + ", isRedDora=" + isRedDora
        + ", isUraDora=" + isUraDora + ", isDora="
        + isDora
        + "]";
  }

  /**
   * Returns the base tile.
   *
   * @return The base tile.
   */
  public BaseTile getBaseTile() {
    return tile;
  }

  /**
   * Checks if the tile is a red dora.
   *
   * @return True if the tile is a red dora; false otherwise.
   */
  public boolean isRedDora() {
    return isRedDora;
  }

  /**
   * Checks if the tile is an ura dora.
   *
   * @return True if the tile is an ura dora; false otherwise.
   */
  public boolean isUraDora() {
    return isUraDora;
  }

  /**
   * Checks if the tile is a dora.
   *
   * @return True if the tile is a dora; false otherwise.
   */
  public boolean isDora() {
    return isDora;
  }

  /**
   * Returns the base tile.
   *
   * @return The base tile.
   */
  public BaseTile getTile() {
    return tile;
  }
}