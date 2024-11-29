package view;

import interface_adapter.edit_status.EditStatusController;
import interface_adapter.edit_status.EditStatusState;
import interface_adapter.edit_status.EditStatusViewModel;
import interface_adapter.edit_tiles.TileSelectorPropertyUpdateNotifier;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
    private EditStatusController editStatusController;
    private TileSelectorPropertyUpdateNotifier notifier;

    private String playerName = "default";

    private JComboBox<String> tileTypeComboBox;
    private JComboBox<String> winTypeComboBox;
    private JComboBox<String> roundWindComboBox;
    private JComboBox<String> seatWindComboBox;
    private JSpinner akadoraSpinner;
    private List<JCheckBox> checkBoxes = new ArrayList<>();


    /**
     * Constructs a EditStatusView object with the specified view model and controller.
     * @param editStatusViewModel the view model for editing gameplay statuses.
     */
    public EditStatusView(EditStatusViewModel editStatusViewModel) {
        this.editStatusViewModel = editStatusViewModel;
        editStatusViewModel.addPropertyChangeListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Constructs panel with combo box for selecting the type of tile added
        JPanel tileTypePanel = new JPanel();
        tileTypePanel.setLayout(new FlowLayout());
        JLabel tileTypeLabel = new JLabel("Tile Type:");
        tileTypeComboBox = new JComboBox<>(EditStatusViewModel.TILE_TYPES);
        tileTypePanel.add(tileTypeLabel);
        tileTypePanel.add(tileTypeComboBox);

        // Combo box event to inform the tile selector for updating destination
        tileTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                final String item = itemEvent.getItem().toString().toLowerCase();
                if (List.of("hand", "dora", "uradora").contains(item)) {
                    notifier.notifyPropertyChange("target", item);
                }
            }
        });

        // Constructs panel with combo box for how the player has won
        JPanel winTypePanel = new JPanel();
        winTypePanel.setLayout(new FlowLayout());
        JLabel winTypeLabel = new JLabel("Win Type:");
        winTypeComboBox = new JComboBox<>(EditStatusViewModel.WIN_TYPES);
        winTypeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(winTypeComboBox)) {
                    String selectedItem = (String) winTypeComboBox.getSelectedItem();
                    Boolean[] newAttributes = preHandleComboBoxSelection(winTypeComboBox, selectedItem);

                    final EditStatusState currentState = editStatusViewModel.getState();

                    editStatusController.execute("winType", newAttributes, currentState.getNumAkadora(),
                            currentState.getSeatWind(), currentState.getRoundWind(), selectedItem,
                            playerName);
                }
            }
        });
        winTypePanel.add(winTypeLabel);
        winTypePanel.add(winTypeComboBox);

        // Constructs panel with combo box for selecting round wind
        JPanel roundWindPanel = new JPanel();
        roundWindPanel.setLayout(new FlowLayout());
        JLabel roundWindLabel = new JLabel("Round Wind:");
        roundWindComboBox = new JComboBox<>(EditStatusViewModel.WINDS);
        roundWindComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(roundWindComboBox)) {
                    String selectedItem = (String) roundWindComboBox.getSelectedItem();
                    final EditStatusState currentState = editStatusViewModel.getState();

                    editStatusController.execute("roundWind", currentState.getAttributes(), currentState.getNumAkadora(),
                            currentState.getSeatWind(), selectedItem, currentState.getWinType(),
                            playerName);
                }
            }
        });
        roundWindPanel.add(roundWindLabel);
        roundWindPanel.add(roundWindComboBox);

        // Constructs panel with combo box for selecting seat wind
        JPanel seatWindPanel = new JPanel();
        seatWindPanel.setLayout(new FlowLayout());
        JLabel seatWindLabel = new JLabel("Seat Wind:");
        seatWindComboBox = new JComboBox<>(EditStatusViewModel.WINDS);
        seatWindComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(seatWindComboBox)) {
                    String selectedItem = (String) seatWindComboBox.getSelectedItem();
                    Boolean[] newAttributes = preHandleComboBoxSelection(seatWindComboBox, selectedItem);
                    final EditStatusState currentState = editStatusViewModel.getState();

                    editStatusController.execute("seatWind", newAttributes, currentState.getNumAkadora(),
                            selectedItem, currentState.getRoundWind(), currentState.getWinType(),
                            playerName);
                }
            }
        });
        seatWindPanel.add(seatWindLabel);
        seatWindPanel.add(seatWindComboBox);

        // Constructs panel with spinner for selecting the number of Akadora
        JPanel akadoraPanel = new JPanel();
        akadoraPanel.setLayout(new FlowLayout());
        JLabel akadoraLabel = new JLabel("Akadora:");
        akadoraSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 3, 1));
        akadoraSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent evt) {
                int newValue = (int) akadoraSpinner.getValue();
                final EditStatusState currentState = editStatusViewModel.getState();
                editStatusController.execute("numAkadora", currentState.getAttributes(), newValue,
                        currentState.getSeatWind(), currentState.getRoundWind(), currentState.getWinType(),
                        playerName);
            }
        });
        akadoraPanel.add(akadoraLabel);
        akadoraPanel.add(akadoraSpinner);

        // Add all combo box and spinner panels to a big panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(tileTypePanel);
        leftPanel.add(winTypePanel);
        leftPanel.add(roundWindPanel);
        leftPanel.add(seatWindPanel);
        leftPanel.add(akadoraPanel);

        // Construct panel for the list of checkboxes
        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));

        // Constructs panel for each checkbox & label
        this.checkBoxes = addCheckBoxes(EditStatusViewModel.ATTRIBUTES);
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.getText().equals("One-shot") || checkBox.getText().equals("Riichi") ||
                    checkBox.getText().equals("Double Riichi")) {
                checkBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        final EditStatusState currentState = editStatusViewModel.getState();
                        Boolean[] newAttributes = preHandleCheckBoxSelection(checkBox);
                        editStatusController.execute("attributes", newAttributes, currentState.getNumAkadora(),
                                currentState.getSeatWind(), currentState.getRoundWind(), currentState.getWinType(),
                                playerName);
                    }
                });
            }
            else {
                checkBox.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        Boolean[] newAttributes = getAttributes();
                        final EditStatusState currentState = editStatusViewModel.getState();
                        editStatusController.execute("attributes", newAttributes, currentState.getNumAkadora(),
                                currentState.getSeatWind(), currentState.getRoundWind(), currentState.getWinType(),
                                playerName);
                    }
                });
            }
            checkboxPanel.add(checkBox);
        }

        initializeCheckBoxes();

        // Add all components to this panel
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(leftPanel);
        this.add(checkboxPanel);
    }

    private void initializeCheckBoxes() {
        // Disable certain checkboxes based on initial combo box selections
        checkBoxes.get(EditStatusViewModel.ONE_SHOT_INDEX).setEnabled(false);
        checkBoxes.get(EditStatusViewModel.UNDER_THE_RIVER_INDEX).setEnabled(false);
        checkBoxes.get(EditStatusViewModel.AFTER_A_KAN_INDEX).setEnabled(false);
        checkBoxes.get(EditStatusViewModel.ROBBING_A_KAN_INDEX).setEnabled(false);
        checkBoxes.get(EditStatusViewModel.CHIIHOU_INDEX).setEnabled(false);
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

    private void addCheckBox(List<JCheckBox> checkBoxes, String checkBoxName) {
        JCheckBox checkBox = new JCheckBox(checkBoxName);
        checkBox.setSelected(false);
        checkBox.addActionListener(this);
        checkBoxes.add(checkBox);
    }

    private List<JCheckBox> addCheckBoxes(String[] checkBoxNames) {
        List<JCheckBox> checkBoxes = new ArrayList<>();
        for (String checkBoxName : checkBoxNames) {
            addCheckBox(checkBoxes, checkBoxName);
        }
        return checkBoxes;
    }

    private Boolean[] preHandleComboBoxSelection(JComboBox<String> comboBox, String selectedItem) {
        Boolean[] newAttributes = getAttributes();
        // Set disabled checkboxes to false
        if (comboBox.equals(winTypeComboBox)) {
            if ("Tsumo".equals(selectedItem)) {
                newAttributes[EditStatusViewModel.UNDER_THE_RIVER_INDEX] = false;
                newAttributes[EditStatusViewModel.ROBBING_A_KAN_INDEX] = false;
            }
            else if ("Ron".equals(selectedItem)) {
                newAttributes[EditStatusViewModel.UNDER_THE_SEA_INDEX] = false;
            }
        }
        if (comboBox.equals(seatWindComboBox)) {
            if ("Ton".equals(selectedItem)) {
                newAttributes[EditStatusViewModel.CHIIHOU_INDEX] = false;
            }
            else {
                newAttributes[EditStatusViewModel.TENHOU_INDEX] = false;
            }
        }
        return newAttributes;
    }

    /**
     * Method for handling selection events on combo boxes.
     * @param comboBox the combo box where the selection event occurred
     * @param selectedItem the item (String) that was selected
     */
    private void handleComboBoxSelection(JComboBox<String> comboBox, String selectedItem) {
        System.out.println("ComboBox selection handled for: " + selectedItem);

        // Disable/enable "Under the River/Sea" checkboxes according to win type
        if (comboBox.equals(winTypeComboBox)) {
            if ("Tsumo".equals(selectedItem)) {
                disableCheckBox("Under the River");
                enableCheckBox("Under the Sea");
                disableCheckBox("Robbing a Kan");
            }
            else if ("Ron".equals(selectedItem)) {
                disableCheckBox("Under the Sea");
                enableCheckBox("Under the River");
                enableCheckBox("Robbing a Kan");
            }
        }
        // Disable/enable "Tenhou/Chiihou" checkboxes according to seat wind
        if (comboBox.equals(seatWindComboBox)) {
            // If the player is East (starts the first), they can only win big at beginning by Tenhou
            if ("Ton".equals(selectedItem))  {
                System.out.println("Tenhou enabled");
                disableCheckBox("Chiihou");
                enableCheckBox("Tenhou");
            }
            // If the player is not East, they can only win big at beginning by Chiihou
            else {
                System.out.println("Chiihou enabled");
                disableCheckBox("Tenhou");
                enableCheckBox("Chiihou");
            }
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * Method for handling selection events on checkboxes.
     * @param checkBox the checkbox on select.
     */
    private Boolean[] preHandleCheckBoxSelection(JCheckBox checkBox) {
        System.out.println("Pre handle checkbox");
        Boolean[] newAttributes = getAttributes();

        if (checkBox.getText().equals("Riichi")) {
            if (checkBox.isSelected()) {
                // If there is Riichi, set Double Riichi to false
                newAttributes[EditStatusViewModel.DOUBLE_RIICHI_INDEX] = false;
            }
        }
        else if (checkBox.getText().equals("Double Riichi")) {
            if (checkBox.isSelected()) {
                // If there is Double Riichi, set Riichi to false
                newAttributes[EditStatusViewModel.RIICHI_INDEX] = false;
            }
        }
        // If both Riichi and Double Riichi are unselected, set One-shot to false
        if (!(checkBoxes.get(EditStatusViewModel.RIICHI_INDEX).isSelected() ||
                checkBoxes.get(EditStatusViewModel.DOUBLE_RIICHI_INDEX).isSelected())) {
            newAttributes[EditStatusViewModel.ONE_SHOT_INDEX] = false;
        }
        return newAttributes;
    }

    /**
     * Method for handling selection events on checkboxes.
     */
    private void handleCheckBoxSelection() {
        boolean isRiichi = checkBoxes.get(EditStatusViewModel.RIICHI_INDEX).isSelected();
        if (isRiichi) {
            // If there is Riichi, disable Double Riichi and enable One-shot
            enableCheckBox("One-shot");
            disableCheckBox("Double Riichi");
        }
        else {
            // If Riichi is unselected, enable Double Riichi
            enableCheckBox("Double Riichi");
        }
        boolean isDoubleRiichi = checkBoxes.get(EditStatusViewModel.DOUBLE_RIICHI_INDEX).isSelected();
        if (isDoubleRiichi) {
            // If Double Riichi is selected, disable Riichi and enable One-shot
            enableCheckBox("One-shot");
            disableCheckBox("Riichi");
        }
        else {
            // If Double Riichi is unselected, enable Riichi
            enableCheckBox("Riichi");
        }
        // If both Riichi and Double Riichi are unselected, disable One-shot
        if (!(checkBoxes.get(EditStatusViewModel.RIICHI_INDEX).isSelected() ||
                checkBoxes.get(EditStatusViewModel.DOUBLE_RIICHI_INDEX).isSelected())) {
            disableCheckBox("One-shot");
        }
        this.revalidate();
        this.repaint();
    }

    /**
     * Disable and deselect a checkbox with the specified name.
     * @param checkBoxName name/label of the checkbox, as shown on the GUI
     */
    private void disableCheckBox(String checkBoxName) {
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.getText().equals(checkBoxName)) {
                SwingUtilities.invokeLater(() -> {
                    checkBox.setEnabled(false);
                    checkBox.setSelected(false);
                });
//                checkBox.setEnabled(false);
//                checkBox.setSelected(false);
            }
        }
        System.out.println("Disabled checkbox: " + checkBoxName);
    }

    /**
     * Enable a checkbox with the specified name.
     * @param checkBoxName name/label of the checkbox, as shown on the GUI
     */
    private void enableCheckBox(String checkBoxName) {
        for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.getText().equals(checkBoxName)) {
                checkBox.setEnabled(true);
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Update what the user is looking at, which is the checkboxes in this view
        // Get the object where the event occurred
        Object source = evt.getSource();

        final EditStatusState state = (EditStatusState) evt.getNewValue();
        System.out.println(state);
        String propertyName = evt.getPropertyName();
        switch (propertyName) {
            case "attributes":
                System.out.println("Property change event for attributes occurred");
                Boolean[] attributes = state.getAttributes();
                for (int i = 0; i < attributes.length; i++) {
                    checkBoxes.get(i).setSelected(attributes[i]);
                }
                handleCheckBoxSelection();
                break;
            case "seatWind":
                System.out.println("Property change event for seatWind occurred");
                String seatWind = state.getSeatWind();
                handleComboBoxSelection(seatWindComboBox, seatWind);
                break;
            case "winType":
                System.out.println("Property change event for winType occurred");
                String winType = state.getWinType();
                handleComboBoxSelection(winTypeComboBox, winType);
                break;
        }
    }

    public void setEditStatusController(EditStatusController editStatusController) {
        this.editStatusController = editStatusController;
    }

    public void setNotifier(TileSelectorPropertyUpdateNotifier notifier) {
        this.notifier = notifier;
    }

    // For debugging purposes
    public String checkBoxesToString() {
        StringBuilder sb = new StringBuilder();
        for (JCheckBox checkBox : checkBoxes) {
            sb.append(checkBox.getText()).append(", ");
        }
        return sb.toString();
    }

    public List<JCheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    private Boolean[] getAttributes() {
        Boolean[] newAttributes = new Boolean[EditStatusViewModel.ATTRIBUTES.length];
        for (int i = 0; i < checkBoxes.size(); i++) {
            newAttributes[i] = checkBoxes.get(i).isSelected();
        }
        return newAttributes;
    }
}