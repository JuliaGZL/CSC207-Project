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

package usecase.editstatus;

/**
 * The output boundary for the Edit Status Use Case.
 */
public interface EditStatusOutputBoundary {
  /**
   * Prepares the success view for the Edit Status Use Case.
   *
   * @param outputData the output data
   */
  void prepareSuccessView(EditStatusOutputData outputData);

  /**
   * Prepares the success view for the Edit Status Use Case.
   *
   * @param updateName the element updated as to be inputted in ViewModel
   * @param outputData the output data
   */
  void prepareSuccessView(String updateName, EditStatusOutputData outputData);

  /**
   * Prepares the failure view for the Edit Status Use Case.
   *
   * @param errorMessage the explanation of the failure
   */
  void prepareFailView(String errorMessage);

  /**
   * Switches to the Select Dora View.
   *
   * @param outputData the output data
   */
  void switchToSelectDoraView(EditStatusOutputData outputData);

  /**
   * Switches to add tiles to hand.
   */
  void switchToSelectForHand();
}
