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

package unit.usecase.pullremotehand;

import dataaccess.InMemoryUniversalDataAccessObject;
import entity.PlayerFactory;
import entity.Tile;
import java.util.List;
import mahjong.BaseTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecase.pullremotehand.PullRemoteHandDataAccessInterface;
import usecase.pullremotehand.PullRemoteHandInputBoundary;
import usecase.pullremotehand.PullRemoteHandInputData;
import usecase.pullremotehand.PullRemoteHandInteractor;
import usecase.pullremotehand.PullRemoteHandOutputBoundary;
import usecase.pullremotehand.PullRemoteHandOutputData;

public class PullRemoteHandInteractorTest {
  @Test
  void testUsual() {
    final PlayerFactory factory = new PlayerFactory();
    final String name = "new_player";
    final MockDAO DAO = new MockDAO();
    final MockOTB presenter = new MockOTB();
    final PullRemoteHandInputBoundary interactor = new PullRemoteHandInteractor(DAO, presenter);
    final List<BaseTile> inputBase = List.of(new BaseTile[] { BaseTile._1m });
    final List<Tile> input = List.of(new Tile[] { new Tile(BaseTile._1m) });

    DAO.savePlayer(factory.createEmpty(name));
    DAO.setInput(input);
    interactor.execute(new PullRemoteHandInputData(name));

    Assertions.assertEquals(presenter.getHandBaseTile(), inputBase);
    Assertions.assertEquals(DAO.getPlayer(name).getHand(), input);
  }
}

class MockOTB implements PullRemoteHandOutputBoundary {
  private List<BaseTile> handBaseTile;

  public List<BaseTile> getHandBaseTile() {
    return handBaseTile;
  }

  @Override
  public void prepareSuccessView(PullRemoteHandOutputData outputData) {
    handBaseTile = outputData.getPlayerHand();
  }

  @Override
  public void prepareFailView(String errorMessage) {

  }
}

class MockDAO extends InMemoryUniversalDataAccessObject implements PullRemoteHandDataAccessInterface {
  private List<Tile> input;

  public void setInput(List<Tile> input) {
    this.input = input;
  }

  @Override
  public List<Tile> getHandFromDiscord() {
    return input;
  }
}
