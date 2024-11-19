package interface_adapter.edit_tiles;

import use_case.add_tile.AddTileInputData;
import use_case.add_tile.AddTileOutputBoundary;
import use_case.add_tile.AddTileOutputData;
import use_case.remove_tile.RemoveTileOutputBoundary;
import use_case.remove_tile.RemoveTileOutputData;

public class AddRemoveTilePresenter implements AddTileOutputBoundary, RemoveTileOutputBoundary {
    /**
     * Prepares the success view for the use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(AddTileOutputData outputData) {

    }

    /**
     * Prepares the success view for the use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(RemoveTileOutputData outputData) {

    }

    /**
     * Prepares the failure view for the use case.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {

    }
}
