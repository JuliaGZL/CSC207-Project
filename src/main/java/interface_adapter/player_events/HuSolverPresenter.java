package interface_adapter.player_events;

import usecase.hu_solver.HuSolveOutputBoundary;
import usecase.hu_solver.HuSolverOutputData;

/**
 * Presenter for the Hu solver.
 */
public class HuSolverPresenter implements HuSolveOutputBoundary {
    private final PlayerEventsViewModel viewModel;

    public HuSolverPresenter(PlayerEventsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Prepares the success view for the use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(HuSolverOutputData outputData) {
        viewModel.getState().setScore(viewModel.getState().getScore() + outputData.getScore());
        viewModel.getState().setMessage(outputData.getMessage());
        viewModel.firePropertyChanged("score");
    }

    /**
     * Prepares the failure view for the use case.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        viewModel.getState().setMessage(errorMessage);
        viewModel.firePropertyChanged("failed");
    }
}
