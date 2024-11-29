package interface_adapter.read_hand;

import entity.Tile;
import usecase.read_hand.ReadHandInputBoundary;
import usecase.read_hand.ReadHandInputData;

import java.util.List;

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
