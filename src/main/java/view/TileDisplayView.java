package view;

import interface_adapter.edit_tiles.*;
import mahjong.BaseTile;
import utils.BaseTileToPathMapping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Display a list of tiles, either hand or dora.
 */
public class TileDisplayView extends JPanel implements ActionListener, PropertyChangeListener {

    private final TilesDisplayViewModel viewModel;

    // player to act on
    private String playerName = "default";

    private JLabel titleLabel;
    private JPanel tileListPanel;
    private JPanel leftPanel;
    JButton clearButton;

    private RemoveTileController removeTileController;
    private ClearTilesController clearTilesController;

    public TileDisplayView(TilesDisplayViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        // Configure tileListPanel
        tileListPanel = new JPanel();
        tileListPanel.setLayout(new GridLayout(1, 14));

        // Configure style of the title label
        titleLabel = new JLabel(getViewName());
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Configure clear button
        clearButton = new JButton("Clear");
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clearTilesController.execute(playerName);
            }
        });

        // Add all components to the panel
        this.leftPanel = new JPanel();
        this.leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(titleLabel);
        leftPanel.add(tileListPanel);
        this.add(leftPanel);
        this.add(clearButton);

        // add dummy buttons
        this.setTiles(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }

    private void setTiles(List<BaseTile> idList,
                          List<String> nameList,
                          List<String> iconList) {
        // clear all current components
        this.tileListPanel.removeAll();
        // rebuild the list
        for (int i = 0; i < 14; i++) {
            if(i < idList.size()) {
                final BaseTile tileId = idList.get(i);
                final String iconPath = iconList.get(i);
                TileButton button = TileButtonFactory.createImageButton(iconPath, tileId);
                // add button action listener (for removing tile on click)
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        removeTileController.execute(tileId, playerName);
                    }
                });
                tileListPanel.add(button);
            } else {
                // fill in the blank with dummy buttons
                final TileButton button = TileButtonFactory.createImageButton(
                        BaseTileToPathMapping.getTilePath(BaseTile._5z),
                        null
                );
                tileListPanel.add(button);
            }
            // refresh display
            this.revalidate();
            this.repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // update tile list
        TilesDisplayState state = (TilesDisplayState) evt.getNewValue();
        if(Objects.equals(evt.getPropertyName(), "player")) {
            playerName = state.getPlayerName();
        } else if (Objects.equals(evt.getPropertyName(), "tiles")) {
            setTiles(state.getIdList(), state.getNameList(), state.getIconList());
        } else if (Objects.equals(evt.getPropertyName(), "failed")) {
            JOptionPane.showMessageDialog(null, state.getErrorMsg());
        }
    }

    public String getViewName() {
        return viewModel.getViewName();
    }

    public void setRemoveTileController(RemoveTileController removeTileController) {
        this.removeTileController = removeTileController;
    }

    public void setClearTilesController(ClearTilesController clearTilesController) {
        this.clearTilesController = clearTilesController;
    }
}
