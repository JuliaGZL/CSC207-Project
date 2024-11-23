package use_case.edit_status;

import java.util.Map;

import mahjong.BaseTile;

/**
 * The output data for the edit status use case.
 */
public class EditStatusOutputData {

    private final Boolean[] attributes;
    private final Boolean[] redDora;
    private Map<BaseTile, Integer> doraCounts;

    private final boolean useCaseFailed;

    public EditStatusOutputData(Boolean[] attributes, Boolean[] redDora, Map<BaseTile, Integer> doraCounts, boolean useCaseFailed) {
        this.attributes = attributes;
        this.redDora = redDora;
        this.doraCounts = doraCounts;
        this.useCaseFailed = useCaseFailed;
    }

    // Getters
    public Boolean[] getAttributes() {
        return attributes;
    }

    public Boolean[] getRedDora() {
        return redDora;
    }

    public Map<BaseTile, Integer> getDoraCounts() {
        return doraCounts;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
