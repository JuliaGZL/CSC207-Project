package interfaceadapter.playerevents;

import interfaceadapter.ViewModel;

/**
 * View model for the player events panel.
 */
public class PlayerEventsViewModel extends ViewModel<PlayerEventsState> {
  /**
   * Constructs a new PlayerEventsViewModel.
   */
  public PlayerEventsViewModel() {
    super("player_events_panel");
    this.setState(new PlayerEventsState());
  }
}
