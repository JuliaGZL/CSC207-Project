package view;

import interface_adapter.read_hand.ReadHandController;
import interface_adapter.read_hand.ReadHandState;
import interface_adapter.read_hand.ReadHandViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The view for reading the player's hand (as well as special attributes) out loud.
 * Note that this is only a button triggered by a keyboard shortcut and presented with sound.
 */
public class ReadHandView extends JButton implements ActionListener, PropertyChangeListener {
    // Shortcut (Ctrl + R) to trigger the reading of the player's hand
    private KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);

    private final ReadHandViewModel readHandViewModel;
    private ReadHandController readHandController;

    public ReadHandView(ReadHandViewModel readHandViewModel) {
        super("Read"); // set the text of the button to "Read"
//        setMnemonic(KeyEvent.VK_R); // set the mnemonic to 'R' (Alt + R will trigger the button)

        this.readHandViewModel = readHandViewModel;
        readHandViewModel.addPropertyChangeListener(this);

        // Add event for directly tapping the button
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                onEvent(evt);
            }
        });

        // Add event for keyboard shortcut
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "readAction");
        this.getActionMap().put("readAction", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                onEvent(evt);
            }
        });

    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * This method gets called when a bound property is changed.
     *
     * @param evt A PropertyChangeEvent object describing the event source
     *            and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ReadHandState state = (ReadHandState) evt.getNewValue();
        readOutLoud(state.getHandInfo());
    }

    /**
     * Invoked when the user taps the button or inputs keyboard shortcuts related to this ReadHandView.
     * @param evt
     */
    private void onEvent(ActionEvent evt) {
        if (evt.getSource().equals(ReadHandView.this)) {
            final ReadHandState currentState = readHandViewModel.getState();

            readHandController.execute(currentState.getPlayerName(), ReadHandViewModel.ATTRIBUTE_NAMES);
        }
    }

    /**
     * Read out the player's hand.
     */
    private void readOutLoud(String handInfo) {
        // Add read functionality here
        System.out.println("readOutLoud called");
    }

    public void setReadHandController(ReadHandController readHandController) {
        this.readHandController = readHandController;
    }
}
