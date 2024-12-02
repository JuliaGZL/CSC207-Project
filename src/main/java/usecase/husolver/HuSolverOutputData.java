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

package usecase.husolver;

/**
 * Output data format for the Hu solver.
 */
public class HuSolverOutputData {
  private final String message;
  private final int score;

  /**
   * Constructs a new HuSolverOutputData with the specified message and score.
   *
   * @param message the message to be included in the output data
   * @param score   the score to be included in the output data
   */
  public HuSolverOutputData(String message, int score) {
    this.message = message;
    this.score = score;
  }

  /**
   * Returns the message included in the output data.
   *
   * @return the message
   */
  public String getMessage() {
    return message;
  }

  /**
   * Returns the score included in the output data.
   *
   * @return the score
   */
  public int getScore() {
    return score;
  }
}
