package interface_adapter.display_tiles;

import interface_adapter.edit_tiles.TileSelectorViewModel;

/**
 * This is for the communication from the tile display panels and status editor
 * panel toward the tile selector view.
 */
public class TileSelectorPropertyUpdateNotifier {
    private final TileSelectorViewModel viewModel;

    public TileSelectorPropertyUpdateNotifier(TileSelectorViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Issue a property update notification.
     * 
     * @param event   name of the event - e.g. "target", or basically an empty
     *                String - but not null!
     * @param message the message (the new value to update)
     */
    public void notifyPropertyChange(String event, String message) {
        viewModel.getState().setMessage(message);
        viewModel.firePropertyChanged(event);
    }
}
