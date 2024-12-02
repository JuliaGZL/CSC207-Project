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

import usecase.pullremotehand.PullRemoteHandInputBoundary;
import usecase.pullremotehand.PullRemoteHandInputData;

/**
 * Controller for handling the pull remote hand use case.
 */
public class PullRemoteHandController {

  /**
   * The interactor to be used by this controller.
   */
  private final PullRemoteHandInputBoundary interactor;

  /**
   * Constructs a PullRemoteHandController with the given interactor.
   *
   * @param interactor the interactor to be used by this controller
   */
  public PullRemoteHandController(PullRemoteHandInputBoundary interactor) {
    this.interactor = interactor;
  }

  /**
   * Entry to run the use case.
   *
   * @param playerName name of the current player
   */
  public void execute(String playerName) {
    interactor.execute(new PullRemoteHandInputData(playerName));
  }
}
