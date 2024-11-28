package interface_adapter.read_hand;

import usecase.read_hand.ReadHandInputBoundary;
import usecase.read_hand.ReadHandInputData;

public class ReadHandController {
    private ReadHandInputBoundary readHandUseCaseInteractor;

    public ReadHandController(ReadHandInputBoundary readHandUseCaseInteractor) {
        this.readHandUseCaseInteractor = readHandUseCaseInteractor;
    }

    public void execute(String playerName, String[] attributeNames) {
        final ReadHandInputData readHandInputData = new ReadHandInputData(playerName, attributeNames);

        readHandUseCaseInteractor.execute(readHandInputData);
    }
}
