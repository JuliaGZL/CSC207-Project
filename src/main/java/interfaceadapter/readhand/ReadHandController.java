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

import usecase.readhand.ReadHandInputBoundary;
import usecase.readhand.ReadHandInputData;

/**
 * Controller for handling read hand operations.
 */
public class ReadHandController {
  private ReadHandInputBoundary readHandUseCaseInteractor;

  /**
   * Constructs a ReadHandController with the specified use case interactor.
   *
   * @param readHandUseCaseInteractor the use case interactor for reading hand
   *                                  data
   */
  public ReadHandController(ReadHandInputBoundary readHandUseCaseInteractor) {
    this.readHandUseCaseInteractor = readHandUseCaseInteractor;
  }

  /**
   * Executes the read hand operation with the given player name and attribute
   * names.
   *
   * @param playerName     the name of the player
   * @param attributeNames the names of the attributes to be read
   */
  public void execute(String playerName, String[] attributeNames) {
    final ReadHandInputData readHandInputData = new ReadHandInputData(playerName, attributeNames);

    readHandUseCaseInteractor.execute(readHandInputData);
  }
}
