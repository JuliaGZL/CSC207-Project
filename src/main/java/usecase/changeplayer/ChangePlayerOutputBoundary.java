package usecase.changeplayer;

/**
 * Input boundary of the change player use case.
 */
public interface ChangePlayerOutputBoundary {
  /**
   * Prepares the success view for the use case.
   *
   * @param outputData the output data
   */
  void prepareSuccessView(ChangePlayerOutputData outputData);

  /**
   * Prepares the failure view for the use case.
   *
   * @param errorMessage the explanation of the failure
   */
  void prepareFailView(String errorMessage);
}
