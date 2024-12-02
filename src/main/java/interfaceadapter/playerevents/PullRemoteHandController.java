package interfaceadapter.playerevents;

import usecase.pullremotehand.PullRemoteHandInputBoundary;
import usecase.pullremotehand.PullRemoteHandInputData;

/**
 * Controller for handling the pull remote hand use case.
 */
public class PullRemoteHandController {

  /**
   * The interactor to be used by this controller.
   */
  private final PullRemoteHandInputBoundary interactor;

  /**
   * Constructs a PullRemoteHandController with the given interactor.
   *
   * @param interactor the interactor to be used by this controller
   */
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
