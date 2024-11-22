package use_case.remove_tile;

import entity.Player;
import entity.Tile;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Interactor for the add_tile use case.
 */
public class RemoveTileInteractor implements RemoveTileInputBoundary {
    private RemoveTileDataAccessInterface dataAccessObj;
    private RemoveTileOutputBoundary presenter;

    public RemoveTileInteractor(RemoveTileDataAccessInterface dataAccessObj,
                                RemoveTileOutputBoundary presenter) {
        this.dataAccessObj = dataAccessObj;
        this.presenter = presenter;
    }

    /**
     * Executes the add_tile use case.
     *
     * @param inputData the input data
     */
    @Override
    public void execute(RemoveTileInputData inputData) {
        final String name = inputData.getPlayerName();
        final BaseTile id = inputData.getTileId();
        if (!dataAccessObj.existsByName(name)) {
            // This should never happen!
            throw new RuntimeException("Player name not found!");
        }
        else {
            final Player player = dataAccessObj.getPlayer(name);
            final List<Tile> hand = player.getHand();
            removeTile(id, hand, player, name);
        }
    }

    private void removeTile(BaseTile id, List<Tile> hand, Player player, String name) {
        for (Tile tile : hand) {
            if (tile.getTile() == id) {
                hand.remove(tile);
                break;
            }
        }
        // TODO: sort hand!
        player.setHand(hand);
        dataAccessObj.savePlayer(player);

        final List<BaseTile> idList = new ArrayList<BaseTile>();
        final List<String> nameList = new ArrayList<String>();
        final List<String> iconList = new ArrayList<String>();
        for (Tile tile : hand) {
            idList.add(tile.getTile());
            nameList.add(tile.toString());
            iconList.add(BaseTileToPathMapping.getTilePath(tile.getTile()));
        }
        final RemoveTileOutputData output = new RemoveTileOutputData(false, name, idList, nameList, iconList);

        presenter.prepareSuccessView(output);
    }
}
