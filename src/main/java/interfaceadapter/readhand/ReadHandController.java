package interfaceadapter.readhand;

import usecase.readhand.ReadHandInputBoundary;
import usecase.readhand.ReadHandInputData;

/**
 * Controller for handling read hand operations.
 */
public class ReadHandController {
  private ReadHandInputBoundary readHandUseCaseInteractor;

  /**
   * Constructs a ReadHandController with the specified use case interactor.
   *
   * @param readHandUseCaseInteractor the use case interactor for reading hand data
   */
  public ReadHandController(ReadHandInputBoundary readHandUseCaseInteractor) {
    this.readHandUseCaseInteractor = readHandUseCaseInteractor;
  }

  /**
   * Executes the read hand operation with the given player name and attribute names.
   *
   * @param playerName the name of the player
   * @param attributeNames the names of the attributes to be read
   */
  public void execute(String playerName, String[] attributeNames) {
    final ReadHandInputData readHandInputData = new ReadHandInputData(playerName, attributeNames);

    readHandUseCaseInteractor.execute(readHandInputData);
  }
}
