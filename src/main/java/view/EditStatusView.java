package view;

import interface_adapter.edit_status.EditStatusController;
import interface_adapter.edit_status.EditStatusViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * The panel for editing user gameplay status.
 * Handles selecting/unselecting gameplay statuses as well as selecting Dora indicators.
 */
public class EditStatusView extends JPanel implements ActionListener, PropertyChangeListener {

    private String viewName = "edit status";
    private Boolean[] statuses;
    private final EditStatusViewModel editStatusViewModel;
    private final EditStatusController editStatusController;

    /**
     * Constructs a EditStatusView object with the specified view model and controller.
     * @param editStatusViewModel the view model for editing gameplay statuses.
     * @param editStatusController the controller for editing gameplay statuses.
     */
    public EditStatusView(EditStatusViewModel editStatusViewModel, EditStatusController editStatusController) {
        this.editStatusViewModel = editStatusViewModel;
        this.editStatusController = editStatusController;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Constructs panel for all checkboxes corresponding to each status
        JPanel checkboxPanel = new JPanel();
        List<JPanel> checkboxPanels = new ArrayList<>();

        // Add all status checkboxes to the panel
        for (String status : EditStatusViewModel.STATUSES) {
            JPanel statusPanel = new JPanel();
            statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
            JCheckBox statusCheckBox = new JCheckBox();
            statusCheckBox.setSelected(false);
            statusCheckBox.addActionListener(this);
            JLabel statusLabel = new JLabel(status);
            statusPanel.add(statusCheckBox);
            statusPanel.add(statusLabel);
            checkboxPanels.add(statusPanel);
        }

        // Add all status panels to the checkbox panel
        for (JPanel statusPanel : checkboxPanels) {
            checkboxPanel.add(statusPanel);
        }

        // Construct panel for red dora checkboxes
        JPanel redDoraPanel = new JPanel();
        redDoraPanel.setLayout(new BoxLayout(redDoraPanel, BoxLayout.Y_AXIS));

        JPanel redDoraManPanel = new JPanel();
        JCheckBox redDoraManCheckBox = new JCheckBox();
        redDoraManCheckBox.setSelected(false);
        redDoraManCheckBox.addActionListener(this);
        JLabel redDoraManLabel = new JLabel(EditStatusViewModel.MAN_LABEL);
        redDoraManPanel.add(redDoraManCheckBox);
        redDoraManPanel.add(redDoraManLabel);

        JPanel redDoraPinPanel = new JPanel();
        JCheckBox redDoraPinCheckBox = new JCheckBox();
        redDoraPinCheckBox.setSelected(false);
        redDoraPinCheckBox.addActionListener(this);
        JLabel redDoraPinLabel = new JLabel(EditStatusViewModel.PIN_LABEL);
        redDoraPinPanel.add(redDoraPinCheckBox);
        redDoraPinPanel.add(redDoraPinLabel);

        JPanel redDoraSouPanel = new JPanel();
        JCheckBox redDoraSouCheckBox = new JCheckBox();
        redDoraSouCheckBox.setSelected(false);
        redDoraSouCheckBox.addActionListener(this);
        JLabel redDoraSouLabel = new JLabel(EditStatusViewModel.SOU_LABEL);
        redDoraSouPanel.add(redDoraSouCheckBox);
        redDoraSouPanel.add(redDoraSouLabel);

        JLabel redDoraLabel = new JLabel(EditStatusViewModel.RED_DORA_LABEL);
        JPanel redDoraCheckBoxPanel = new JPanel(new FlowLayout());

        redDoraCheckBoxPanel.add(redDoraManPanel);
        redDoraCheckBoxPanel.add(redDoraPinPanel);
        redDoraCheckBoxPanel.add(redDoraSouPanel);

        redDoraPanel.add(redDoraLabel);
        redDoraPanel.add(redDoraCheckBoxPanel);



        // Construct panel for the dora indicator image labels
        JPanel doraIndicatorPanel = new JPanel();
        doraIndicatorPanel.setLayout(new FlowLayout());

        // Initialize blank dora indicator labels.
        // TODO: Add actual dora indicator images according to db
        for (int i = 0; i < 5; i++) {
            ImageIcon doraIndicatorIcon = loadImageIcon(EditStatusViewModel.BLANK_MAHJONG_PATH);
            JLabel doraIndicatorLabel = new JLabel(doraIndicatorIcon);
            doraIndicatorLabel.setPreferredSize(new Dimension(60, 80));
            doraIndicatorPanel.add(doraIndicatorLabel);
        }

        // Button for opening dora indicator selector
        JButton indicatorSelectButton = new JButton(EditStatusViewModel.SELECT_INDICATOR_LABEL);
        indicatorSelectButton.addActionListener(this);

        // Add all components to this panel
        this.add(checkboxPanel);
        this.add(redDoraPanel);
        this.add(doraIndicatorPanel);
        this.add(indicatorSelectButton);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private ImageIcon loadImageIcon(String path) {
        // Get resource from the classpath
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}