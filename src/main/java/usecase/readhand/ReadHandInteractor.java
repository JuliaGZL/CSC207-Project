package usecase.readhand;

import entity.Tile;
import java.util.List;

/**
 * Interactor for the read hand use case.
 */
public class ReadHandInteractor implements ReadHandInputBoundary {
  private final ReadHandDataAccessInterface dataAccessObj;
  private final ReadHandOutputBoundary presenter;

  /**
   * Constructs a ReadHandInteractor with the given data access object and
   * presenter.
   *
   * @param dataAccessObj the data access object
   * @param presenter     the presenter
   */
  public ReadHandInteractor(
      ReadHandDataAccessInterface dataAccessObj,
      ReadHandOutputBoundary presenter) {
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

  /**
   * Retrieves information about the player's hand, dora, uradora, and attributes.
   *
   * @param playerName     the name of the player
   * @param attributeNames the names of the attributes
   * @return a string describing the player's hand, dora, uradora, and attributes
   */
  private String getHandInfo(String playerName, String[] attributeNames) {
    // TODO: read more than just Head
    List<Tile> hand = dataAccessObj.getPlayer(playerName).getHand();
    StringBuilder handInfo = new StringBuilder();

    // 1. Create string that describes the hand
    if (hand.isEmpty()) {
      handInfo.append("Your hand is empty. ");
    } else {
      handInfo.append("Your hand includes");
      for (Tile tile : hand) {
        handInfo.append(" ");
        handInfo.append(tile.getTile().toText());
        handInfo.append(",");
      }
      handInfo.deleteCharAt(handInfo.length() - 1); // Delete the final comma
      handInfo.append(". ");
    }

    // 2. Create string that describes dora list.
    List<Tile> doras = dataAccessObj.getPlayer(playerName).getDora();
    if (doras.isEmpty()) {
      handInfo.append("There are no dora tiles.");
    } else {
      handInfo.append("The dora tiles are");
      for (Tile dora : doras) {
        handInfo.append(" ");
        handInfo.append(dora.getTile().toText());
        handInfo.append(",");
      }
      handInfo.deleteCharAt(handInfo.length() - 1); // Delete the final comma
      handInfo.append(". ");
    }
    // 3. Create string that describes uradora list.
    List<Tile> uradoras = dataAccessObj.getPlayer(playerName).getUradora();
    if (uradoras.isEmpty()) {
      handInfo.append("There are no uradora tiles.");
    } else {
      handInfo.append("The uradora tiles are");
      for (Tile uradora : uradoras) {
        handInfo.append(" ");
        handInfo.append(uradora.getTile().toText());
        handInfo.append(",");
      }
      handInfo.deleteCharAt(handInfo.length() - 1); // Delete the final comma
      handInfo.append(". ");
    }
    handInfo.append("That's all tiles.");

    // 4. Create string that describes the player's attributes.
    // Get whether all attributes are false.
    Boolean[] attributes = dataAccessObj.getPlayer(playerName).getAttributes();
    boolean allFalse = false;
    for (Boolean attribute : attributes) {
      if (attribute) {
        // There is a true attributes. Early return.
        allFalse = false;
        break;
      }
      allFalse = true;
    }
    // If all attributes are false, inform this special case to the player.
    if (allFalse) {
      handInfo.append("You have no special attributes.");
    } else {
      // Else, list the attributes that are true.
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
