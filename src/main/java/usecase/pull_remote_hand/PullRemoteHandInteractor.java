package usecase.pull_remote_hand;

import entity.Player;
import entity.Tile;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Interactor for the pull hand from discord use case.
 *
 * This class is responsible for orchestrating the business logic of
 * the pull hand use case, coordinating the data access and output
 * interactions for pulling a hand from Discord.
 */
public class PullRemoteHandInteractor implements PullRemoteHandInputBoundary {

    /**
     * The data access object used to interact with the data layer
     * for the pull hand use case.
     */
    private final PullRemoteHandDataAccessInterface dataAccessObj;

    /**
     * The presenter used to handle the output of the pull hand use case.
     */
    private final PullRemoteHandOutputBoundary presenter;

    /**
     * Constructor to create an instance of PullRemoteHandInteractor.
     *
     * @param dataAccessObjInput the data access object for pulling hand data
     * @param presenterInput   the presenter for handling output after the pull
     *                      hand use case execution
     */
    public PullRemoteHandInteractor(final PullRemoteHandDataAccessInterface
                                            dataAccessObjInput,
                                    final PullRemoteHandOutputBoundary
                                            presenterInput) {
        this.dataAccessObj = dataAccessObjInput;
        this.presenter = presenterInput;
    }

    /**
     * Executes the use case.
     *
     * @param inputData the input data
     */
    @Override
    public void execute(final PullRemoteHandInputData inputData) {
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
