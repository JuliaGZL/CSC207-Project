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

package unit.usecase.changeplayer;

import dataaccess.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.PlayerFactory;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecase.changeplayer.ChangePlayerInputData;
import usecase.changeplayer.ChangePlayerInteractor;
import usecase.changeplayer.ChangePlayerOutputBoundary;
import usecase.changeplayer.ChangePlayerOutputData;

public class ChangePlayerInteractorTest {
  private final PlayerFactory factor = new PlayerFactory();
  private final String playerName1 = "player1";
  private final String playerName2 = "player2";
  private final Tile newTile = new Tile(BaseTile._1m);

  @Test
  void SuccessTestExistingPlayer() {
    InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();
    Player player1 = factor.createEmpty(playerName1);
    Player player2 = factor.createEmpty(playerName2);
    List<Tile> hand = new ArrayList<>();
    hand.add(newTile);
    player2.setHand(hand);
    DAO.savePlayer(player1);
    DAO.savePlayer(player2);
    MockOTB mockOTB = new MockOTB();
    ChangePlayerInteractor interactor = new ChangePlayerInteractor(DAO, mockOTB);
    interactor.execute(new ChangePlayerInputData(playerName2));
    Assertions.assertEquals(mockOTB.getPlayerName(), playerName2);
    Assertions.assertEquals(DAO.getPlayer(mockOTB.getPlayerName()).getHand().size(), 1);
  }

  @Test
  void SuccessTestNewPlayer() {
    InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();
    Player player1 = factor.createEmpty(playerName1);
    DAO.savePlayer(player1);
    MockOTB mockOTB = new MockOTB();
    ChangePlayerInteractor interactor = new ChangePlayerInteractor(DAO, mockOTB);
    interactor.execute(new ChangePlayerInputData(playerName2));
    Assertions.assertEquals(mockOTB.getPlayerName(), playerName2);
    Assertions.assertEquals(DAO.getPlayer(mockOTB.getPlayerName()).getHand().size(), 0);
  }

  @Test
  void FailTest() {
    // this never happens
  }
}

class MockOTB implements ChangePlayerOutputBoundary {
  private String playerName = null;

  public String getPlayerName() {
    return playerName;
  }

  /**
   * Prepares the success view for the use case.
   *
   * @param outputData the output data
   */
  @Override
  public void prepareSuccessView(ChangePlayerOutputData outputData) {
    playerName = outputData.getPlayerName();
  }

  /**
   * Prepares the failure view for the use case.
   *
   * @param errorMessage the explanation of the failure
   */
  @Override
  public void prepareFailView(String errorMessage) {
    // should never happen
  }
}
