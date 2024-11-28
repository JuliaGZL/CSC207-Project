package entity;

/**
 * Factory for creating Add-Tile Managers.
 */
public interface AddTileManagerFactory {

    /**
     * Creates a new AddTileManager.
     * @param name the name of the new player
     * @return the new AddTileManager
     */
    AddTileManager create(String name);
}
