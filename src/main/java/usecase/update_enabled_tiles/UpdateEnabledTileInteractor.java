package usecase.update_enabled_tiles;

import entity.Player;
import entity.Tile;
import mahjong.BaseTile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Interactor of update enabled tile use case.
 */
public class UpdateEnabledTileInteractor implements UpdateEnabledTilesInputBoundary {

    private final UpdateEnabledTileDataAccessInterface dataAccessObj;
    private final UpdateEnabledTileOutputBoundary presenter;

    public UpdateEnabledTileInteractor(UpdateEnabledTileDataAccessInterface dataAccessObj,
                                       UpdateEnabledTileOutputBoundary presenter) {
        this.dataAccessObj = dataAccessObj;
        this.presenter = presenter;
    }

    /**
     * Executes the add_tile use case.
     *
     * @param inputData the input data
     */
    @Override
    public void execute(UpdateEnabledTileInputData inputData) {
        final Player player = dataAccessObj.getPlayer(inputData.getPlayerName());
        final String target = inputData.getTarget();

        Set<BaseTile> enabledTiles = new HashSet<BaseTile>();
        UpdateEnabledTileOutputData outputData;

        // check whether already full - if full, disable all tiles in tile selector.
        switch (target) {
            case "hand": {
                final int maxTiles = 14;
                if (player.getHand().size() >= maxTiles) {
                    outputData = new UpdateEnabledTileOutputData(enabledTiles);
                    presenter.prepareSuccessView(outputData);
                    return;
                }
                break;
            }
            case "dora": {
                final int maxTiles = 4;
                if (player.getDora().size() >= maxTiles) {
                    outputData = new UpdateEnabledTileOutputData(enabledTiles);
                    presenter.prepareSuccessView(outputData);
                    return;
                }
                break;
            }
            case "uradora": {
                final int maxTiles = 4;
                if (player.getUradora().size() >= maxTiles) {
                    outputData = new UpdateEnabledTileOutputData(enabledTiles);
                    presenter.prepareSuccessView(outputData);
                    return;
                }
                break;
            }
        }

        // build enabled tile set
        enabledTiles = buildEnabledSet(player.getHand(), player.getDora(), player.getUradora());
        presenter.prepareSuccessView(new UpdateEnabledTileOutputData(enabledTiles));
    }

    private Set<BaseTile> buildEnabledSet(List<Tile> hand,
                                          List<Tile> dora,
                                          List<Tile> uradora) {
        // A tile is enabled if and only if its total count in the three lists <4.
        Set<BaseTile> ret = new HashSet<BaseTile>();
        for (BaseTile t : BaseTile.values()) {
            int count = 0;
            for (Tile tile : hand) {
                if (tile.getBaseTile() == t) {
                    count ++;
                }
            }
            for (Tile tile : dora) {
                if (tile.getBaseTile() == t) {
                    count ++;
                }
            }
            for (Tile tile : uradora) {
                if (tile.getBaseTile() == t) {
                    count ++;
                }
            }
            if (count < 4) {
                ret.add(t);
            }
        }
        return ret;
    }
}
