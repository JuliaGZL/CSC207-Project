package usecase.edit_status;

import mahjong.BaseTile;

/**
 * The input data for the Edit Status Use Case.
 */
public class EditStatusInputData {
    private String playerName;

    private String winType = "Tsumo";
    private String roundWind = "East";
    private String seatWind = "East";
    private int numAkadora = 0;
    private final Boolean[] attributes; // Attributes of the hand in this round

    public EditStatusInputData(Boolean[] attributes, int numAkadora, String seatWind, String roundWind, String winType, String playerName) {
        this.attributes = attributes;
        this.numAkadora = numAkadora;
        this.seatWind = seatWind;
        this.roundWind = roundWind;
        this.winType = winType;
        this.playerName = playerName;
    }

    // Getters
    public String getPlayerName() {
        return playerName;
    }

    public String getWinType() {
        return winType;
    }

    public String getRoundWind() {
        return roundWind;
    }

    public String getSeatWind() {
        return seatWind;
    }

    public int getNumAkadora() {
        return numAkadora;
    }

    public Boolean[] getAttributes() {
        return attributes;
    }
}
