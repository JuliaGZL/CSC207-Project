package view;

import interface_adapter.edit_status.EditStatusController;
import interface_adapter.edit_status.EditStatusViewModel;
import interface_adapter.edit_tiles.UpdateEnabledTileController;

import javax.swing.*;
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

    // TODO: this is very awful. Let's actually create a use case later.
    // NOTE: this is just for updating the addTileTarget property.
    // originally planned to create a use case, but looks like it is overcomplicating.
    private TileSelectorView tileSelectorView;
    private UpdateEnabledTileController updateEnabledTileController;

    /**
     * Constructs a EditStatusView object with the specified view model and controller.
     * @param editStatusViewModel the view model for editing gameplay statuses.
     */
    public EditStatusView(EditStatusViewModel editStatusViewModel) {
        this.editStatusViewModel = editStatusViewModel;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Constructs panel with combo box for selecting the type of tile added
        JPanel tileTypePanel = new JPanel();
        tileTypePanel.setLayout(new FlowLayout());
        JLabel tileTypeLabel = new JLabel("Tile Type:");
        JComboBox<String> tileTypeComboBox = new JComboBox<>(EditStatusViewModel.TILE_TYPES);
        tileTypePanel.add(tileTypeLabel);
        tileTypePanel.add(tileTypeComboBox);

        // TODO: change this to an actual use case.
        // Combo box event to inform the tile selector for updating destination
        tileTypeComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                switch (itemEvent.getItem().toString()) {
                    case "Hand":
                        tileSelectorView.setTileAddTarget("hand");
                        updateEnabledTileController.execute(tileSelectorView.getPlayerName(), "hand");
                        break;
                    case "Dora":
                        tileSelectorView.setTileAddTarget("dora");
                        updateEnabledTileController.execute(tileSelectorView.getPlayerName(), "dora");
                        break;
                    case "Uradora":
                        tileSelectorView.setTileAddTarget("uradora");
                        updateEnabledTileController.execute(tileSelectorView.getPlayerName(), "uradora");
                        break;
                }
            }
        });

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

        // Constructs panel with spinner for selecting the number of Akadora
        JPanel akadoraPanel = new JPanel();
        akadoraPanel.setLayout(new FlowLayout());
        JLabel akadoraLabel = new JLabel("Akadora:");
        JSpinner akadoraSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 3, 1));
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

    public void setEditStatusController(EditStatusController editStatusController) {
        this.editStatusController = editStatusController;
    }

    public void setTileSelectorView(TileSelectorView tileSelectorView) {
        this.tileSelectorView = tileSelectorView;
    }

    public void setUpdateEnabledTileController(UpdateEnabledTileController updateEnabledTileController) {
        this.updateEnabledTileController = updateEnabledTileController;
    }
}