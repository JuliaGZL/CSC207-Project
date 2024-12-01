package view;

import interfaceadapter.edittiles.AddTileController;
import interfaceadapter.edittiles.TileSelectorState;
import interfaceadapter.edittiles.TileSelectorViewModel;
import interfaceadapter.edittiles.UpdateEnabledTileController;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;
import utils.TextToSpeech;

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

  // Controllers to insert tile to a list (hand/dora/uradora)
  private AddTileController addToHandController;
  private AddTileController addToDoraController;
  private AddTileController addToUradoraController;

  // controller to handle updating enabled tiles
  private UpdateEnabledTileController updateEnabledTileController;

  /**
   * Constructs a TileSelectorView with the specified TileSelectorViewModel.
   *
   * @param tileSelectorViewModel the view model for the tile selector
   */
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
    Set<BaseTile> enabledTiles = tileSelectorViewModel.getState().getEnabledTiles();
    for (BaseTile[] row : TileSelectorViewModel.tileRows) {
      for (BaseTile tile : row) {
        if (enabledTiles.contains(tile)) {
          // create the button object
          TileButton button = TileButtonFactory.createImageButton(
              tile, BaseTileToPathMapping.getTilePath(tile));

          // add action listener
          button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              // Add card to either dora or hand according to current state
              // indicated by addTarget.
              switch (tileAddTarget) {
                case "dora":
                  addToDoraController.execute(button.getTileId(), playerName);
                  break;
                case "uradora":
                  addToUradoraController.execute(button.getTileId(), playerName);
                  break;
                case "hand":
                  addToHandController.execute(button.getTileId(), playerName);
                  break;
                default:
                  throw new IllegalArgumentException("Invalid target:" + tileAddTarget);
              }
              // update enabled buttons
              updateEnabledTileController.execute(playerName, tileAddTarget);
            }
          });
          button.addMouseListener(new MouseAdapter() {
            // if hovered, speak the tile
            Timer timer = new Timer(200, new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                TextToSpeech.getInstance().speakInThread(tile.toText());
              }
            });

            @Override
            public void mouseEntered(MouseEvent e) {
              timer.setRepeats(false);
              timer.start();
            }

            @Override
            public void mouseExited(MouseEvent e) {
              timer.stop();
            }
          });

          // add to panel
          tileButtonsPanel.add(button);
        } else {
          // if tile not enabled, show blank button
          tileButtonsPanel.add(TileButtonFactory.createDummyButton());
        }
      }
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    // update tile insertion destination as needed
    final String property = evt.getPropertyName();
    switch (property) {
      case "target": {
        TileSelectorState state = (TileSelectorState) evt.getNewValue();
        setTileAddTarget(state.getMessage());
        // update title label
        this.titleLabel.setText("Select " + state.getMessage());
        break;
      }
      case "player": {
        // update player name
        TileSelectorState state = (TileSelectorState) evt.getNewValue();
        playerName = state.getPlayerName();
        break;
      }
      case "enabled_tiles":
        // update buttons to show only enabled ones
        tileButtonsPanel.removeAll();
        addButtons();
        tileButtonsPanel.revalidate();
        tileButtonsPanel.repaint();
        break;
      default:
        break;
    }
<<<<<<< HEAD
    // update enabled buttons
    if (!property.equals("enabled_tiles")) {
      updateEnabledTileController.execute(playerName, tileAddTarget);
=======

    private void addButtons() {
        Set<BaseTile> enabledTiles = tileSelectorViewModel.getState().getEnabledTiles();
        for (BaseTile[] row : TileSelectorViewModel.tileRows){
            for (BaseTile tile : row) {
                if (enabledTiles.contains(tile)) {
                    // create the button object
                    TileButton button = TileButtonFactory.createImageButton(
                            tile, BaseTileToPathMapping.getTilePath(tile)
                    );

                    // add action listener
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            // Add card to either dora or hand according to current state
                            //      indicated by addTarget.
                            switch (tileAddTarget) {
                                case "dora":
                                    addToDoraController.execute(button.getTileId(), playerName);
                                    break;
                                case "uradora":
                                    addToUradoraController.execute(button.getTileId(), playerName);
                                    break;
                                case "hand":
                                    addToHandController.execute(button.getTileId(), playerName);
                                    break;
                            }
                            // update enabled buttons
                            updateEnabledTileController.execute(playerName, tileAddTarget);
                        }
                    });
                    button.addMouseListener(new MouseAdapter() {
                        Timer timer = new Timer(2000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                TextToSpeech.getInstance().speakInThread(tile.toText());
                            }
                        });

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            timer.setRepeats(false);
                            timer.start();
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            timer.stop();
                        }
                    });

                    // add to panel
                    tileButtonsPanel.add(button);
                } else {
                    // if tile not enabled, show blank button
                    tileButtonsPanel.add(TileButtonFactory.createDummyButton());
                }
            }
        }
>>>>>>> 7d91e70385984a4316676034c69ba617340ee3bc
    }
  }

  public String getViewName() {
    return viewName;
  }

  public void setAddToHandController(AddTileController addToHandController) {
    this.addToHandController = addToHandController;
  }

  public void setAddToDoraController(AddTileController addToDoraController) {
    this.addToDoraController = addToDoraController;
  }

  public void setAddToUradoraController(AddTileController addToUradoraController) {
    this.addToUradoraController = addToUradoraController;
  }

  public void setUpdateEnabledTileController(
      UpdateEnabledTileController updateEnabledTileController) {
    this.updateEnabledTileController = updateEnabledTileController;
  }

  public String getTileAddTarget() {
    return tileAddTarget;
  }

  public void setTileAddTarget(String tileAddTarget) {
    this.tileAddTarget = tileAddTarget;
  }

  public String getPlayerName() {
    return playerName;
  }
}
