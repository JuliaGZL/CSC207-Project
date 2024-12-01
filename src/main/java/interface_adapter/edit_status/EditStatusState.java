package interface_adapter.edit_status;

import mahjong.BaseTile;

/**
 * The state information representing the statuses being edited.
 */
public class EditStatusState {

    private BaseTile[] doraIndicators = new BaseTile[5];
    private String selectIndicatorError;
    private String tileType;

    private String winType = "Tsumo";
    private String seatWind = "East";
    private String roundWind = "East";
    private int numAkadora = 0;
    private Boolean[] attributes = { false, false, false, false, false, false, false, false, false };

    // Setters and getters
    public String getTileType() {
        return tileType;
    }

    public void setTileType(String tileType) {
        this.tileType = tileType;
    }

    public String getWinType() {
        return winType;
    }

    public boolean isTsumo() {
        return winType.equals("Tsumo");
    }

    public boolean isRon() {
        return winType.equals("Ron");
    }

    public void setWinType(String winType) {
        this.winType = winType;
    }

    public String getSeatWind() {
        return seatWind;
    }

    public void setSeatWind(String seatWind) {
        this.seatWind = seatWind;
    }

    public String getRoundWind() {
        return roundWind;
    }

    public Boolean[] getAttributes() {
        return attributes;
    }

    public BaseTile[] getDoraIndicators() {
        return doraIndicators;
    }

    public String getSelectIndicatorError() {
        return selectIndicatorError;
    }

    public int getNumAkadora() {
        return numAkadora;
    }

    // Setters

    public void setAttributes(Boolean[] attributes) {
        this.attributes = attributes;
    }

    public void setDoraIndicators(BaseTile[] doraIndicators) {
        this.doraIndicators = doraIndicators;
    }

    public void setSelectIndicatorError(String selectIndicatorError) {
        this.selectIndicatorError = selectIndicatorError;
    }

    public void setRoundWind(String roundWind) {
        this.roundWind = roundWind;
    }

    public void setNumAkadora(int numAkadora) {
        this.numAkadora = numAkadora;
    }

    public String getAttributeString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i]) {
                sb.append(EditStatusViewModel.ATTRIBUTES[i]);
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public String toString() {
        System.out.println(getAttributeString());
        return "EditStatusState{" +
                "selectedTileType=" + tileType +
                ", selectedWinType=" + winType +
                ", seatWind=" + seatWind +
                ", roundWind=" + roundWind +
                ", numAkadora=" + numAkadora +
                ", attributes=" + getAttributeString() +
                '}';
    }
}
