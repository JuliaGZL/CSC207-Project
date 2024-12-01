package usecase.edit_status;

import java.util.Map;

import entity.Player;
import mahjong.BaseTile;

/**
 * The output data for the edit status use case.
 */
public class EditStatusOutputData {
    private String playerName;

    private String winType;
    private String roundWind;
    private String seatWind;
    private int numAkadora;
    private final Boolean[] attributes;

    private final boolean useCaseFailed;

    // TODO: Should I keep these?
    private int doraCount = 0;

    public EditStatusOutputData(Boolean[] attributes, int numAkadora, String seatWind, String roundWind, String winType,
            String playerName) {
        this.attributes = attributes;
        this.numAkadora = numAkadora;
        this.seatWind = seatWind;
        this.roundWind = roundWind;
        this.winType = winType;
        this.playerName = playerName;
        this.useCaseFailed = false; // This use case never fails.
    }

    public EditStatusOutputData(Player player) {
        this.attributes = player.getAttributes();
        this.numAkadora = player.getNumAkaDora();
        this.seatWind = player.getSeatWind();
        this.roundWind = player.getRoundWind();
        this.winType = player.getWinType();
        this.playerName = player.getName();
        this.useCaseFailed = false; // This use case never fails.
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

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public Boolean[][] getDoraCounts() {
        return null;
    }
}
