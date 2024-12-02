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

package interfaceadapter.editstatus;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.edittiles.SelectDoraState;
import interfaceadapter.edittiles.SelectDoraViewModel;
import interfaceadapter.edittiles.TileSelectorState;
import interfaceadapter.edittiles.TileSelectorViewModel;
import usecase.editstatus.EditStatusOutputBoundary;
import usecase.editstatus.EditStatusOutputData;

/**
 * The Presenter for the Edit Status Use Case.
 */
public class EditStatusPresenter implements EditStatusOutputBoundary {

  /**
   * The ViewModel for editing status.
   */
  private final EditStatusViewModel editStatusViewModel;

  /**
   * The ViewModel for selecting Dora.
   */
  private final SelectDoraViewModel selectDoraViewModel;

  /**
   * The ViewModel for selecting tiles.
   */
  private final TileSelectorViewModel tileSelectorViewModel;

  /**
   * The model for managing views.
   */
  private final ViewManagerModel viewManagerModel;

  /**
   * Constructs an EditStatusPresenter with the given ViewModels and
   * ViewManagerModel.
   *
   * @param editStatusViewModel   the ViewModel for editing status
   * @param selectDoraViewModel   the ViewModel for selecting Dora
   * @param tileSelectorViewModel the ViewModel for selecting tiles
   * @param viewManagerModel      the model for managing views
   */
  public EditStatusPresenter(EditStatusViewModel editStatusViewModel,
      SelectDoraViewModel selectDoraViewModel,
      TileSelectorViewModel tileSelectorViewModel,
      ViewManagerModel viewManagerModel) {
    this.editStatusViewModel = editStatusViewModel;
    this.selectDoraViewModel = selectDoraViewModel;
    this.tileSelectorViewModel = tileSelectorViewModel;
    this.viewManagerModel = viewManagerModel;
  }

  /**
   * Prepares the success view with the given output data.
   *
   * @param outputData the output data
   */
  @Override
  public void prepareSuccessView(EditStatusOutputData outputData) {
    System.out.println("EditStatusPresenter.prepareSuccessView");
    final EditStatusState editStatusState = editStatusViewModel.getState();
    editStatusState.setAttributes(outputData.getAttributes());
    editStatusState.setNumAkadora(outputData.getNumAkadora());
    editStatusState.setRoundWind(outputData.getRoundWind());
    editStatusState.setSeatWind(outputData.getSeatWind());
    editStatusState.setWinType(outputData.getWinType());
    // editStatusState.setTileType(outputData.getTileType());
    editStatusViewModel.setState(editStatusState);
    editStatusViewModel.firePropertyChanged("winType");
  }

  /**
   * Prepares the success view with the given update name and output data.
   *
   * @param updateName the name of the update
   * @param outputData the output data
   */
  @Override
  public void prepareSuccessView(String updateName, EditStatusOutputData outputData) {
    final EditStatusState editStatusState = editStatusViewModel.getState();
    editStatusState.setAttributes(outputData.getAttributes());
    editStatusState.setNumAkadora(outputData.getNumAkadora());
    editStatusState.setRoundWind(outputData.getRoundWind());
    editStatusState.setSeatWind(outputData.getSeatWind());
    editStatusState.setWinType(outputData.getWinType());
    editStatusViewModel.setState(editStatusState);

    editStatusViewModel.firePropertyChanged(updateName);
  }

  /**
   * Prepares the fail view with the given error message.
   *
   * @param errorMessage the error message
   */
  @Override
  public void prepareFailView(String errorMessage) {
    // This use case currently cannot fail.
  }

  /**
   * Switches to the select Dora view with the given output data.
   *
   * @param outputData the output data
   */
  @Override
  public void switchToSelectDoraView(EditStatusOutputData outputData) {
    // Tell the tile selector to now add new tiles to dora.
    final TileSelectorState tileSelectorState = tileSelectorViewModel.getState();
    tileSelectorState.setMessage("dora");
    tileSelectorViewModel.setState(tileSelectorState);
    tileSelectorViewModel.firePropertyChanged("target");

    final SelectDoraState selectDoraState = selectDoraViewModel.getState();
    selectDoraState.setIndicatorSelections(outputData.getDoraCounts());
    selectDoraViewModel.setState(selectDoraState);
    selectDoraViewModel.firePropertyChanged();

    viewManagerModel.setState(selectDoraViewModel.getViewName());
    viewManagerModel.firePropertyChanged();
  }

  /**
   * Switches to the select for hand view.
   */
  @Override
  public void switchToSelectForHand() {
    // Tell the tile selector to now add new tiles to hand.
    final TileSelectorState tileSelectorState = tileSelectorViewModel.getState();
    tileSelectorState.setMessage("hand");
    tileSelectorViewModel.setState(tileSelectorState);
    tileSelectorViewModel.firePropertyChanged("target");
  }
}
