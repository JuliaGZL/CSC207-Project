package usecase.edit_status;

import mahjong.BaseTiles;

/**
 * Output Data for the Select Dora Use Case.
 */
public class SelectDoraOutputData {
    private final BaseTiles[] doraIndicators;

    private final boolean useCaseFailed;

    public SelectDoraOutputData(BaseTiles[] doraIndicators, boolean useCaseFailed) {
        this.doraIndicators = doraIndicators;
        this.useCaseFailed = useCaseFailed;
    }

    // Getters
    public BaseTiles[] getDoraIndicators() {
        return doraIndicators;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
