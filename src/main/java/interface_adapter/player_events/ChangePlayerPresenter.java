package interface_adapter.player_events;

import interface_adapter.edit_tiles.TileSelectorViewModel;
import usecase.change_player.ChangePlayerOutputBoundary;
import usecase.change_player.ChangePlayerOutputData;

public class ChangePlayerPresenter implements ChangePlayerOutputBoundary {
    private final PlayerEventsViewModel playerEventsViewModel;
    private final TileSelectorViewModel tileSelectorViewModel;

    public ChangePlayerPresenter(PlayerEventsViewModel playerEventsViewModel,
                                 TileSelectorViewModel tileSelectorViewModel) {
        this.playerEventsViewModel = playerEventsViewModel;
        this.tileSelectorViewModel = tileSelectorViewModel;
    }

    /**
     * Prepares the success view for the use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(ChangePlayerOutputData outputData) {
        final String name = outputData.getPlayerName();
        final int score = outputData.getScore();

        // notify the tile selector panel for player change
        tileSelectorViewModel.getState().setPlayerName(name);
        tileSelectorViewModel.firePropertyChanged("player");

        // notify the player events panel for player change
        playerEventsViewModel.getState().setPlayerName(name);
        playerEventsViewModel.getState().setScore(score);
        playerEventsViewModel.firePropertyChanged("player");
    }

    /**
     * Prepares the failure view for the use case.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        throw new RuntimeException("The change player use case should never fails!");
    }
}
