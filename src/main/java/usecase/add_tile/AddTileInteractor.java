package usecase.add_tile;

import java.util.ArrayList;
import java.util.List;

import entity.Player;
import entity.Tile;
import mahjong.BaseTile;
import utils.BaseTileToPathMapping;

/**
 * Interactor for the add_tile use case.
 */
public class AddTileInteractor implements AddTileInputBoundary {
    private AddTileDataAccessInterface dataAccessObj;
    private AddTileOutputBoundary presenter;

    public AddTileInteractor(AddTileDataAccessInterface dataAccessObj,
                             AddTileOutputBoundary presenter) {
        this.dataAccessObj = dataAccessObj;
        this.presenter = presenter;
    }

    /**
     * Executes the add_tile use case.
     *
     * @param inputData the input data
     */
    @Override
    public void execute(AddTileInputData inputData) {
        final String name = inputData.getPlayerName();
        final BaseTile id = inputData.getTileId();
        if (!dataAccessObj.existsByName(name)) {
            // This should never happen!
            throw new RuntimeException("Player name not found!");
        }
        else {
            final Player player = dataAccessObj.getPlayer(name);
            final List<Tile> hand = player.getHand();
            final int fullNum = 14;
            if (hand.size() == fullNum) {
                presenter.prepareFailView("Hand already full (with 14 tiles)!");
            }
            else {
                addTile(id, hand, player, name);
            }
        }
    }

    private void addTile(BaseTile id, List<Tile> hand, Player player, String name) {
        final Tile newTile = new Tile(id, false, false, false);
        hand.add(newTile);
        player.setHand(hand);
        dataAccessObj.savePlayer(player);

        final List<BaseTile> idList = new ArrayList<BaseTile>();
        final List<String> nameList = new ArrayList<String>();
        final List<String> iconList = new ArrayList<String>();
        for (Tile tile : hand) {
            idList.add(tile.getBaseTile());
            nameList.add(tile.toString());
            iconList.add(BaseTileToPathMapping.getTilePath(tile.getBaseTile()));
        }
        final AddTileOutputData output = new AddTileOutputData(false, name, idList, nameList, iconList);

        presenter.prepareSuccessView(output);
    }
}
