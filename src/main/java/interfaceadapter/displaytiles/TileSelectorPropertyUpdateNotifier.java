package interfaceadapter.displaytiles;

import interfaceadapter.edittiles.TileSelectorViewModel;

/**
 * This class is responsible for notifying the TileSelectorViewModel about property changes.
 * It facilitates communication from the tile display panels and status editor panel
 * toward the tile selector view.
 */
public class TileSelectorPropertyUpdateNotifier {
  private final TileSelectorViewModel viewModel;

  /**
   * Constructs a TileSelectorPropertyUpdateNotifier with the specified view model.
   *
   * @param viewModel the TileSelectorViewModel to be notified of property changes
   */
  public TileSelectorPropertyUpdateNotifier(TileSelectorViewModel viewModel) {
    this.viewModel = viewModel;
  }

  /**
   * Issues a property update notification to the view model.
   *
   * @param event   the name of the event (e.g., "target"), or an empty string (but not null)
   * @param message the message containing the new value to update
   */
  public void notifyPropertyChange(String event, String message) {
    viewModel.getState().setMessage(message);
    viewModel.firePropertyChanged(event);
  }
}
