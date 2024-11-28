package usecase.clear_tiles;

import java.util.ArrayList;

import entity.Player;
import entity.Tile;

/**
 * Interactor for the clear_tile use case.
 */
public class ClearTilesInteractor implements ClearTilesInputBoundary {
    // Where to clear the tiles - hand, dora or uradora
    public static final int HAND = 1;
    public static final int DORA = 2;
    public static final int URADORA = 3;
    private final int target;

    private ClearTilesDataAccessInterface dataAccessObj;
    private ClearTilesOutputBoundary presenter;

    public ClearTilesInteractor(ClearTilesDataAccessInterface dataAccessObj,
                                ClearTilesOutputBoundary presenter,
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
    public void execute(ClearTilesInputData inputData) {
        final String name = inputData.getPlayerName();
        if (!dataAccessObj.existsByName(name)) {
            // This should never happen!
            throw new RuntimeException("Player name not found!");
        }
        else {
            final Player player = dataAccessObj.getPlayer(name);
            ArrayList<Tile> tileList = new ArrayList<Tile>();
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
                default:
                    throw new IllegalArgumentException("Invalid target: " + target);
            }
            dataAccessObj.savePlayer(player);

            presenter.prepareSuccessView(new ClearTilesOutputData(false, name));
        }
    }
}
