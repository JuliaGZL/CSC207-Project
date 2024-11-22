package usecase.remove_tile.edit_status;

/**
 * The output boundary for the Select Dora Use Case.
 */
public interface SelectDoraOutputBoundary {
    /**
     * Prepares the success view for the Select Dora Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SelectDoraOutputData outputData);

    /**
     * Prepares the failure view for the Select Dora Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
