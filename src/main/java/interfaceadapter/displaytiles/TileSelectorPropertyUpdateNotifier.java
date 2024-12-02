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

package interfaceadapter.displaytiles;

import interfaceadapter.edittiles.TileSelectorViewModel;

/**
 * This class is responsible for notifying the TileSelectorViewModel about
 * property changes.
 * It facilitates communication from the tile display panels and status editor
 * panel
 * toward the tile selector view.
 */
public class TileSelectorPropertyUpdateNotifier {
  private final TileSelectorViewModel viewModel;

  /**
   * Constructs a TileSelectorPropertyUpdateNotifier with the specified view
   * model.
   *
   * @param viewModel the TileSelectorViewModel to be notified of property changes
   */
  public TileSelectorPropertyUpdateNotifier(TileSelectorViewModel viewModel) {
    this.viewModel = viewModel;
  }

  /**
   * Issues a property update notification to the view model.
   *
   * @param event   the name of the event (e.g., "target"), or an empty string
   *                (but not null)
   * @param message the message containing the new value to update
   */
  public void notifyPropertyChange(String event, String message) {
    viewModel.getState().setMessage(message);
    viewModel.firePropertyChanged(event);
  }
}
