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

package interfaceadapter.edittiles;

import interfaceadapter.displaytiles.TilesDisplayState;
import interfaceadapter.displaytiles.TilesDisplayViewModel;
import java.util.ArrayList;
import mahjong.BaseTile;
import usecase.cleartiles.ClearTilesOutputBoundary;
import usecase.cleartiles.ClearTilesOutputData;

/**
 * The presenter for clearing tiles, implementing the ClearTilesOutputBoundary
 * interface.
 */
public class ClearTilePresenter implements ClearTilesOutputBoundary {

  private final TilesDisplayViewModel viewModel;

  /**
   * Constructs a ClearTilePresenter with the given view model.
   *
   * @param viewModel the view model to be used by this presenter
   */
  public ClearTilePresenter(TilesDisplayViewModel viewModel) {
    this.viewModel = viewModel;
  }

  /**
   * Prepares the success view for the use case.
   *
   * @param outputData the output data
   */
  @Override
  public void prepareSuccessView(ClearTilesOutputData outputData) {
    TilesDisplayState state = viewModel.getState();
    state.setIconList(new ArrayList<String>());
    state.setIdList(new ArrayList<BaseTile>());
    state.setNameList(new ArrayList<String>());
    viewModel.firePropertyChanged("tiles");
  }

  /**
   * Prepares the failure view for the use case.
   *
   * @param errorMessage the explanation of the failure
   */
  @Override
  public void prepareFailView(String errorMessage) {

  }
}
