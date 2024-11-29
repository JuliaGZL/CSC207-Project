package interface_adapter.read_hand;

import interface_adapter.ViewManagerModel;
import usecase.read_hand.ReadHandOutputBoundary;
import usecase.read_hand.ReadHandOutputData;

/**
 * The Presenter for the read hand use case.
 */
public class ReadHandPresenter implements ReadHandOutputBoundary {
    private final ReadHandViewModel readHandViewModel;

    public ReadHandPresenter(ReadHandViewModel readHandViewModel) {
        this.readHandViewModel = readHandViewModel;
    }

    /**
     * Prepares the success view for the use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(ReadHandOutputData outputData) {
        final ReadHandState readHandState = readHandViewModel.getState();
        readHandState.setHandInfo(outputData.getHandInfo());
        readHandViewModel.setState(readHandState);
        readHandViewModel.firePropertyChanged("handInfo");
    }

    /**
     * Prepares the failure view for the use case.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        // This use case currently does not have special effects for fail view.
    }
}
