package interface_adapter.player_events;

import usecase.hu_solver.HuSolverInputBoundary;
import usecase.hu_solver.HuSolverInputData;

/**
 * Controller for the Hu solver.
 */
public class HuSolverController {
    private final HuSolverInputBoundary interactor;

    public HuSolverController(HuSolverInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Execute the Hu solver that calculates score.
     * 
     * @param playerName name of the player
     */
    public void execute(String playerName) {
        interactor.execute(new HuSolverInputData(playerName));
    }
}
