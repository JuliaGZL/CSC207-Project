package interface_adapter.edit_status;

import interface_adapter.ViewManagerModel;
import interface_adapter.edit_tiles.SelectDoraState;
import interface_adapter.edit_tiles.SelectDoraViewModel;
import usecase.edit_status.EditStatusOutputBoundary;
import usecase.edit_status.EditStatusOutputData;

/**
 * The Presenter for the Edit Status Use Case.
 */
public class EditStatusPresenter implements EditStatusOutputBoundary  {

    private final EditStatusViewModel editStatusViewModel;
    private final SelectDoraViewModel selectDoraViewModel;
    private final ViewManagerModel  viewManagerModel;

    public EditStatusPresenter(EditStatusViewModel editStatusViewModel, SelectDoraViewModel selectDoraViewModel, ViewManagerModel viewManagerModel) {
        this.editStatusViewModel = editStatusViewModel;
        this.selectDoraViewModel = selectDoraViewModel;
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

        final SelectDoraState selectDoraState = selectDoraViewModel.getState();
        selectDoraState.setIndicatorSelections(outputData.getDoraCounts());
        selectDoraViewModel.setState(selectDoraState);
        selectDoraViewModel.firePropertyChanged();

        viewManagerModel.setState(selectDoraViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
