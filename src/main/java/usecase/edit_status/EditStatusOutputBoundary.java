package usecase.edit_status;

/**
 * The output boundary for the Edit Status Use Case.
 */
public interface EditStatusOutputBoundary {
    /**
     * Prepares the success view for the Edit Status Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(EditStatusOutputData outputData);

    /**
     * Prepares the failure view for the Edit Status Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Select Dora View.
     * @param outputData the output data
     */
    void switchToSelectDoraView(EditStatusOutputData outputData);

    /**
     * Switches to add tiles to hand.
     */
    void switchToSelectForHand();
}
