package interface_adapter.edit_tiles;

import interface_adapter.ViewManagerModel;
import interface_adapter.edit_status.EditStatusState;
import interface_adapter.edit_status.EditStatusViewModel;
import usecase.edit_status.SelectDoraOutputBoundary;
import usecase.edit_status.SelectDoraOutputData;

/**
 * The Presenter for the Select Dora Use Case.
 */
public class SelectDoraPresenter implements SelectDoraOutputBoundary {
    private final SelectDoraViewModel selectDoraViewModel;
    private final EditStatusViewModel editStatusViewModel;
    private final ViewManagerModel viewManagerModel;

    public SelectDoraPresenter(ViewManagerModel viewManagerModel,
                               SelectDoraViewModel selectDoraViewModel,
                               EditStatusViewModel editStatusViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.selectDoraViewModel = selectDoraViewModel;
        this.editStatusViewModel = editStatusViewModel;
    }

    @Override
    public void prepareSuccessView(SelectDoraOutputData outputData) {
        // On success, switch to the edit status view.
        final EditStatusState editStatusState = editStatusViewModel.getState();
        editStatusState.setDoraIndicators(outputData.getDoraIndicators());

        this.viewManagerModel.setState(editStatusViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // On failure, show the error message.
        final SelectDoraState selectDoraState = selectDoraViewModel.getState();
        selectDoraState.setSelectIndicatorError(errorMessage);
        selectDoraViewModel.firePropertyChanged();
    }
}
