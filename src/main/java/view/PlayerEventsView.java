package view;

import interface_adapter.player_events.ChangePlayerController;
import interface_adapter.player_events.HuSolverController;
import interface_adapter.player_events.PlayerEventsViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class PlayerEventsView extends JPanel implements PropertyChangeListener {
    private final PlayerEventsViewModel viewModel;
    private final JLabel playerNameLabel;
    private final JLabel playerScoreLabel;

    private ChangePlayerController changePlayerController;
    private HuSolverController huSolverController;
    private String playerName;
    private int score;

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

        // set layout
        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.Y_AXIS));
        upperPanel.add(playerNameLabel);
        upperPanel.add(playerScoreLabel);
        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.X_AXIS));
        lowerPanel.add(computeScoreButton);
        lowerPanel.add(changePlayerButton);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(lowerPanel);
        this.add(upperPanel);
    }

    /**
     * get the view name
     * @return view name from the viewModel
     */
    public String getViewName() {
        return viewModel.getViewName();
    }

    public void setChangePlayerController(ChangePlayerController changePlayerController) {
        this.changePlayerController = changePlayerController;
    }

    public void setHuSolverController(HuSolverController huSolverController) {
        this.huSolverController = huSolverController;
    }

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
            this.playerScoreLabel.setText(String.valueOf(score));
            JOptionPane.showMessageDialog(this, this.viewModel.getState().getMessage());
        } else if (property.equals("failed")) {
            JOptionPane.showMessageDialog(this, this.viewModel.getState().getMessage());
        }
    }
}
