package usecase.change_player;

import entity.Player;
import entity.PlayerFactory;

/**
 * Input boundary of the change player use case.
 */
public class ChangePlayerInteractor implements ChangePlayerInputBoundary {
    private final ChangePlayerDataAccessInterface dataAccessObj;
    private final ChangePlayerOutputBoundary presenter;

    public ChangePlayerInteractor(ChangePlayerDataAccessInterface dataAccessObj,
                                  ChangePlayerOutputBoundary presenter) {
        this.dataAccessObj = dataAccessObj;
        this.presenter = presenter;
    }

    /**
     * Executes the add_tile use case.
     *
     * @param inputData the input data
     */
    @Override
    public void execute(ChangePlayerInputData inputData) {
        final String name = inputData.getPlayerName();
        if (dataAccessObj.existsByName(name)) {
            // if player already exist, get score and inform change into this player
            int score = dataAccessObj.getPlayer(name).getScore();
            presenter.prepareSuccessView(new ChangePlayerOutputData(name, score));
        } else {
            // if player doesn't exist, create new player with score 0
            Player player = new PlayerFactory().createEmpty(name);
            dataAccessObj.savePlayer(player);
            presenter.prepareSuccessView(new ChangePlayerOutputData(name, 0));
        }
    }
}
