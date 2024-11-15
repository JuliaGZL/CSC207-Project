//package view;
//
//import interface_adapter.table_center.EditStatusController;
//import interface_adapter.table_center.EditStatusViewModel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.beans.PropertyChangeEvent;
//import java.beans.PropertyChangeListener;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * The panel for editing user gameplay status.
// * Handles selecting/unselecting gameplay statuses as well as selecting Dora indicators.
// */
//public class EditStatusPanel extends JPanel implements ActionListener, PropertyChangeListener {
//
//
//    private Boolean[] statuses;
//    private final EditStatusViewModel editStatusViewModel;
//    private final EditStatusController editStatusController;
//
//    /**
//     * Constructs a EditStatusPanel object with the specified view model and controller.
//     * @param editStatusViewModel the view model for editing gameplay statuses.
//     * @param editStatusController the controller for editing gameplay statuses.
//     */
//    public EditStatusPanel(EditStatusViewModel editStatusViewModel, EditStatusController editStatusController) {
//        this.editStatusViewModel = editStatusViewModel;
//        this.editStatusController = editStatusController;
//
//        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
//
//        // Constructs panel for all checkboxes corresponding to each status
//        JPanel checkboxPanel = new JPanel();
//        List<JPanel> checkboxPanels = new ArrayList<>();
//
//        // Add all status checkboxes to the panel
//        String[] statuses = {"Chii", "Pon", "Kan", "Riichi", "Tsumo"};
//        for (String status : statuses) {
//            JPanel statusPanel = new JPanel();
//            statusPanel.setLayout(new FlowLayout());
//            JCheckBox statusCheckBox = new JCheckBox();
//            statusCheckBox.setSelected(false);
//            statusCheckBox.addActionListener(this);
//            JLabel statusLabel = new JLabel(status);
//            statusPanel.add(statusCheckBox);
//            statusPanel.add(statusLabel);
//            checkboxPanels.add(statusPanel);
//        }
//
//        // Add all status panels to the checkbox panel
//        for (JPanel statusPanel : checkboxPanels) {
//            checkboxPanel.add(statusPanel);
//        }
//
//
//        // Construct panel for the dora indicator image labels
//        JPanel doraIndicatorPanel = new JPanel();
//        doraIndicatorPanel.setLayout(new FlowLayout());
//
//        // Initialize blank dora indicator labels. TODO: Add actual dora indicator images according to db
//        for (int i = 0; i < 5; i++) {
//            ImageIcon doraIndicatorIcon = loadImageIcon("/images/Back.png");
//            JLabel doraIndicatorLabel = new JLabel(doraIndicatorIcon);
//            doraIndicatorLabel.setPreferredSize(new Dimension(60, 80));
//            doraIndicatorPanel.add(doraIndicatorLabel);
//        }
//
//        // Button for opening dora indicator selector
//        JButton indicatorSelectButton = new JButton("Select Dora Indicators");
//        indicatorSelectButton.addActionListener(this);
//
//        // Add all components to this panel
//        this.add(checkboxPanel);
//        this.add(doraIndicatorPanel);
//        this.add(indicatorSelectButton);
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//
//    }
//
//    private ImageIcon loadImageIcon(String path) {
//        // Get resource from the classpath
//        java.net.URL imgURL = getClass().getResource(path);
//        if (imgURL != null) {
//            return new ImageIcon(imgURL);
//        } else {
//            System.err.println("Couldn't find file: " + path);
//            return null;
//        }
//    }
//}
