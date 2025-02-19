/*
 Copyright (c) 2024 YAizhou, li-tianchu, JuliaGZL, TurkeyBilly

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package view;

import interfaceadapter.readhand.ReadHandController;
import interfaceadapter.readhand.ReadHandState;
import interfaceadapter.readhand.ReadHandViewModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import utils.TextToSpeech;

/**
 * The view for reading the player's hand (as well as special attributes) out
 * loud.
 * Note that this is only a button triggered by a keyboard shortcut and
 * presented with sound.
 */
public class ReadHandView extends JButton implements ActionListener, PropertyChangeListener {
  // Shortcut (Ctrl + R) to trigger the reading of the player's hand
  private KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);

  private final ReadHandViewModel readHandViewModel;
  private ReadHandController readHandController;

  /**
   * Constructs a ReadHandView with the specified ReadHandViewModel.
   *
   * @param readHandViewModel the view model for reading the hand
   */
  public ReadHandView(ReadHandViewModel readHandViewModel) {
    super("Read (Ctrl+R)"); // set the text of the button to "Read"
    // setMnemonic(KeyEvent.VK_R); // set the mnemonic to 'R' (Alt + R will trigger
    // the button)

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
   * Invoked when the user taps the button or inputs keyboard shortcuts related to
   * this ReadHandView.
   *
   * @param evt the event that triggered this action
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
    // System.out.println("readOutLoud called");
    // System.out.println(handInfo);
    TextToSpeech ttsInstance = TextToSpeech.getInstance();
    ttsInstance.speakInThread(handInfo);
  }

  public void setReadHandController(ReadHandController readHandController) {
    this.readHandController = readHandController;
  }
}
