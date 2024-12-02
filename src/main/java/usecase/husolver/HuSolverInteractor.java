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

import entity.Player;
import entity.PlayerStats;
import entity.PlayerStatsBuilder;
import mahjong.BaseTile;
import mahjong.HandResult;

/**
 * Interactor for the Hu solver.
 */
public class HuSolverInteractor implements HuSolverInputBoundary {
  private final HuSolverDataAccessInterface dataAccessObj;
  private final HuSolveOutputBoundary presenter;

  /**
   * Constructs a HuSolverInteractor with the specified data access object and
   * presenter.
   *
   * @param dataAccessObj the data access object
   * @param presenter     the presenter
   */
  public HuSolverInteractor(
      HuSolverDataAccessInterface dataAccessObj,
      HuSolveOutputBoundary presenter) {
    this.dataAccessObj = dataAccessObj;
    this.presenter = presenter;
  }

  /**
   * Executes the add_tile use case.
   *
   * @param inputData the input data
   */
  @Override
  public void execute(HuSolverInputData inputData) {
    // get attributes
    Player player = dataAccessObj.getPlayer(inputData.getPlayerName());

    // if hand not full, fails.
    if (player.getHand().size() != 14) {
      presenter.prepareFailView("Hand must contain 14 tiles!");
      return;
    }

    Boolean[] attributes = player.getAttributes();
    boolean isTsumo = (player.getWinType().equals("Tsumo"));
    boolean isOya = (player.getSeatWind().equals("East"));

    // get wind
    BaseTile selfWind = getWindBaseTile(player.getSeatWind());
    BaseTile prevalentWind = getWindBaseTile(player.getRoundWind());

    // build stats
    PlayerStats stats = new PlayerStatsBuilder().setHand(player.getHand())
        .setDoraList(player.getDora())
        .setUraDoraList(player.getUradora())
        .setRiichi(attributes[0])
        .setDoubleRiichi(attributes[1])
        .setIppatsu(attributes[2])
        .setHaitei(attributes[3])
        .setHoutei(attributes[4])
        .setRinshan(attributes[5])
        .setChankan(attributes[6])
        .setTenhou(attributes[7])
        .setChiihou(attributes[8])
        .setTsumo(isTsumo)
        .setOya(isOya)
        .setSelfWind(selfWind)
        .setPrevalentWind(prevalentWind)
        .build();

    // get results
    HandResult handResult;
    int score;
    try {
      handResult = HandResult.getInstance(stats);
      score = handResult.getHandScoreResult();
    } catch (RuntimeException e) {
      presenter.prepareFailView(e.getMessage());
      return;
    }
    if (score == 0) {
      presenter.prepareFailView("Current hand is not valid for Hu!");
    } else {
      StringBuilder messageBuilder = new StringBuilder();
      for (String s : handResult.displayHandResult()) {
        messageBuilder.append(s).append("\n");
      }
      String message = messageBuilder.toString();
      dataAccessObj.savePlayer(player);
      presenter.prepareSuccessView(new HuSolverOutputData(message, player.getScore() + score));
    }
  }

  /**
   * Converts a wind string to its corresponding BaseTile.
   *
   * @param wind the wind string
   * @return the corresponding BaseTile
   * @throws IllegalArgumentException if the wind string is invalid
   */
  private BaseTile getWindBaseTile(String wind) {
    BaseTile windBaseTile = BaseTile.windTon;
    switch (wind) {
      case "East":
        break;
      case "West":
        windBaseTile = BaseTile.windShaa;
        break;
      case "North":
        windBaseTile = BaseTile.windPei;
        break;
      case "South":
        windBaseTile = BaseTile.windNan;
        break;
      default:
        throw new IllegalArgumentException("Invalid wind: " + wind);
    }
    return windBaseTile;
  }
}
