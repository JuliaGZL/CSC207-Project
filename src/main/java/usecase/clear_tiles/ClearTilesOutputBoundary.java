package usecase.clear_tiles;

/**
 * The output boundary for the add_tile use case.
 */
public interface ClearTilesOutputBoundary {
    /**
     * Prepares the success view for the use case.
     * 
     * @param outputData the output data
     */
    void prepareSuccessView(ClearTilesOutputData outputData);

    /**
     * Prepares the failure view for the use case.
     * 
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
