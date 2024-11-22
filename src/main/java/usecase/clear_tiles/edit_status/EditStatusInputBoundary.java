package usecase.clear_tiles.edit_status;

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
     * Executes the switch to select dora use case.
     */
    void switchToSelectDoraView();
}
