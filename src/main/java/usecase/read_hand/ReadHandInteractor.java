package usecase.read_hand;

import entity.Tile;

import java.util.List;

/**
 * Interactor for the read hand use case.
 */
public class ReadHandInteractor implements ReadHandInputBoundary {
   private ReadHandDataAccessInterface dataAccessObj;
   private ReadHandOutputBoundary presenter;

    public ReadHandInteractor(ReadHandDataAccessInterface dataAccessObj, ReadHandOutputBoundary presenter) {
        this.dataAccessObj = dataAccessObj;
        this.presenter = presenter;
    }

    /**
     * Executes the read hand use case.
     *
     * @param inputData the input data
     */
    @Override
    public void execute(ReadHandInputData inputData) {
        ReadHandOutputData outputData = new ReadHandOutputData(inputData.getPlayerName(),
                getHandInfo(inputData.getPlayerName(), inputData.getAttributeNames()));
        presenter.prepareSuccessView(outputData);
    }

    private String getHandInfo(String playerName, String[] attributeNames) {
        // TODO: read more than just Head
        List<Tile> hand = dataAccessObj.getPlayer(playerName).getHand();
        StringBuilder handInfo = new StringBuilder();

        // 1. Create string that describes the hand
        if (hand.isEmpty()) {
            handInfo.append("Your hand is empty.");
        }
        else {
            handInfo.append("Your hand includes");
            for (Tile tile : hand) {
                handInfo.append(" ");
                handInfo.append(tile.getTile().toString());
                handInfo.append(",");
            }
            handInfo.deleteCharAt(handInfo.length() - 1); // Delete the final comma
            handInfo.append(". Finish. ");
        }

        // 2. Create string that describes the player's attributes.
        // Get whether all attributes are false.
        Boolean[] attributes = dataAccessObj.getPlayer(playerName).getAttributes();
        boolean allFalse = false;
        for (Boolean attribute : attributes) {
            if (attribute) {
                // There is a true attributes. Early return.
                allFalse = false;
            }
            allFalse = true;
        }
        // If all attributes are false, inform this special case to the player.
        if (allFalse) {
            handInfo.append("You have no special attributes.");
        }
        // Else, list the attributes that are true.
        else {
            handInfo.append("You have the following special attributes: ");
            for (int i = 0; i < attributes.length; i++) {
                if (attributes[i]) {
                    handInfo.append(attributeNames[i]);
                    handInfo.append(", ");
                }
            }
            handInfo.deleteCharAt(handInfo.length() - 1); // Delete the final space
            handInfo.deleteCharAt(handInfo.length() - 1); // Delete the final comma
            handInfo.append(". Finish. ");
        }

        return handInfo.toString();
    }
}
