package interface_adapter.display_tiles;

import interface_adapter.edit_tiles.AddRemoveTilePresenter;
import usecase.add_tile.AddTileOutputData;
import usecase.pull_remote_hand.PullRemoteHandOutputBoundary;
import usecase.pull_remote_hand.PullRemoteHandOutputData;

/**
 * Presenter for the pull hand from discord use case
 * This is an adapter: it adapts the AddTilePresenter to implement the output
 * logic.
 */
public class PullRemoteHandPresenter implements PullRemoteHandOutputBoundary {
    private AddRemoveTilePresenter addRemoveTilePresenter;

    public PullRemoteHandPresenter(AddRemoveTilePresenter addRemoveTilePresenter) {
        this.addRemoveTilePresenter = addRemoveTilePresenter;
    }

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
