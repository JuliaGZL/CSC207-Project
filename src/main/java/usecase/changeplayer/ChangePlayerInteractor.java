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

package usecase.changeplayer;

import entity.Player;
import entity.PlayerFactory;

/**
 * Input boundary of the change player use case.
 */
public class ChangePlayerInteractor implements ChangePlayerInputBoundary {
  private final ChangePlayerDataAccessInterface dataAccessObj;
  private final ChangePlayerOutputBoundary presenter;

  /**
   * Interactor class responsible for handling the change player use case.
   * It interacts with the data access layer and the presenter to perform the
   * necessary operations.
   *
   * @param dataAccessObj The data access object used to interact with the data
   *                      layer.
   * @param presenter     The presenter used to output the results of the use
   *                      case.
   */
  public ChangePlayerInteractor(ChangePlayerDataAccessInterface dataAccessObj,
      ChangePlayerOutputBoundary presenter) {
    this.dataAccessObj = dataAccessObj;
    this.presenter = presenter;
  }

  /**
   * Executes the add_tile use case.
   *
   * @param inputData the input data
   */
  @Override
  public void execute(ChangePlayerInputData inputData) {
    final String name = inputData.getPlayerName();
    if (dataAccessObj.existsByName(name)) {
      // if player already exist, get score and inform change into this player
      int score = dataAccessObj.getPlayer(name).getScore();
      presenter.prepareSuccessView(new ChangePlayerOutputData(name, score));
    } else {
      // if player doesn't exist, create new player with score 0
      Player player = new PlayerFactory().createEmpty(name);
      dataAccessObj.savePlayer(player);
      presenter.prepareSuccessView(new ChangePlayerOutputData(name, 0));
    }
  }
}
