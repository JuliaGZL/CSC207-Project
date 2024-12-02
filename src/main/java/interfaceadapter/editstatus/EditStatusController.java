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

import usecase.editstatus.EditStatusInputBoundary;
import usecase.editstatus.EditStatusInputData;

/**
 * Controller for editing status.
 */
public class EditStatusController {
  private EditStatusInputBoundary editStatusUseCaseInteractor;

  /**
   * Constructs an EditStatusController with the given interactor.
   *
   * @param editStatusUseCaseInteractor the interactor to handle the use case
   */
  public EditStatusController(EditStatusInputBoundary editStatusUseCaseInteractor) {
    this.editStatusUseCaseInteractor = editStatusUseCaseInteractor;
  }

  /**
   * Executes the edit status use case with the given parameters.
   *
   * @param attributes the attributes to update
   * @param numAkadora the number of Akadora
   * @param seatWind   the seat wind
   * @param roundWind  the round wind
   * @param winType    the type of win
   * @param playerName the name of the player
   */
  public void execute(
      Boolean[] attributes, int numAkadora,
      String seatWind, String roundWind, String winType,
      String playerName) {
    final EditStatusInputData editStatusInputData = new EditStatusInputData(
        attributes, numAkadora, seatWind,
        roundWind, winType, playerName);

    editStatusUseCaseInteractor.execute(editStatusInputData);
  }

  /**
   * Executes the edit status use case with the given parameters and update name.
   *
   * @param updateName the name of the update
   * @param attributes the attributes to update
   * @param numAkadora the number of Akadora
   * @param seatWind   the seat wind
   * @param roundWind  the round wind
   * @param winType    the type of win
   * @param playerName the name of the player
   */
  public void execute(
      String updateName, Boolean[] attributes,
      int numAkadora, String seatWind, String roundWind,
      String winType, String playerName) {
    final EditStatusInputData editStatusInputData = new EditStatusInputData(
        attributes, numAkadora, seatWind,
        roundWind, winType, playerName);

    editStatusUseCaseInteractor.execute(updateName, editStatusInputData);
  }
}
