package entity;

/**
 * Manages the addition of tiles with a specified type.
 */
public class CommonAddTileManager implements AddTileManager {

    private String name;
    private String addTileType = "Hand";    // Default value of addTileType as shown in GUI

    /**
     * Constructs a CommonAddTileManager with the specified name.
     *
     * @param name the name of the manager
     */
    public CommonAddTileManager(String name) {
        this.name = name;
    }

    /**
     * Constructs a CommonAddTileManager with the specified name and addTileType.
     *
     * @param name the name of the manager
     * @param addTileType the type of tile addition
     */
    public CommonAddTileManager(String name, String addTileType) {
        this.name = name;
        this.addTileType = addTileType;
    }

    /**
     * Returns the type of tile addition.
     *
     * @return the addTileType
     */
    @Override
    public String getAddTileType() {
        return addTileType;
    }

    /**
     * Sets the type of tile addition.
     *
     * @param addTileType the new type of tile addition
     */
    public void setAddTileType(String addTileType) {
        this.addTileType = addTileType;
    }
}
