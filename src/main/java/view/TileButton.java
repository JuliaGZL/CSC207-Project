package view;

import mahjong.BaseTiles;

import javax.swing.*;

/**
 * A TileButton is simply a JButton which with an extra property indicating the associated Tile ID.
 */
public class TileButton extends JButton {
    private final BaseTiles tileId;

    public  TileButton(Icon icon, BaseTiles tileId) {
        super(icon);
        this.tileId = tileId;
    }

    public BaseTiles getTileId() {
        return tileId;
    }
}
