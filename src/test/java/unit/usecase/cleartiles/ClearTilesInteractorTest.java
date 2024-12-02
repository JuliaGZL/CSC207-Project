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

package unit.usecase.cleartiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import dataaccess.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.PlayerFactory;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecase.cleartiles.ClearTilesDataAccessInterface;
import usecase.cleartiles.ClearTilesInputBoundary;
import usecase.cleartiles.ClearTilesInputData;
import usecase.cleartiles.ClearTilesInteractor;
import usecase.cleartiles.ClearTilesOutputBoundary;
import usecase.cleartiles.ClearTilesOutputData;

public class ClearTilesInteractorTest {
  private final String name = "player1";
  private final BaseTile tileId = BaseTile._1m;
  private final PlayerFactory factory = new PlayerFactory();
  private final ClearTilesDataAccessInterface DAO = new InMemoryUniversalDataAccessObject();

  @Test
  void SuccessTestHand() {
    ClearTilesInputBoundary interactor = new ClearTilesInteractor(
        DAO, new dummyClearTilesOTB(name), ClearTilesInteractor.HAND);
    List<Tile> hand = new ArrayList<Tile>();
    final Tile newTile = new Tile(tileId, false, false, false);
    for (int i = 0; i < 10; i++) {
      hand.add(newTile);
    }
    Player player = factory.createEmpty(name);
    player.setHand(hand);
    DAO.savePlayer(player);
    interactor.execute(new ClearTilesInputData(name));
    assertEquals(0, DAO.getPlayer(name).getHand().size());
  }

  @Test
  void SuccessTestDora() {
    ClearTilesInputBoundary interactor = new ClearTilesInteractor(
        DAO, new dummyClearTilesOTB(name), ClearTilesInteractor.DORA);
    List<Tile> dora = new ArrayList<Tile>();
    final Tile newTile = new Tile(tileId, false, false, false);
    for (int i = 0; i < 10; i++) {
      dora.add(newTile);
    }
    Player player = factory.createEmpty(name);
    player.setDora(dora);
    DAO.savePlayer(player);
    interactor.execute(new ClearTilesInputData(name));
    assertEquals(0, DAO.getPlayer(name).getDora().size());
  }

  @Test
  void SuccessTestUradora() {
    ClearTilesInputBoundary interactor = new ClearTilesInteractor(
        DAO, new dummyClearTilesOTB(name), ClearTilesInteractor.URADORA);
    List<Tile> uradora = new ArrayList<Tile>();
    final Tile newTile = new Tile(tileId, false, false, false);
    for (int i = 0; i < 10; i++) {
      uradora.add(newTile);
    }
    Player player = factory.createEmpty(name);
    player.setUradora(uradora);
    DAO.savePlayer(player);
    interactor.execute(new ClearTilesInputData(name));
    assertEquals(0, DAO.getPlayer(name).getUradora().size());
  }

  @Test
  void FailTestPlayerNotExist() {
    ClearTilesInputBoundary interactorHand = new ClearTilesInteractor(
        DAO, new dummyClearTilesOTB(name), ClearTilesInteractor.HAND);
    Assertions.assertThrows(
        RuntimeException.class,
        () -> {
          interactorHand.execute(new ClearTilesInputData("no_exist"));
        });
  }

  @Test
  void FailTestInvalidTarget() {
    ClearTilesInputBoundary interactorInvalid = new ClearTilesInteractor(
        DAO, new dummyClearTilesOTB(name), 233);
    Player player = factory.createEmpty(name);
    DAO.savePlayer(player);
    Assertions.assertThrows(
        RuntimeException.class,
        () -> {
          interactorInvalid.execute(new ClearTilesInputData(name));
        });
  }
}

class dummyClearTilesOTB implements ClearTilesOutputBoundary {
  private final String expected;

  dummyClearTilesOTB(String expected) {
    this.expected = expected;
  }

  @Override
  public void prepareSuccessView(ClearTilesOutputData outputData) {
    assertEquals(expected, outputData.getPlayerName());
  }

  @Override
  public void prepareFailView(String errorMessage) {
    throw new ExpectedFail();
  }
}

class ExpectedFail extends RuntimeException {
}