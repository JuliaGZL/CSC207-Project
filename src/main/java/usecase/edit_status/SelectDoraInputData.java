package usecase.edit_status;

/**
 * The Input Data for the Select Dora Use Case.
 */
public class SelectDoraInputData {
    private final Boolean[][] doraSelections;

    public SelectDoraInputData(Boolean[][] doraSelections) {
        this.doraSelections = doraSelections;
    }

    // Getters
    public Boolean[][] getDoraSelections() {
        return doraSelections;
    }
}
