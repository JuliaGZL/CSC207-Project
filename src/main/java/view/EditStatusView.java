package view;

import interface_adapter.edit_status.EditStatusController;
import interface_adapter.edit_status.EditStatusState;
import interface_adapter.edit_status.EditStatusViewModel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private EditStatusController editStatusController;

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
                    final EditStatusState currentState = editStatusViewModel.getState();

                    editStatusController.execute("seatWind", currentState.getAttributes(), currentState.getNumAkadora(),
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
            checkBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    Boolean[] newAttributes = getAttributes();
                    final EditStatusState currentState = editStatusViewModel.getState();
                    editStatusController.execute("attributes", newAttributes, currentState.getNumAkadora(),
                            currentState.getSeatWind(), currentState.getRoundWind(), currentState.getWinType(),
                            playerName);
                }
            });
            checkboxPanel.add(checkBox);
        }

        handleComboBoxSelection(winTypeComboBox, "Tsumo");

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
        if (comboBox.equals(winTypeComboBox)) {
            if ("Tsumo".equals(selectedItem)) {
                newAttributes[EditStatusViewModel.UNDER_THE_RIVER_INDEX] = false;
            }
            else if ("Ron".equals(selectedItem)) {
                newAttributes[EditStatusViewModel.UNDER_THE_SEA_INDEX] = false;
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
        // TODO: disable certain checkboxes based on combo box selections
        System.out.println("ComboBox selection handled for: " + selectedItem);
        if (comboBox.equals(winTypeComboBox)) {
            if ("Tsumo".equals(selectedItem)) {
                disableCheckBox("Under the River");
                enableCheckBox("Under the Sea");
            }
            else if ("Ron".equals(selectedItem)) {
                disableCheckBox("Under the Sea");
                enableCheckBox("Under the River");
            }
        }
        this.revalidate();
        this.repaint();
        // TODO: update the view model/state and use controller to update entities
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
        if ("winType".equals(evt.getPropertyName())) {
            System.out.println("Property change event for winType occurred");
            String winType = state.getWinType();
            handleComboBoxSelection(winTypeComboBox, winType);
        }
    }

    public void setEditStatusController(EditStatusController editStatusController) {
        this.editStatusController = editStatusController;
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