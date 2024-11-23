package usecase.add_tile;

/**
 * The output boundary for the add_tile use case.
 */
public interface AddTileOutputBoundary {
    /**
     * Prepares the success view for the use case.
     * @param outputData the output data
     */
    void prepareSuccessView(AddTileOutputData outputData);

    /**
     * Prepares the failure view for the use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
