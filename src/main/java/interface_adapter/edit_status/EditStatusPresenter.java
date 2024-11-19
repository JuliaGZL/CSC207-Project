package interface_adapter.edit_status;

import interface_adapter.ViewManagerModel;
import interface_adapter.edit_tiles.SelectDoraState;
import interface_adapter.edit_tiles.TileSelectorViewModel;
import usecase.edit_status.EditStatusOutputBoundary;
import usecase.edit_status.EditStatusOutputData;

/**
 * The Presenter for the Edit Status Use Case.
 */
public class EditStatusPresenter implements EditStatusOutputBoundary  {

    private final EditStatusViewModel editStatusViewModel;
    private final TileSelectorViewModel tileSelectorViewModel;
    private final ViewManagerModel  viewManagerModel;

    public EditStatusPresenter(EditStatusViewModel editStatusViewModel, TileSelectorViewModel tileSelectorViewModel, ViewManagerModel viewManagerModel) {
        this.editStatusViewModel = editStatusViewModel;
        this.tileSelectorViewModel = tileSelectorViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EditStatusOutputData outputData) {
        // This use case currently does not need to prepare a success view.
        editStatusViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // This use case currently cannot fail.
    }

    @Override
    public void switchToSelectDoraView(EditStatusOutputData outputData) {
        // Switch to the select dora view if intended.

        final SelectDoraState selectDoraState = tileSelectorViewModel.getState();
        selectDoraState.setIndicatorSelections(outputData.getDoraCounts());
        tileSelectorViewModel.setState(selectDoraState);
        tileSelectorViewModel.firePropertyChanged();

        viewManagerModel.setState(tileSelectorViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
