package usecase.pull_remote_hand;

import entity.Player;
import entity.Tile;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Interactor of the pull hand from discord use case.
 */
public class PullRemoteHandInteractor implements PullRemoteHandInputBoundary {
    private final PullRemoteHandDataAccessInterface dataAccessObj;
    private final PullRemoteHandOutputBoundary presenter;

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
