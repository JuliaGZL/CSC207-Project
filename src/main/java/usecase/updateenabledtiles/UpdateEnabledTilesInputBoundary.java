package usecase.updateenabledtiles;

/**
 * Input boundary of update enabled tile use case.
 */
public interface UpdateEnabledTilesInputBoundary {
  /**
   * Executes the add_tile use case.
   *
   * @param inputData the input data
   */
  void execute(UpdateEnabledTileInputData inputData);
}
