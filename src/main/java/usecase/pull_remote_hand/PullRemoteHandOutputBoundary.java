package usecase.pull_remote_hand;

/**
 * Output Boundary for the pull hand from discord use case.
 */
public interface PullRemoteHandOutputBoundary {
    /**
     * Prepares the success view for the use case.
     * @param outputData the output data
     */
    void prepareSuccessView(PullRemoteHandOutputData outputData);

    /**
     * Prepares the failure view for the use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
