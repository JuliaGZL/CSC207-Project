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

package unit.usecase.huresolver;

import dataaccess.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.PlayerFactory;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecase.husolver.HuSolveOutputBoundary;
import usecase.husolver.HuSolverInputData;
import usecase.husolver.HuSolverInteractor;
import usecase.husolver.HuSolverOutputData;

public class HuResolverInteractorTest {
  private final PlayerFactory factory = new PlayerFactory();
  private final String playerName = "player";

  @Test
  void SuccessTest() {
    InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();
    Player player = factory.createEmpty(playerName);
    BaseTile[] handTiles = {
        BaseTile._1m, BaseTile._1m, BaseTile._1m,
        BaseTile._2m, BaseTile._2m, BaseTile._2m,
        BaseTile._3m, BaseTile._3m, BaseTile._3m,
        BaseTile._4m, BaseTile._4m, BaseTile._4m,
        BaseTile._5m, BaseTile._5m
    };
    List<Tile> hand = new ArrayList<>();
    for (BaseTile baseTile : handTiles) {
      hand.add(new Tile(baseTile));
    }
    player.setHand(hand);
    DAO.savePlayer(player);
    MockOTB mockOTB = new MockOTB();
    HuSolverInteractor interactor = new HuSolverInteractor(DAO, mockOTB);
    interactor.execute(new HuSolverInputData(playerName));
    Assertions.assertEquals(mockOTB.getScore(), 96000);
  }

  @Test
  void FailTestHandFull() {
    InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();
    Player player = factory.createEmpty(playerName);
    BaseTile[] handTiles = {
        BaseTile._1s, BaseTile._1m, BaseTile._1m,
        BaseTile._2m, BaseTile._2m, BaseTile._2m,
        BaseTile._3m, BaseTile._3m, BaseTile._3m,
        BaseTile._4m, BaseTile._4m, BaseTile._4m,
        BaseTile._5m, BaseTile._5m
    };
    List<Tile> hand = new ArrayList<>();
    for (BaseTile baseTile : handTiles) {
      hand.add(new Tile(baseTile));
    }
    player.setHand(hand);
    DAO.savePlayer(player);
    MockOTB mockOTB = new MockOTB();
    HuSolverInteractor interactor = new HuSolverInteractor(DAO, mockOTB);
    interactor.execute(new HuSolverInputData(playerName));
    Assertions.assertTrue(mockOTB.isFailed());
  }

  @Test
  void FailTestHandNotFull() {
    InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();
    Player player = factory.createEmpty(playerName);
    BaseTile[] handTiles = {
        BaseTile._1m
    };
    List<Tile> hand = new ArrayList<>();
    for (BaseTile baseTile : handTiles) {
      hand.add(new Tile(baseTile));
    }
    player.setHand(hand);
    DAO.savePlayer(player);
    MockOTB mockOTB = new MockOTB();
    HuSolverInteractor interactor = new HuSolverInteractor(DAO, mockOTB);
    interactor.execute(new HuSolverInputData(playerName));
    Assertions.assertTrue(mockOTB.isFailed());
  }
}

class MockOTB implements HuSolveOutputBoundary {
  private int score = 0;
  private boolean failed = false;

  /**
   * Prepares the success view for the use case.
   *
   * @param outputData the output data
   */
  @Override
  public void prepareSuccessView(HuSolverOutputData outputData) {
    score = outputData.getScore();
  }

  /**
   * Prepares the failure view for the use case.
   *
   * @param errorMessage the explanation of the failure
   */
  @Override
  public void prepareFailView(String errorMessage) {
    failed = true;
  }

  public int getScore() {
    return score;
  }

  public boolean isFailed() {
    return failed;
  }
}
