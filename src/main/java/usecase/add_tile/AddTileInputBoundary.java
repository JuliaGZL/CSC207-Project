package usecase.add_tile;

/**
 * Input Boundary for the add_tile use case.
 */
public interface AddTileInputBoundary {
    /**
     * Executes the add_tile use case.
     * @param inputData the input data
     */
    void execute(AddTileInputData inputData);
}
