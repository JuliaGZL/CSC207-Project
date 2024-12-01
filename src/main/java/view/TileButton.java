package view;

import javax.swing.*;

import mahjong.BaseTile;

/**
 * A TileButton is simply a JButton which with an extra property indicating the
 * associated Tile ID.
 */
public class TileButton extends JButton {
    private final BaseTile tileId;

    public TileButton(Icon icon, BaseTile tileId) {
        super(icon);
        this.tileId = tileId;
    }

    public BaseTile getTileId() {
        return tileId;
    }
}
