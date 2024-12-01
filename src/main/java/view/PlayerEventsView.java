package view;

import interfaceadapter.playerevents.ChangePlayerController;
import interfaceadapter.playerevents.HuSolverController;
import interfaceadapter.playerevents.PlayerEventsViewModel;
import interfaceadapter.playerevents.PullRemoteHandController;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import utils.TextToSpeech;

/**
 * The PlayerEventsView class represents the view for player events in the game.
 * It listens to changes in the PlayerEventsViewModel and updates the UI accordingly.
 */
public class PlayerEventsView extends JPanel implements PropertyChangeListener {
  private final PlayerEventsViewModel viewModel;
  private final JLabel playerNameLabel;
  private final JLabel playerScoreLabel;

  private ChangePlayerController changePlayerController;
  private HuSolverController huSolverController;
  private PullRemoteHandController pullRemoteHandController;
  private String playerName;
  private int score;

  /**
   * Constructs a PlayerEventsView with the specified view model.
   *
   * @param viewModel the view model to be used by this view
   */
  public PlayerEventsView(PlayerEventsViewModel viewModel) {
    this.viewModel = viewModel;
    this.viewModel.addPropertyChangeListener(this);
    this.playerName = viewModel.getState().getPlayerName();
    this.score = viewModel.getState().getScore();

    // initialize contents
    playerNameLabel = new JLabel("Player: default");
    playerNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    playerScoreLabel = new JLabel("Score: 0");
    playerNameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    JButton changePlayerButton = new JButton("Change Player");
    JButton computeScoreButton = new JButton("Win");
    JButton pullHandFromDiscordButton = new JButton("Pull hand from Discord...");

    // set action
    changePlayerButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        String newPlayerName = JOptionPane.showInputDialog("New player name:");
        if (newPlayerName != null) {
          changePlayerController.execute(newPlayerName);
        }
      }
    });
    computeScoreButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        huSolverController.execute(playerName);
      }
    });
    pullHandFromDiscordButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        pullRemoteHandController.execute(playerName);
      }
    });

    // set layout
    JPanel rightPanel = new JPanel();
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    rightPanel.add(playerNameLabel);
    rightPanel.add(playerScoreLabel);
    JPanel upperLeftPanel = new JPanel();
    upperLeftPanel.setLayout(new BoxLayout(upperLeftPanel, BoxLayout.X_AXIS));
    upperLeftPanel.add(computeScoreButton);
    upperLeftPanel.add(changePlayerButton);
    JPanel leftPanel = new JPanel();
    final int rowNum = 2;
    final int colNum = 2;
    leftPanel.setLayout(new GridLayout(rowNum, colNum));
    leftPanel.add(upperLeftPanel);
    leftPanel.add(pullHandFromDiscordButton);
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    this.add(leftPanel);
    this.add(rightPanel);
  }

  /**
   * Gets the view name from the view model.
   *
   * @return the view name
   */
  public String getViewName() {
    return viewModel.getViewName();
  }

  /**
   * Sets the ChangePlayerController for this view.
   *
   * @param changePlayerController the ChangePlayerController to be set
   */
  public void setChangePlayerController(ChangePlayerController changePlayerController) {
    this.changePlayerController = changePlayerController;
  }

  /**
   * Sets the HuSolverController for this view.
   *
   * @param huSolverController the HuSolverController to be set
   */
  public void setHuSolverController(HuSolverController huSolverController) {
    this.huSolverController = huSolverController;
  }

  /**
   * Sets the PullRemoteHandController for this view.
   *
   * @param pullRemoteHandController the PullRemoteHandController to be set
   */
  public void setPullRemoteHandController(PullRemoteHandController pullRemoteHandController) {
    this.pullRemoteHandController = pullRemoteHandController;
  }

  /**
   * Handles property change events from the view model.
   *
   * @param propertyChangeEvent the property change event
   */
  @Override
  public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
    final String property = propertyChangeEvent.getPropertyName();
    if (property.equals("player")) {
      this.playerName = this.viewModel.getState().getPlayerName();
      this.score = this.viewModel.getState().getScore();
      this.playerNameLabel.setText("Player: " + this.playerName);
      this.playerScoreLabel.setText("Score: " + this.score);
    } else if (property.equals("score")) {
      this.score = this.viewModel.getState().getScore();
      this.playerScoreLabel.setText("Score: " + String.valueOf(score));

      String message = this.viewModel.getState().getMessage();
      showDialogWithHotkey(message);
    } else if (property.equals("failed")) {
      String message = this.viewModel.getState().getMessage();
      showDialogWithHotkey(message);
    }
  }

  /**
   * Helper function for showing a dialog that binds with the hotkey CTRL+R
   * for accessibility purposes.
   *
   * @param message the message to show in the dialog and to be read out loud
   */
  public void showDialogWithHotkey(String message) {
    // Create JOptionPane
    JOptionPane optionPane = new JOptionPane(
        message,
        JOptionPane.INFORMATION_MESSAGE,
        JOptionPane.DEFAULT_OPTION);
    // Create JDialog
    JDialog dialog = optionPane.createDialog(this, message);

    // Add hotkey binding
    dialog.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke("ctrl R"), "readMessage");
    dialog.getRootPane().getActionMap().put("readMessage", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // Read the message out loud
        readOutLoud(message);
      }
    });

    // Show the dialog
    dialog.setVisible(true);
  }

  /**
   * Reads out loud the specified message.
   *
   * @param message the message to be read
   */
  public void readOutLoud(String message) {
    // System.out.println(message);
    TextToSpeech ttsInstance = TextToSpeech.getInstance();
    ttsInstance.speakInThread(message);
  }
}
