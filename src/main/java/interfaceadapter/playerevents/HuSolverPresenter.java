/*
 Copyright (c) 2024 YAizhou, li-tianchu, JuliaGZL, TurkeyBilly

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

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
