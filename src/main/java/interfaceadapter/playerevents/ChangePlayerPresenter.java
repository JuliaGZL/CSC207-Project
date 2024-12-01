package interfaceadapter.playerevents;

import interfaceadapter.displaytiles.TilesDisplayViewModel;
import interfaceadapter.edittiles.TileSelectorViewModel;
import java.util.List;
import usecase.changeplayer.ChangePlayerOutputBoundary;
import usecase.changeplayer.ChangePlayerOutputData;

/**
 * The ChangePlayerPresenter class implements the ChangePlayerOutputBoundary interface
 * and is responsible for updating the view models when a player change occurs.
 */
public class ChangePlayerPresenter implements ChangePlayerOutputBoundary {
  private PlayerEventsViewModel playerEventsViewModel;
  private TileSelectorViewModel tileSelectorViewModel;
  private List<TilesDisplayViewModel> tileDisplayViewModels;

  /**
   * Constructs a ChangePlayerPresenter with the specified list of TilesDisplayViewModels.
   *
   * @param tileDisplayViewModels the list of TilesDisplayViewModels
   */
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

  /**
   * Sets the PlayerEventsViewModel.
   *
   * @param playerEventsViewModel the PlayerEventsViewModel to set
   */
  public void setPlayerEventsViewModel(PlayerEventsViewModel playerEventsViewModel) {
    this.playerEventsViewModel = playerEventsViewModel;
  }

  /**
   * Sets the TileSelectorViewModel.
   *
   * @param tileSelectorViewModel the TileSelectorViewModel to set
   */
  public void setTileSelectorViewModel(TileSelectorViewModel tileSelectorViewModel) {
    this.tileSelectorViewModel = tileSelectorViewModel;
  }

  /**
   * Adds a TileDisplayViewModel on which firePropertyChanged will be called.
   *
   * @param tileDisplayViewModel the new TileDisplayViewModel
   */
  public void addTileDisplayViewModel(TilesDisplayViewModel tileDisplayViewModel) {
    this.tileDisplayViewModels.add(tileDisplayViewModel);
  }
}
