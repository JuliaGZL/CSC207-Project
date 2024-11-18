package use_case.clear_tiles;

import java.util.ArrayList;

import entity.Player;
import entity.Tile;

/**
 * Interactor for the clear_tile use case.
 */
public class ClearTilesInteractor implements ClearTilesInputBoundary {
    private ClearTilesDataAccessInterface dataAccessObj;
    private ClearTilesOutputBoundary presenter;

    public ClearTilesInteractor(ClearTilesDataAccessInterface dataAccessObj,
                                ClearTilesOutputBoundary presenter) {
        this.dataAccessObj = dataAccessObj;
        this.presenter = presenter;
    }

    /**
     * Executes the add_tile use case.
     *
     * @param inputData the input data
     */
    @Override
    public void execute(ClearTilesInputData inputData) {
        final String name = inputData.getPlayerName();
        if (!dataAccessObj.existsByName(name)) {
            // This should never happen!
            throw new RuntimeException("Player name not found!");
        }
        else {
            final Player player = dataAccessObj.getPlayer(name);
            player.setHand(new ArrayList<Tile>());
            dataAccessObj.savePlayer(player);

            presenter.prepareSuccessView(new ClearTilesOutputData(false, name));
        }
    }
}
