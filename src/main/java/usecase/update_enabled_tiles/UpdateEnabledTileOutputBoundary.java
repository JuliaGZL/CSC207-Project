package usecase.update_enabled_tiles;

/**
 * Output boundary of update enabled tile use case.
 */
public interface UpdateEnabledTileOutputBoundary {
    /**
     * Prepares the success view for the use case.
     * @param outputData the output data
     */
    void prepareSuccessView(UpdateEnabledTileOutputData outputData);

    /**
     * Prepares the failure view for the use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
