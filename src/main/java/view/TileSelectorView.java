package view;

import interface_adapter.edit_tiles.AddTileController;
import interface_adapter.edit_tiles.SelectDoraController;
import interface_adapter.edit_tiles.TileSelectorState;
import interface_adapter.edit_tiles.TileSelectorViewModel;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The tile selector, for both hand and dora.
 */
public class TileSelectorView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "select hand tiles or dora indicators";
    private final TileSelectorViewModel tileSelectorViewModel;

    private JLabel titleLabel = new JLabel("Select hand");

    private JPanel tileButtonsPanel;

    // Target to add the tile
    private String tileAddTarget = "hand";
    // Player to insert tiles
    private String playerName = "default";

    private SelectDoraController selectDoraController;
    private AddTileController addTileController;

    public TileSelectorView(TileSelectorViewModel tileSelectorViewModel) {
        this.tileSelectorViewModel = tileSelectorViewModel;

        this.tileSelectorViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Configure doraIndicatorPanel
        tileButtonsPanel = new JPanel();
        tileButtonsPanel.setLayout(new GridLayout(4, 9));

        // Configure style of the title label
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("A" +
                "rial", Font.BOLD, 20));

        // Create the buttons for the tile buttons and add them to the panel.
        addButtons();

        // Add all components to the panel
        final JPanel buttonsWrapperPanel = new JPanel();
        buttonsWrapperPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 30));
        buttonsWrapperPanel.add(tileButtonsPanel);
        this.add(titleLabel);
        this.add(buttonsWrapperPanel);
        // this.add(confirmButton); - we don't need a confirm button
    }

    private void addButtons() {
        for (BaseTile[] row : TileSelectorViewModel.tileRows){
            for (BaseTile tile : row) {
                // create the button object
                TileButton button = TileButtonFactory.createImageButton(
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
                            addTileController.execute(button.getTileId(), playerName);
                        }
                    }
                });

                // add to panel
                tileButtonsPanel.add(button);
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
        } else if (evt.getPropertyName().equals("player")) {
            TileSelectorState state = (TileSelectorState) evt.getNewValue();
            playerName = state.getPlayerName();
        }
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
