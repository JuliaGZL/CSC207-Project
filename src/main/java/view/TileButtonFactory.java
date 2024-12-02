package view;

import interfaceadapter.edittiles.TileSelectorViewModel;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;
import org.jetbrains.annotations.NotNull;

/**
 * A factory class for creating TileButton instances with specific images or as
 * dummy buttons.
 */
public class TileButtonFactory {
  /**
   * Creates a button with the appearance of the image under imagePath.
   *
   * @param tileId    the identifier of the tile
   * @param imagePath the path of the image to be displayed on the button
   * @return the button with the image
   * @throws IllegalArgumentException if the image is not found at the specified
   *                                  path
   */
  public static TileButton createImageButton(BaseTile tileId, String imagePath) {
    // Load the image from the resources directory
    URL imageURL = TileButton.class.getResource(imagePath);
    if (imageURL == null) {
      throw new IllegalArgumentException("Image not found: " + imagePath);
    }

    // Dimension of original image is 300 x 400
    // Resize the image to 60 x 80
    Image image = (new ImageIcon(imageURL)).getImage().getScaledInstance(
        TileSelectorViewModel.TILE_WIDTH,
        TileSelectorViewModel.TILE_HEIGHT, Image.SCALE_SMOOTH);

    return getTileButton(tileId, image);
  }

  /**
   * Creates a dummy blank button.
   *
   * @return a dummy button with a blank tile image
   */
  public static TileButton createDummyButton() {
    return createImageButton(BaseTile._1m, BaseTileToPathMapping.BLANK_TILE);
  }

  /**
   * Creates a TileButton with the specified tile identifier and image.
   *
   * @param tileId the identifier of the tile
   * @param image  the image to be displayed on the button
   * @return a TileButton with the specified image and tile identifier
   */
  @NotNull
  private static TileButton getTileButton(BaseTile tileId, Image image) {
    ImageIcon icon = new ImageIcon(image);

    // Create the button with the icon and size
    TileButton button = new TileButton(icon, tileId);
    button.setPreferredSize(new Dimension(
        TileSelectorViewModel.TILE_WIDTH, TileSelectorViewModel.TILE_HEIGHT));

    // Remove button's default look
    button.setBorderPainted(true); // Remove border
    button.setFocusPainted(true); // Remove focus border
    button.setContentAreaFilled(false); // Make the background transparent
    return button;
  }
}
