package usecase.pullremotehand;

import entity.Player;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;

/**
 * Interactor of the pull hand from discord use case.
 */
public class PullRemoteHandInteractor implements PullRemoteHandInputBoundary {
  private final PullRemoteHandDataAccessInterface dataAccessObj;
  private final PullRemoteHandOutputBoundary presenter;

  /**
   * Constructs a PullRemoteHandInteractor.
   *
   * @param dataAccessObj the data access object
   * @param presenter the presenter
   */
  public PullRemoteHandInteractor(PullRemoteHandDataAccessInterface dataAccessObj,
      PullRemoteHandOutputBoundary presenter) {
    this.dataAccessObj = dataAccessObj;
    this.presenter = presenter;
  }

  /**
   * Executes the use case.
   *
   * @param inputData the input data
   */
  @Override
  public void execute(PullRemoteHandInputData inputData) {
    List<Tile> newHand = dataAccessObj.getHandFromDiscord();
    Player player = dataAccessObj.getPlayer(inputData.getPlayerName());
    player.setHand(newHand);
    dataAccessObj.savePlayer(player);

    List<BaseTile> baseTileList = new ArrayList<BaseTile>();
    List<String> nameList = new ArrayList<String>();
    List<String> iconList = new ArrayList<String>();

    for (Tile tile : newHand) {
      baseTileList.add(tile.getTile());
      nameList.add(tile.getTile().toText());
      iconList.add(BaseTileToPathMapping.getTilePath(tile.getTile()));
    }

    PullRemoteHandOutputData outputData = new PullRemoteHandOutputData(
        inputData.getPlayerName(), baseTileList, nameList, iconList);
    presenter.prepareSuccessView(outputData);
  }
}
