package interfaceadapter.displaytiles;

import interfaceadapter.edittiles.AddRemoveTilePresenter;
import usecase.addtile.AddTileOutputData;
import usecase.pullremotehand.PullRemoteHandOutputBoundary;
import usecase.pullremotehand.PullRemoteHandOutputData;

/**
 * Presenter for the pull hand from discord use case.
 * This is an adapter: it adapts the AddTilePresenter to implement the output logic.
 */
public class PullRemoteHandPresenter implements PullRemoteHandOutputBoundary {
  private AddRemoveTilePresenter addRemoveTilePresenter;

  /**
   * Constructs a PullRemoteHandPresenter with the given AddRemoveTilePresenter.
   *
   * @param addRemoveTilePresenter the AddRemoveTilePresenter to be used
   */
  public PullRemoteHandPresenter(AddRemoveTilePresenter addRemoveTilePresenter) {
    this.addRemoveTilePresenter = addRemoveTilePresenter;
  }

  /**
   * Sets the AddRemoveTilePresenter.
   *
   * @param addRemoveTilePresenter the AddRemoveTilePresenter to be set
   */
  public void setAddRemoveTilePresenter(AddRemoveTilePresenter addRemoveTilePresenter) {
    this.addRemoveTilePresenter = addRemoveTilePresenter;
  }

  /**
   * Prepares the success view for the use case.
   *
   * @param outputData the output data
   */
  @Override
  public void prepareSuccessView(PullRemoteHandOutputData outputData) {
    addRemoveTilePresenter.prepareSuccessView(
        new AddTileOutputData(
            false,
            outputData.getPlayerName(),
            outputData.getPlayerHand(),
            outputData.getNameList(),
            outputData.getIconList()));
  }

  /**
   * Prepares the failure view for the use case.
   *
   * @param errorMessage the explanation of the failure
   */
  @Override
  public void prepareFailView(String errorMessage) {
    // this shouldn't happen
    throw new RuntimeException("Pull hand from discord should never fail!");
  }
}
