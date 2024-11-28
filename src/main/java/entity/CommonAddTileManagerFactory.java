package entity;

public class CommonAddTileManagerFactory implements AddTileManagerFactory {

    @Override
    public AddTileManager create(String type) {
        return new CommonAddTileManager(type);
    }
}
