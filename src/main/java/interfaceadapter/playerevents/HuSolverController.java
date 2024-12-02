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

import usecase.husolver.HuSolverInputBoundary;
import usecase.husolver.HuSolverInputData;

/**
 * Controller for the Hu solver.
 */
public class HuSolverController {
  private final HuSolverInputBoundary interactor;

  /**
   * Constructs a HuSolverController with the given interactor.
   *
   * @param interactor the interactor to be used by this controller
   */
  public HuSolverController(HuSolverInputBoundary interactor) {
    this.interactor = interactor;
  }

  /**
   * Execute the Hu solver that calculates score.
   *
   * @param playerName name of the player
   */
  public void execute(String playerName) {
    interactor.execute(new HuSolverInputData(playerName));
  }
}
