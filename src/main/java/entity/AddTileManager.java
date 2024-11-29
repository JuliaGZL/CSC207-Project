package entity;

/**
 * The representation of an Add-Tile Manager in our program.
 */
public interface AddTileManager {
    /**
     * Returns the type of tile that would be added.
     * @return the type of tile that would be added.
     */
    String getAddTileType();

    /**
     * Sets the type of tile that would be added.
     * @param addTileType the new type of tile that would be added.
     */
    void setAddTileType(String addTileType);
}
