package interface_adapter.edit_status;

import usecase.edit_status.EditStatusInputBoundary;
import usecase.edit_status.EditStatusInputData;

public class EditStatusController {
    private EditStatusInputBoundary editStatusUseCaseInteractor;

    public EditStatusController(EditStatusInputBoundary editStatusUseCaseInteractor) {
        this.editStatusUseCaseInteractor = editStatusUseCaseInteractor;
    }

    public void execute(Boolean[] attributes, int numAkadora, String seatWind, String roundWind, String winType,
            String playerName) {
        final EditStatusInputData editStatusInputData = new EditStatusInputData(attributes, numAkadora, seatWind,
                roundWind, winType, playerName);

        editStatusUseCaseInteractor.execute(editStatusInputData);
    }

    public void execute(String updateName, Boolean[] attributes, int numAkadora, String seatWind, String roundWind,
            String winType, String playerName) {
        final EditStatusInputData editStatusInputData = new EditStatusInputData(attributes, numAkadora, seatWind,
                roundWind, winType, playerName);

        editStatusUseCaseInteractor.execute(updateName, editStatusInputData);
    }
}
