package interface_adapter.edit_status;

import mahjong.BaseTiles;

/**
 * The state information representing the statuses being edited.
 */
public class EditStatusState {

    private Boolean[] editStatus = new Boolean[5];
    private Boolean[] redDoraStatus = new Boolean[3];
    private BaseTiles[] doraIndicators = new BaseTiles[5];
    private String selectIndicatorError;

    // Setters and getters
    public Boolean[] getEditStatus() {
        return editStatus;
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

    public void setEditStatus(Boolean[] editStatus) {
        this.editStatus = editStatus;
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
                "editStatus=" + editStatus +
                ", redDoraStatus=" + redDoraStatus +
                ", doraIndicators=" + doraIndicators +
                '}';
    }
}
