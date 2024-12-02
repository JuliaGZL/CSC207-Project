package usecase.cleartiles;

/**
 * Input Boundary for the add_tile use case.
 */
public interface ClearTilesInputBoundary {
  /**
   * Executes the add_tile use case.
   *
   * @param inputData the input data
   */
  void execute(ClearTilesInputData inputData);
}
