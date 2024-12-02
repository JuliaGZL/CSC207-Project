package view;

import javax.swing.Icon;
import javax.swing.JButton;
import mahjong.BaseTile;

/**
 * A TileButton is simply a JButton which with an extra property indicating the
 * associated Tile ID.
 */
public class TileButton extends JButton {
  private final BaseTile tileId;

  /**
   * Constructs a TileButton with the specified icon and tile ID.
   *
   * @param icon the icon to be displayed on the button
   * @param tileId the tile ID associated with this button
   */
  public TileButton(Icon icon, BaseTile tileId) {
    super(icon);
    this.tileId = tileId;
  }

  /**
   * Retrieves the tile identifier associated with this TileButton.
   *
   * @return the tile identifier of type BaseTile
   */
  public BaseTile getTileId() {
    return tileId;
  }
}
