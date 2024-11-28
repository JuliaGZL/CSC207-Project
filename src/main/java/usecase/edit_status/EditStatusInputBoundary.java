package usecase.edit_status;

/**
 * The Edit Status Use Case.
 */
public interface EditStatusInputBoundary {

    /**
     * Execute the Edit Status Use Case.
     * @param editStatusInputData the input data for this use case
     */
    void execute(EditStatusInputData editStatusInputData);

    /**
     * Execute the Edit Status Use Case.
     * @param updateName the element updated as inputted in ViewModel
     * @param editStatusInputData the input data for this use case
     */
    void execute(String updateName, EditStatusInputData editStatusInputData);

    /**
     * Executes the switch to select dora use case.
     * @param inputData the input data for this use case
     */
    void switchToSelectDoraView(EditStatusInputData inputData);
}
