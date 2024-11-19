package view;

import mahjong.BaseTile;

import javax.swing.*;

/**
 * A TileButton is simply a JButton which with an extra property indicating the associated Tile ID.
 */
public class TileButton extends JButton {
    private final BaseTile tileId;

    public  TileButton(Icon icon, BaseTile tileId) {
        super(icon);
        this.tileId = tileId;
    }

    public BaseTile getTileId() {
        return tileId;
    }
}
