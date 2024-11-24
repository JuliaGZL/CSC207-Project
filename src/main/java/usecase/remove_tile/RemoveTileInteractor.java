package usecase.remove_tile;

import entity.Player;
import entity.Tile;
import mahjong.BaseTile;
import utils.BaseTileToPathMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Interactor for the add_tile use case.
 */
public class RemoveTileInteractor implements RemoveTileInputBoundary {
    // Where to remove the tile from - hand, dora or uradora
    public static final int HAND = 1;
    public static final int DORA = 2;
    public static final int URADORA = 3;
    private final int target;

    private RemoveTileDataAccessInterface dataAccessObj;
    private RemoveTileOutputBoundary presenter;

    public RemoveTileInteractor(RemoveTileDataAccessInterface dataAccessObj,
                                RemoveTileOutputBoundary presenter,
                                int target) {
        this.dataAccessObj = dataAccessObj;
        this.presenter = presenter;
        this.target = target;
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
            List<Tile> tileList;
            switch (target) {
                case HAND:
                    tileList = player.getHand();
                    break;
                case DORA:
                    tileList = player.getDora();
                    break;
                case URADORA:
                    tileList = player.getUradora();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid target: " + target);
            }
            removeTile(id, tileList, player, name, target);
        }
    }

    private void removeTile(BaseTile id, List<Tile> tileList,
                            Player player, String name, int target) {
        // id == null is a special case that just reads the list without altering it.
        if (id != null) {
            for (Tile tile : tileList) {
                if (Objects.equals(tile.getBaseTile(), id)) {
                    tileList.remove(tile);
                    break;
                }
            }
            switch (target) {
                case HAND:
                    player.setHand(tileList);
                    break;
                case DORA:
                    player.setDora(tileList);
                    break;
                case URADORA:
                    player.setUradora(tileList);
                    break;
            }
            dataAccessObj.savePlayer(player);
        }

        final List<BaseTile> idList = new ArrayList<BaseTile>();
        final List<String> nameList = new ArrayList<String>();
        final List<String> iconList = new ArrayList<String>();
        for (Tile tile : tileList) {
            idList.add(tile.getBaseTile());
            nameList.add(tile.toString());
            iconList.add(BaseTileToPathMapping.getTilePath(tile.getBaseTile()));
        }
        final RemoveTileOutputData output = new RemoveTileOutputData(false, name, idList, nameList, iconList);

        presenter.prepareSuccessView(output);
    }
}
