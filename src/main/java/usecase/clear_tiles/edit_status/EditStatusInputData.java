package usecase.clear_tiles.edit_status;

import mahjong.BaseTile;

/**
 * The input data for the Edit Status Use Case.
 */
public class EditStatusInputData {
    private final Boolean[] attributes; // Attributes of the hand in this round
    private final Boolean[] redDora; // Represents whether there is Man/Pin/Sou red dora
    private final BaseTile doraIndicators; // The dora indicators (0-5 indicators)

    public EditStatusInputData(Boolean[] attributes, Boolean[] redDora, BaseTile doraIndicators) {
        this.attributes = attributes;
        this.redDora = redDora;
        this.doraIndicators = doraIndicators;
    }

    // Getters
    public Boolean[] getAttributes() {
        return attributes;
    }

    public Boolean[] getRedDora() {
        return redDora;
    }

    public BaseTile getDoraIndicators() {
        return doraIndicators;
    }
}
