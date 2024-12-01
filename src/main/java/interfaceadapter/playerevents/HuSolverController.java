package interfaceadapter.playerevents;

import usecase.husolver.HuSolverInputBoundary;
import usecase.husolver.HuSolverInputData;

/**
 * Controller for the Hu solver.
 */
public class HuSolverController {
  private final HuSolverInputBoundary interactor;

  /**
   * Constructs a HuSolverController with the given interactor.
   *
   * @param interactor the interactor to be used by this controller
   */
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
