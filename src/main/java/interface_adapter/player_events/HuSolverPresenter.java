package interface_adapter.player_events;

import mahjong.HandResult;
import mahjong.Yaku;
import mahjong.YakuAndScoreLevelToSpeech;
import usecase.hu_solver.HuSolveOutputBoundary;
import usecase.hu_solver.HuSolverOutputData;
import utils.TextToSpeech;

import java.util.List;

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
        // read out result
        HandResult handResult = HandResult.getInstance();
        List<Yaku> yakus = handResult.getHandYakuList();
        YakuAndScoreLevelToSpeech.playSoundInThread(yakus, handResult.getScoreLevel());
        HandResult.resetInstance();

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

        // read out error
        TextToSpeech.getInstance().speakInThread(errorMessage);
    }
}
