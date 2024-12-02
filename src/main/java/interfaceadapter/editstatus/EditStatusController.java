package interfaceadapter.editstatus;

import usecase.editstatus.EditStatusInputBoundary;
import usecase.editstatus.EditStatusInputData;

/**
 * Controller for editing status.
 */
public class EditStatusController {
  private EditStatusInputBoundary editStatusUseCaseInteractor;

  /**
   * Constructs an EditStatusController with the given interactor.
   *
   * @param editStatusUseCaseInteractor the interactor to handle the use case
   */
  public EditStatusController(EditStatusInputBoundary editStatusUseCaseInteractor) {
    this.editStatusUseCaseInteractor = editStatusUseCaseInteractor;
  }

  /**
   * Executes the edit status use case with the given parameters.
   *
   * @param attributes the attributes to update
   * @param numAkadora the number of Akadora
   * @param seatWind   the seat wind
   * @param roundWind  the round wind
   * @param winType    the type of win
   * @param playerName the name of the player
   */
  public void execute(
      Boolean[] attributes, int numAkadora,
      String seatWind, String roundWind, String winType,
      String playerName) {
    final EditStatusInputData editStatusInputData = new EditStatusInputData(
        attributes, numAkadora, seatWind,
        roundWind, winType, playerName);

    editStatusUseCaseInteractor.execute(editStatusInputData);
  }

  /**
   * Executes the edit status use case with the given parameters and update name.
   *
   * @param updateName the name of the update
   * @param attributes the attributes to update
   * @param numAkadora the number of Akadora
   * @param seatWind   the seat wind
   * @param roundWind  the round wind
   * @param winType    the type of win
   * @param playerName the name of the player
   */
  public void execute(
      String updateName, Boolean[] attributes,
      int numAkadora, String seatWind, String roundWind,
      String winType, String playerName) {
    final EditStatusInputData editStatusInputData = new EditStatusInputData(
        attributes, numAkadora, seatWind,
        roundWind, winType, playerName);

    editStatusUseCaseInteractor.execute(updateName, editStatusInputData);
  }
}
