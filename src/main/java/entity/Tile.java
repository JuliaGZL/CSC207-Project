package entity;

import mahjong.BaseTiles;

/**
 * (DUMMY) A Tile.
 */
public class Tile {
    public Tile(BaseTiles tile, boolean isRedDora, boolean isUraDora, boolean isDora) {
        // do nothing yet for now;
    }
}

/*
 * Entity that represents a tile.
 *  - id: ID of the card.
 *  - displayName: Name of the tile to be displayed on the GUI.
 *  - iconPath: Path to the icon file to be displayed on the GUI.

public class Tile {
    private final int id;
    private final String displayName;
    private final String iconPath;

    public Tile(int id, String displayName, String iconPath) {
        this.id = id;
        this.displayName = displayName;
        this.iconPath = iconPath;
    }

    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getIconPath() {
        return iconPath;
    }
}
*/
