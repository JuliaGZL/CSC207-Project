package interface_adapter.edit_status;

import mahjong.BaseTiles;

/**
 * The state information representing the statuses being edited.
 */
public class EditStatusState {

    private Boolean[] attributes = new Boolean[5];
    private Boolean[] redDoraStatus = new Boolean[3];
    private BaseTiles[] doraIndicators = new BaseTiles[5];
    private String selectIndicatorError;

    // Setters and getters
    public Boolean[] getAttributes() {
        return attributes;
    }

    public Boolean[] getRedDoraStatus() {
        return redDoraStatus;
    }

    public BaseTiles[] getDoraIndicators() {
        return doraIndicators;
    }

    public String getSelectIndicatorError() {
        return selectIndicatorError;
    }

    public void setAttributes(Boolean[] attributes) {
        this.attributes = attributes;
    }

    public void setRedDoraStatus(Boolean[] redDoraStatus) {
        this.redDoraStatus = redDoraStatus;
    }

    public void setDoraIndicators(BaseTiles[] doraIndicators) {
        this.doraIndicators = doraIndicators;
    }

    public void setSelectIndicatorError(String selectIndicatorError) {
        this.selectIndicatorError = selectIndicatorError;
    }

    @Override
    public String toString() {
        return "EditStatusState{" +
                "editStatus=" + attributes +
                ", redDoraStatus=" + redDoraStatus +
                ", doraIndicators=" + doraIndicators +
                '}';
    }
}
