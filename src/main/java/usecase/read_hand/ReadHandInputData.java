package usecase.read_hand;

public class ReadHandInputData {
    private final String playerName;
    private final String[] attributeNames;

    public ReadHandInputData(String playerName, String[] attributeNames) {
        this.playerName = playerName;
        this.attributeNames = attributeNames;
    }

    // Getters
    public String getPlayerName() {
        return playerName;
    }

    public String[] getAttributeNames() {
        return attributeNames;
    }
}
