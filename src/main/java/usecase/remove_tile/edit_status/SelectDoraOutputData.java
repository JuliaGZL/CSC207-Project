package usecase.remove_tile.edit_status;

import mahjong.BaseTile;

/**
 * Output Data for the Select Dora Use Case.
 */
public class SelectDoraOutputData {
    private final BaseTile[] doraIndicators;

    private final boolean useCaseFailed;

    public SelectDoraOutputData(BaseTile[] doraIndicators, boolean useCaseFailed) {
        this.doraIndicators = doraIndicators;
        this.useCaseFailed = useCaseFailed;
    }

    // Getters
    public BaseTile[] getDoraIndicators() {
        return doraIndicators;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
