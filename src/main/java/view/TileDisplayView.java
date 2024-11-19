package view;

import interface_adapter.edit_tiles.*;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

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
    }

    private void setTiles(List<BaseTile> idList,
                          List<String> nameList,
                          List<String> iconList) {
        // clear all current components
        this.tileListPanel.removeAll();
        // rebuild the list
        for (int i = 0; i < idList.size(); i++) {
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
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // update tile list
        TilesDisplayState state = (TilesDisplayState) evt.getNewValue();
        if(evt.getPropertyName() == "player") {
            playerName = state.getPlayerName();
        } else if (evt.getPropertyName() == "tiles") {
            setTiles(state.getIdList(), state.getNameList(), state.getIconList());
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
