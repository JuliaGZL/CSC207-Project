package interface_adapter.player_events;

import usecase.change_player.ChangePlayerInputBoundary;
import usecase.change_player.ChangePlayerInputData;

public class ChangePlayerController {
    private final ChangePlayerInputBoundary interactor;

    public ChangePlayerController(ChangePlayerInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute a change player use case.
     * @param playerName name of player to switch to
     */
    public void execute(String playerName) {
        interactor.execute(new ChangePlayerInputData(playerName));
    }
}
