package view;

import interface_adapter.edit_tiles.AddTileController;
import interface_adapter.edit_tiles.SelectDoraController;
import interface_adapter.edit_tiles.TileSelectorState;
import interface_adapter.edit_tiles.TileSelectorViewModel;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

public class TileSelectorView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "select hand tiles or dora indicators";
    private final TileSelectorViewModel tileSelectorViewModel;

    private JLabel titleLabel = new JLabel("Select hand");

    private JPanel doraIndicatorPanel;
    // private JButton confirmButton = new JButton(SelectDoraViewModel.CONFIRM_BUTTON_LABEL);

    // Target to add the tile
    private String tileAddTarget = "hand";

    private SelectDoraController selectDoraController;
    private AddTileController addTileController;

    public TileSelectorView(TileSelectorViewModel tileSelectorViewModel,
                            SelectDoraController selectDoraController,
                            AddTileController addTileController) {
        this.tileSelectorViewModel = tileSelectorViewModel;
        this.selectDoraController = selectDoraController;
        this.addTileController = addTileController;

        this.tileSelectorViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Configure doraIndicatorPanel
        doraIndicatorPanel = new JPanel();
        doraIndicatorPanel.setLayout(new GridLayout(4, 9));

        // Configure style of the title label
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Create the buttons for the dora indicators and add them to the panel.
        addButtons();

        // Add all components to the panel
        this.add(titleLabel);
        this.add(doraIndicatorPanel);
        // this.add(confirmButton); - we don't need a confirm button
    }

    private void addButtons() {
        for (BaseTile[] row : TileSelectorViewModel.tileRows){
            for (BaseTile tile : row) {
                // create the button object
                TileButton button = createImageButton(
                        BaseTileToPathMapping.getTilePath(tile),
                        tile
                );

                // add action listener
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        // Add card to either dora or hand according to current state
                        //      indicated by addTarget.
                        if (tileAddTarget.equals("dora")) {
                            // TODO: selectDoraController.execute(...)
                        } else if (tileAddTarget.equals("hand")) {
                            // TODO: addTileController.execute(...)
                        }
                    }
                });

                // add to panel
                doraIndicatorPanel.add(button);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // update tile insertion destination as needed
        if (evt.getPropertyName().equals("target")) {
            TileSelectorState state = (TileSelectorState) evt.getNewValue();
            setTileAddTarget(state.getTarget());
            // update title label
            this.titleLabel.setText("Select " + state.getTarget());
        }
    }

    /**
     * Creates a button with the appearance of the image under imagePath.
     * @param imagePath the path of the image to be displayed on the button
     * @return the button with the image
     */
    private TileButton createImageButton(String imagePath, BaseTile tileId) {
        // Load the image from the resources directory
        URL imageURL = getClass().getResource(imagePath);
        if (imageURL == null) {
            throw new IllegalArgumentException("Image not found: " + imagePath);
        }

        // Dimension of original image is 300 x 400
        // Resize the image to 60 x 80
        Image image = (new ImageIcon(imageURL)).getImage().getScaledInstance(TileSelectorViewModel.TILE_WIDTH,
                TileSelectorViewModel.TILE_HEIGHT, Image.SCALE_SMOOTH);

        return getTileButton(tileId, image);
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

    public String getViewName() {
        return viewName;
    }

    public void setSelectDoraController(SelectDoraController selectDoraController) {
        this.selectDoraController = selectDoraController;
    }

    public void setAddTileController(AddTileController addTileController) {
        this.addTileController = addTileController;
    }

    public String getTileAddTarget() {
        return tileAddTarget;
    }

    public void setTileAddTarget(String tileAddTarget) {
        this.tileAddTarget = tileAddTarget;
    }
}
