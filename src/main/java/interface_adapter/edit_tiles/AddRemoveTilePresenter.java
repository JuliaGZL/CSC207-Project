package interface_adapter.edit_tiles;

import interface_adapter.display_tiles.TilesDisplayState;
import interface_adapter.display_tiles.TilesDisplayViewModel;
import usecase.add_tile.AddTileOutputBoundary;
import usecase.add_tile.AddTileOutputData;
import usecase.remove_tile.RemoveTileOutputBoundary;
import usecase.remove_tile.RemoveTileOutputData;

public class AddRemoveTilePresenter implements
        AddTileOutputBoundary, RemoveTileOutputBoundary {

    private final TilesDisplayViewModel viewModel;

    public AddRemoveTilePresenter(TilesDisplayViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Prepares the success view for the use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(final AddTileOutputData outputData) {
        TilesDisplayState state = viewModel.getState();
        state.setNameList(outputData.getNameList());
        state.setIdList(outputData.getIdList());
        state.setIconList(outputData.getIconList());
        viewModel.firePropertyChanged("tiles");
    }

    /**
     * Prepares the success view for the use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(final RemoveTileOutputData outputData) {
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
    public void prepareFailView(final String errorMessage) {
        TilesDisplayState state = viewModel.getState();
        state.setErrorMsg(errorMessage);
        viewModel.firePropertyChanged("failed");
    }
}
