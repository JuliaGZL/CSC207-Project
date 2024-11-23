package interface_adapter.edit_tiles;

import java.util.ArrayList;

import mahjong.BaseTile;
import usecase.clear_tiles.ClearTilesOutputBoundary;
import usecase.clear_tiles.ClearTilesOutputData;

public class ClearTilePresenter implements ClearTilesOutputBoundary {

    private final TilesDisplayViewModel viewModel;

    public ClearTilePresenter(TilesDisplayViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Prepares the success view for the use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(ClearTilesOutputData outputData) {
        TilesDisplayState state = viewModel.getState();
        state.setIconList(new ArrayList<String>());
        state.setIdList(new ArrayList<BaseTile>());
        state.setNameList(new ArrayList<String>());
        viewModel.firePropertyChanged("tiles");
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
