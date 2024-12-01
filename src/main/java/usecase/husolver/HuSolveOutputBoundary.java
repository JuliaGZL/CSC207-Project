package usecase.husolver;

/**
 * Output boundary for the Hu solver.
 */
public interface HuSolveOutputBoundary {
  /**
   * Prepares the success view for the use case.
   *
   * @param outputData the output data
   */
  void prepareSuccessView(HuSolverOutputData outputData);

  /**
   * Prepares the failure view for the use case.
   *
   * @param errorMessage the explanation of the failure
   */
  void prepareFailView(String errorMessage);
}
