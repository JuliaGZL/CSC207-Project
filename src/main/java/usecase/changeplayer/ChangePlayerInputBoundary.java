package usecase.changeplayer;

/**
 * Input boundary of the change player use case.
 */
public interface ChangePlayerInputBoundary {
  /**
   * Executes the add_tile use case.
   *
   * @param inputData the input data
   */
  void execute(ChangePlayerInputData inputData);
}
