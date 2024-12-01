package interface_adapter.player_events;

import usecase.pull_remote_hand.PullRemoteHandInputBoundary;
import usecase.pull_remote_hand.PullRemoteHandInputData;

public class PullRemoteHandController {
    private final PullRemoteHandInputBoundary interactor;

    public PullRemoteHandController(PullRemoteHandInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Entry to run the use case.
     * 
     * @param playerName name of the current player
     */
    public void execute(String playerName) {
        interactor.execute(new PullRemoteHandInputData(playerName));
    }
}
