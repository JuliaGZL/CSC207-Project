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

package interfaceadapter.displaytiles;

import interfaceadapter.edittiles.AddRemoveTilePresenter;
import usecase.addtile.AddTileOutputData;
import usecase.pullremotehand.PullRemoteHandOutputBoundary;
import usecase.pullremotehand.PullRemoteHandOutputData;

/**
 * Presenter for the pull hand from discord use case.
 * This is an adapter: it adapts the AddTilePresenter to implement the output
 * logic.
 */
public class PullRemoteHandPresenter implements PullRemoteHandOutputBoundary {
  private AddRemoveTilePresenter addRemoveTilePresenter;

  /**
   * Constructs a PullRemoteHandPresenter with the given AddRemoveTilePresenter.
   *
   * @param addRemoveTilePresenter the AddRemoveTilePresenter to be used
   */
  public PullRemoteHandPresenter(AddRemoveTilePresenter addRemoveTilePresenter) {
    this.addRemoveTilePresenter = addRemoveTilePresenter;
  }

  /**
   * Sets the AddRemoveTilePresenter.
   *
   * @param addRemoveTilePresenter the AddRemoveTilePresenter to be set
   */
  public void setAddRemoveTilePresenter(AddRemoveTilePresenter addRemoveTilePresenter) {
    this.addRemoveTilePresenter = addRemoveTilePresenter;
  }

  /**
   * Prepares the success view for the use case.
   *
   * @param outputData the output data
   */
  @Override
  public void prepareSuccessView(PullRemoteHandOutputData outputData) {
    addRemoveTilePresenter.prepareSuccessView(
        new AddTileOutputData(
            false,
            outputData.getPlayerName(),
            outputData.getPlayerHand(),
            outputData.getNameList(),
            outputData.getIconList()));
  }

  /**
   * Prepares the failure view for the use case.
   *
   * @param errorMessage the explanation of the failure
   */
  @Override
  public void prepareFailView(String errorMessage) {
    // this shouldn't happen
    throw new RuntimeException("Pull hand from discord should never fail!");
  }
}
