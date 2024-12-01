package interfaceadapter.playerevents;

import usecase.changeplayer.ChangePlayerInputBoundary;
import usecase.changeplayer.ChangePlayerInputData;

/**
 * Controller for changing the player.
 */
public class ChangePlayerController {
  private final ChangePlayerInputBoundary interactor;

  /**
   * Constructs a ChangePlayerController with the given interactor.
   *
   * @param interactor the interactor to handle the change player use case
   */
  public ChangePlayerController(ChangePlayerInputBoundary interactor) {
    this.interactor = interactor;
  }

  /**
   * Execute a change player use case.
   *
   * @param playerName name of player to switch to
   */
  public void execute(String playerName) {
    interactor.execute(new ChangePlayerInputData(playerName));
  }
}
