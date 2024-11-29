package entity;

/**
 * Factory class for creating instances of CommonAddTileManager.
 */
public class CommonAddTileManagerFactory implements AddTileManagerFactory {

    /**
     * Creates a new instance of CommonAddTileManager based on the provided type.
     *
     * @param type the type of AddTileManager to create
     * @return a new instance of CommonAddTileManager
     */
    @Override
    public AddTileManager create(String type) {
        return new CommonAddTileManager(type);
    }
}
