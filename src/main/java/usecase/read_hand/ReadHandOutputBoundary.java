package usecase.read_hand;

/**
 * The output boundary for the read hand use case.
 */
public interface ReadHandOutputBoundary {
    /**
     * Prepares the success view for the use case.
     * @param outputData the output data
     */
    void prepareSuccessView(ReadHandOutputData outputData);

    /**
     * Prepares the failure view for the use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
