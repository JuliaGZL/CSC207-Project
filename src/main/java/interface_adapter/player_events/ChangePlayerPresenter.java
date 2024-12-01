package interface_adapter.player_events;

import interface_adapter.edit_tiles.TileSelectorViewModel;
import interface_adapter.display_tiles.TilesDisplayViewModel;
import usecase.change_player.ChangePlayerOutputBoundary;
import usecase.change_player.ChangePlayerOutputData;

import java.util.List;

public class ChangePlayerPresenter implements ChangePlayerOutputBoundary {
    private PlayerEventsViewModel playerEventsViewModel;
    private TileSelectorViewModel tileSelectorViewModel;
    private List<TilesDisplayViewModel> tileDisplayViewModels;

    public ChangePlayerPresenter(List<TilesDisplayViewModel> tileDisplayViewModels) {
        this.tileDisplayViewModels = tileDisplayViewModels;
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

        // notify the tile list displays to update content
        for (TilesDisplayViewModel viewModel : tileDisplayViewModels) {
            viewModel.getState().setPlayerName(name);
            viewModel.firePropertyChanged("player");
        }
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

    public void setPlayerEventsViewModel(PlayerEventsViewModel playerEventsViewModel) {
        this.playerEventsViewModel = playerEventsViewModel;
    }

    public void setTileSelectorViewModel(TileSelectorViewModel tileSelectorViewModel) {
        this.tileSelectorViewModel = tileSelectorViewModel;
    }

    /**
     * Add a TileDisplayViewModel on which firePropertyChanged will be called
     * 
     * @param tileDisplayViewModel the new TileDisplayViewModel
     */
    public void addTileDisplayViewModel(TilesDisplayViewModel tileDisplayViewModel) {
        this.tileDisplayViewModels.add(tileDisplayViewModel);
    }
}
