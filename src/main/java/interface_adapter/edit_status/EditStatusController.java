package interface_adapter.edit_status;

import usecase.edit_status.EditStatusInputBoundary;

public class EditStatusController {
    private EditStatusInputBoundary editStatusUseCaseInteractor;

    public EditStatusController(EditStatusInputBoundary editStatusUseCaseInteractor) {
        this.editStatusUseCaseInteractor = editStatusUseCaseInteractor;
    }
}
