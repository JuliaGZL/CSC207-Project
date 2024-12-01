package interfaceadapter.edittiles;

import interfaceadapter.displaytiles.TilesDisplayState;
import interfaceadapter.displaytiles.TilesDisplayViewModel;
import usecase.addtile.AddTileOutputBoundary;
import usecase.addtile.AddTileOutputData;
import usecase.removetile.RemoveTileOutputBoundary;
import usecase.removetile.RemoveTileOutputData;

/**
 * The presenter for adding and removing tiles.
 * Implements the output boundaries for both add and remove tile use cases.
 */
public class AddRemoveTilePresenter implements AddTileOutputBoundary, RemoveTileOutputBoundary {

  private final TilesDisplayViewModel viewModel;

  /**
   * Constructs an AddRemoveTilePresenter with the given view model.
   *
   * @param viewModel the view model to be used by this presenter
   */
  public AddRemoveTilePresenter(TilesDisplayViewModel viewModel) {
    this.viewModel = viewModel;
  }

  /**
   * Prepares the success view for the add tile use case.
   *
   * @param outputData the output data from the add tile use case
   */
  @Override
  public void prepareSuccessView(AddTileOutputData outputData) {
    TilesDisplayState state = viewModel.getState();
    state.setNameList(outputData.getNameList());
    state.setIdList(outputData.getIdList());
    state.setIconList(outputData.getIconList());
    viewModel.firePropertyChanged("tiles");
  }

  /**
   * Prepares the success view for the remove tile use case.
   *
   * @param outputData the output data from the remove tile use case
   */
  @Override
  public void prepareSuccessView(RemoveTileOutputData outputData) {
    TilesDisplayState state = viewModel.getState();
    state.setNameList(outputData.getNameList());
    state.setIdList(outputData.getIdList());
    state.setIconList(outputData.getIconList());
    viewModel.firePropertyChanged("tiles");
  }

  /**
   * Prepares the failure view for the use case.
   *
   * @param errorMessage the explanation of the failure
   */
  @Override
  public void prepareFailView(String errorMessage) {
    TilesDisplayState state = viewModel.getState();
    state.setErrorMsg(errorMessage);
    viewModel.firePropertyChanged("failed");
  }
}
