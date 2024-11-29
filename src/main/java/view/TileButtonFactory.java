package view;

import interface_adapter.edit_tiles.TileSelectorViewModel;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class TileButtonFactory {
    /**
     * Creates a button with the appearance of the image under imagePath.
     *
     * @param imagePath the path of the image to be displayed on the button
     * @return the button with the image
     */
    public static TileButton createImageButton(BaseTile tileId, String imagePath) {
        // Load the image from the resources directory
        URL imageURL = TileButton.class.getResource(imagePath);
        if (imageURL == null) {
            throw new IllegalArgumentException("Image not found: " + imagePath);
        }

        // Dimension of original image is 300 x 400
        // Resize the image to 60 x 80
        Image image = (new ImageIcon(imageURL)).getImage().getScaledInstance(TileSelectorViewModel.TILE_WIDTH,
                TileSelectorViewModel.TILE_HEIGHT, Image.SCALE_SMOOTH);

        return getTileButton(tileId, image);
    }

    /**
     * Creates a dummy blank button.
     */
    public static TileButton createDummyButton() {
        return createImageButton(BaseTile._1m, BaseTileToPathMapping.BLANK_TILE);
    }

    @NotNull
    private static TileButton getTileButton(BaseTile tileId, Image image) {
        ImageIcon icon = new ImageIcon(image);

        // Create the button with the icon and size
        TileButton button = new TileButton(icon, tileId);
        button.setPreferredSize(new Dimension(TileSelectorViewModel.TILE_WIDTH, TileSelectorViewModel.TILE_HEIGHT));

        // Remove button's default look
        button.setBorderPainted(true); // Remove border
        button.setFocusPainted(true);  // Remove focus border
        button.setContentAreaFilled(false); // Make the background transparent
        return button;
    }
}
