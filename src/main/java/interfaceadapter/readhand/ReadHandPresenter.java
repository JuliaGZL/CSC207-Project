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

package interfaceadapter.readhand;

import usecase.readhand.ReadHandOutputBoundary;
import usecase.readhand.ReadHandOutputData;

/**
 * The Presenter for the read hand use case.
 */
public class ReadHandPresenter implements ReadHandOutputBoundary {
  private final ReadHandViewModel readHandViewModel;

  /**
   * Constructs a ReadHandPresenter with the given view model.
   *
   * @param readHandViewModel the view model to be used by this presenter
   */
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
