/*
 Copyright (c) 2024 YAizhou, li-tianchu, JuliaGZL, TurkeyBilly

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package view;

import interfaceadapter.displaytiles.TileSelectorPropertyUpdateNotifier;
import interfaceadapter.displaytiles.TilesDisplayState;
import interfaceadapter.displaytiles.TilesDisplayViewModel;
import interfaceadapter.edittiles.ClearTilesController;
import interfaceadapter.edittiles.RemoveTileController;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;

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
  private TileSelectorPropertyUpdateNotifier notifier;

  /**
   * Constructs a TileDisplayView with the specified view model.
   *
   * @param viewModel the view model to be used by this view
   */
  public TileDisplayView(TilesDisplayViewModel viewModel) {
    this.viewModel = viewModel;
    this.viewModel.addPropertyChangeListener(this);

    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

    // Configure tileListPanel
    tileListPanel = new JPanel();
    tileListPanel.setLayout(new GridLayout(1, 14));

    // Configure style of the title label
    titleLabel = new JLabel(getViewName());
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

    // Configure clear button
    clearButton = new JButton("Clear");
    clearButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        // clear the tiles
        clearTilesController.execute(playerName);
        // notify the tile selector to update enabled buttons
        notifier.notifyPropertyChange("", "");
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
      if (i < idList.size()) {
        final BaseTile tileId = idList.get(i);
        final String iconPath = iconList.get(i);
        TileButton button = TileButtonFactory.createImageButton(tileId, iconPath);
        // add button action listener (for removing tile on click)
        button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent actionEvent) {
            // remove the tile
            removeTileController.execute(tileId, playerName);
            // notify the tile selector to update enabled buttons
            notifier.notifyPropertyChange("", "");
          }
        });
        tileListPanel.add(button);
      } else {
        // fill in the blank with dummy buttons
        final TileButton button = TileButtonFactory.createImageButton(
            null, BaseTileToPathMapping.BLANK_TILE);
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
    if (Objects.equals(evt.getPropertyName(), "player")) {
      playerName = state.getPlayerName();
      // NOTE: with tileId null is a special type of remove tile use case
      // that does not actually remove any tile but just read out the tile list.
      removeTileController.execute(null, playerName);
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

  public void setNotifier(TileSelectorPropertyUpdateNotifier notifier) {
    this.notifier = notifier;
  }
}
