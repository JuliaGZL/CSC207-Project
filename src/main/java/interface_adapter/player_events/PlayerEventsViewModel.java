package interface_adapter.player_events;

import interface_adapter.ViewModel;

/**
 * View model for the player events panel.
 */
public class PlayerEventsViewModel extends ViewModel<PlayerEventsState> {
    public PlayerEventsViewModel() {
        super("player_events_panel");
        this.setState(new PlayerEventsState());
    }
}
