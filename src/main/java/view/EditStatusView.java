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

        // Constructs panel with combo box for selecting the type of tile added
        JPanel tileTypePanel = new JPanel();
        tileTypePanel.setLayout(new FlowLayout());
        JLabel tileTypeLabel = new JLabel("Tile Type:");
        JComboBox<String> tileTypeComboBox = new JComboBox<>(EditStatusViewModel.TILE_TYPES);
        tileTypePanel.add(tileTypeLabel);
        tileTypePanel.add(tileTypeComboBox);

        // Constructs panel with combo box for how the player has won
        JPanel winTypePanel = new JPanel();
        winTypePanel.setLayout(new FlowLayout());
        JLabel winTypeLabel = new JLabel("Win Type:");
        JComboBox<String> winTypeComboBox = new JComboBox<>(EditStatusViewModel.WIN_TYPES);
        winTypePanel.add(winTypeLabel);
        winTypePanel.add(winTypeComboBox);

        // Constructs panel with combo box for selecting round wind
        JPanel roundWindPanel = new JPanel();
        roundWindPanel.setLayout(new FlowLayout());
        JLabel roundWindLabel = new JLabel("Round Wind:");
        JComboBox<String> roundWindComboBox = new JComboBox<>(EditStatusViewModel.WINDS);
        roundWindPanel.add(roundWindLabel);
        roundWindPanel.add(roundWindComboBox);

        // Constructs panel with combo box for selecting seat wind
        JPanel seatWindPanel = new JPanel();
        seatWindPanel.setLayout(new FlowLayout());
        JLabel seatWindLabel = new JLabel("Seat Wind:");
        JComboBox<String> seatWindComboBox = new JComboBox<>(EditStatusViewModel.WINDS);
        seatWindPanel.add(seatWindLabel);
        seatWindPanel.add(seatWindComboBox);

//        // Constructs panel with spinner for selecting the number of Akadora
//        JPanel akadoraPanel = new JPanel();
//        akadoraPanel.setLayout(new FlowLayout());
//        JLabel akadoraLabel = new JLabel("Akadora:");
//        JSpinner akadoraSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 3, 1));
//        akadoraPanel.add(akadoraLabel);
//        akadoraPanel.add(akadoraSpinner);

        // Add all combo box and spinner panels to a big panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(tileTypePanel);
        leftPanel.add(winTypePanel);
        leftPanel.add(roundWindPanel);
        leftPanel.add(seatWindPanel);
//        leftPanel.add(akadoraPanel);

        // Construct panel for the list of checkboxes
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        List<JPanel> checkboxPanels = new ArrayList<>();

        // Constructs panel for each checkbox & label
        for (String attribute : EditStatusViewModel.ATTRIBUTES) {
            JPanel attributePanel = new JPanel();
            attributePanel.setLayout(new FlowLayout());
            JCheckBox attributeCheckBox = new JCheckBox();
            attributeCheckBox.setSelected(false);
            attributeCheckBox.addActionListener(this);
            JLabel attributeLabel = new JLabel(attribute);
            attributePanel.add(attributeCheckBox);
            attributePanel.add(attributeLabel);
            checkboxPanels.add(attributePanel);
        }

        // Add all individual checkbox panels to the main checkbox panel
        for (JPanel panel : checkboxPanels) {
            checkboxPanel.add(panel);
        }

        // Add all components to this panel
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(leftPanel);
        this.add(checkboxPanel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the object where the event occurred
        Object source = e.getSource();

        if (source instanceof JComboBox) {
            // If the event occurred on a combo box
            JComboBox<String> comboBox = (JComboBox<String>) source;
            String selectedItem = (String) comboBox.getSelectedItem();
            handleComboBoxSelection(comboBox, selectedItem);
        }
    }

    /**
     * Method for handling selection events on combo boxes.
     * @param comboBox the combo box where the selection event occurred
     * @param selectedItem the item (String) that was selected
     */
    private void handleComboBoxSelection(JComboBox<String> comboBox, String selectedItem) {
        // TODO: disable certain checkboxes based on combo box selections
        // TODO: update the view model/state and use controller to update entities
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Update what the user is looking at, which is unnecessary under this view
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