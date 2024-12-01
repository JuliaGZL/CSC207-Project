package usecase.pullremotehand;

/**
 * Interface for the PullRemoteHand use case input boundary.
 */
public interface PullRemoteHandInputBoundary {
  /**
   * Executes the use case.
   *
   * @param inputData the input data
   */
  public void execute(PullRemoteHandInputData inputData);
}
