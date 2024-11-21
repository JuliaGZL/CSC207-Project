package interface_adapter.edit_status;

import interface_adapter.ViewManagerModel;
import use_case.edit_status.EditStatusOutputBoundary;
import use_case.edit_status.EditStatusOutputData;

/**
 * The Presenter for the Edit Status Use Case.
 */
public class EditStatusPresenter implements EditStatusOutputBoundary  {

    private final EditStatusViewModel editStatusViewModel;
    private final ViewManagerModel  viewManagerModel;

    public EditStatusPresenter(EditStatusViewModel editStatusViewModel, ViewManagerModel viewManagerModel) {
        this.editStatusViewModel = editStatusViewModel;
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
}
