package interfaceadapter.edittiles;

import usecase.updateenabledtiles.UpdateEnabledTileOutputBoundary;
import usecase.updateenabledtiles.UpdateEnabledTileOutputData;

/**
 * Presenter for updating enabled tiles.
 */
public class UpdateEnabledTilePresenter implements UpdateEnabledTileOutputBoundary {

  private final TileSelectorViewModel viewModel;

  /**
   * Constructs an UpdateEnabledTilePresenter with the given view model.
   *
   * @param viewModel the view model to be used
   */
  public UpdateEnabledTilePresenter(TileSelectorViewModel viewModel) {
    this.viewModel = viewModel;
  }

  /**
   * Prepares the success view for the use case.
   *
   * @param outputData the output data
   */
  @Override
  public void prepareSuccessView(UpdateEnabledTileOutputData outputData) {
    viewModel.getState().setEnabledTiles(outputData.getEnabledTiles());
    viewModel.firePropertyChanged("enabled_tiles");
  }

  /**
   * Prepares the failure view for the use case.
   *
   * @param errorMessage the explanation of the failure
   */
  @Override
  public void prepareFailView(String errorMessage) {
    // this should never happen!
    throw new RuntimeException("UpdateEnableTile should never fails!");
  }
}
