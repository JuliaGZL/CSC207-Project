package entity;

public class CommonAddTileManager implements AddTileManager {

    private String name;
    private String addTileType = "Hand";    // Default value of addTileType as shown in GUI

    public CommonAddTileManager(String name) {
        this.name = name;
    }

    public CommonAddTileManager(String name, String addTileType) {
        this.name = name;
        this.addTileType = addTileType;
    }

    @Override
    public String getAddTileType() {
        return addTileType;
    }

    public void setAddTileType(String addTileType) {
        this.addTileType = addTileType;
    }
}
