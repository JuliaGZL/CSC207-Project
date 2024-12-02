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
 * The Edit Status Use Case.
 */
public interface EditStatusInputBoundary {

  /**
   * Execute the Edit Status Use Case.
   *
   * @param editStatusInputData the input data for this use case
   */
  void execute(EditStatusInputData editStatusInputData);

  /**
   * Execute the Edit Status Use Case.
   *
   * @param updateName    the element updated as inputted in ViewModel
   * @param editStatusInputData the input data for this use case
   */
  void execute(String updateName, EditStatusInputData editStatusInputData);

  /**
   * Executes the switch to select dora use case.
   *
   * @param inputData the input data for this use case
   */
  void switchToSelectDoraView(EditStatusInputData inputData);
}
