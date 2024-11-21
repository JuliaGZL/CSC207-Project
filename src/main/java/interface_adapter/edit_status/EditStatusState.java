package interface_adapter.edit_status;

import mahjong.BaseTiles;

/**
 * The state information representing the statuses being edited.
 */
public class EditStatusState {

    private String selectedTileType;
    private String selectedWinType;
    private String seatWind;
    private String roundWind;
    private Boolean[] attributes = new Boolean[9];

    // Setters and getters
    public String getSelectedTileType() {
        return selectedTileType;
    }

    public void setSelectedTileType(String selectedTileType) {
        this.selectedTileType = selectedTileType;
    }

    public String getSelectedWinType() {
        return selectedWinType;
    }

    public boolean isTsumo() {
        return selectedWinType.equals("Tsumo");
    }

    public boolean isRon() {
        return selectedWinType.equals("Ron");
    }

    public void setSelectedWinType(String selectedWinType) {
        this.selectedWinType = selectedWinType;
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

    public void setRoundWind(String roundWind) {
        this.roundWind = roundWind;
    }

    public Boolean[] getAttributes() {
        return attributes;
    }

    public void setAttributes(Boolean[] attributes) {
        this.attributes = attributes;
    }

    public String getAttributeString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i]) {
                sb.append(EditStatusViewModel.ATTRIBUTES[i]);
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "EditStatusState{" +
                "selectedTileType=" + selectedTileType +
                ", selectedWinType=" + selectedWinType +
                ", seatWind=" + seatWind +
                ", roundWind=" + roundWind +
                ", attributes=" + getAttributeString() +
                '}';
    }
}
