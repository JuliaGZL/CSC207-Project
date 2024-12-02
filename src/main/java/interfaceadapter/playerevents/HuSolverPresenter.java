package interfaceadapter.playerevents;

import java.util.List;
import mahjong.HandResult;
import mahjong.Yaku;
import mahjong.YakuAndScoreLevelToSpeech;
import usecase.husolver.HuSolveOutputBoundary;
import usecase.husolver.HuSolverOutputData;
import utils.TextToSpeech;


/**
 * Presenter for the Hu solver.
 */
public class HuSolverPresenter implements HuSolveOutputBoundary {
  private final PlayerEventsViewModel viewModel;

  /**
   * Constructs a HuSolverPresenter with the given view model.
   *
   * @param viewModel the view model to be used by this presenter
   */
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
