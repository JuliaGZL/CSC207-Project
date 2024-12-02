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

package unit.usecase.updateenabledtiles;

import dataaccess.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.PlayerFactory;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import mahjong.BaseTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecase.updateenabledtiles.UpdateEnabledTileInputData;
import usecase.updateenabledtiles.UpdateEnabledTileInteractor;
import usecase.updateenabledtiles.UpdateEnabledTileOutputBoundary;
import usecase.updateenabledtiles.UpdateEnabledTileOutputData;

public class UpdateEnabledTilesInteractorTest {
  private final PlayerFactory factory = new PlayerFactory();
  private final String playerName = "player";

  @Test
  void SuccessTestAllEnabled() {
    InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();
    Player player = factory.createEmpty(playerName);
    DAO.savePlayer(player);
    MockOTB mockOTB = new MockOTB();
    UpdateEnabledTileInteractor interactor = new UpdateEnabledTileInteractor(DAO, mockOTB);

    // test for hand
    interactor.execute(new UpdateEnabledTileInputData(playerName, "hand"));
    Assertions.assertTrue(mockOTB.getResult().containsAll(List.of(BaseTile.values())));

    // test for dora
    interactor.execute(new UpdateEnabledTileInputData(playerName, "dora"));
    Assertions.assertTrue(mockOTB.getResult().containsAll(List.of(BaseTile.values())));

    // test for uradora
    interactor.execute(new UpdateEnabledTileInputData(playerName, "uradora"));
    Assertions.assertTrue(mockOTB.getResult().containsAll(List.of(BaseTile.values())));
  }

  @Test
  void SuccessTestPartialDisabled() {
    final BaseTile newTile = BaseTile._1m;
    InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();
    Player player = factory.createEmpty(playerName);
    List<Tile> hand = List.of(new Tile[] {
        new Tile(newTile), new Tile(newTile)
    });
    List<Tile> dora = List.of(new Tile[] {
        new Tile(newTile)
    });
    List<Tile> uradora = List.of(new Tile[] {
        new Tile(newTile)
    });
    player.setHand(hand);
    player.setDora(dora);
    player.setUradora(uradora);
    DAO.savePlayer(player);
    MockOTB mockOTB = new MockOTB();
    UpdateEnabledTileInteractor interactor = new UpdateEnabledTileInteractor(DAO, mockOTB);

    Set<BaseTile> expectedEnabledTiles = new java.util.HashSet<>(Set.of(BaseTile.values()));
    expectedEnabledTiles.remove(newTile);

    // test for hand
    interactor.execute(new UpdateEnabledTileInputData(playerName, "hand"));
    Assertions.assertEquals(mockOTB.getResult(), expectedEnabledTiles);

    // test for dora
    interactor.execute(new UpdateEnabledTileInputData(playerName, "dora"));
    Assertions.assertEquals(mockOTB.getResult(), expectedEnabledTiles);

    // test for uradora
    interactor.execute(new UpdateEnabledTileInputData(playerName, "uradora"));
    Assertions.assertEquals(mockOTB.getResult(), expectedEnabledTiles);
  }

  @Test
  void SuccessTestAllDisabled() {
    final BaseTile newTile = BaseTile._1m;
    InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();
    Player player = factory.createEmpty(playerName);
    List<Tile> hand = new ArrayList<>();
    List<Tile> dora = new ArrayList<>();
    List<Tile> uradora = new ArrayList<>();
    for (int i = 0; i < 14; i++) {
      hand.add(new Tile(newTile));
    }
    for (int i = 0; i < 4; i++) {
      dora.add(new Tile(newTile));
      uradora.add(new Tile(newTile));
    }
    player.setHand(hand);
    player.setDora(dora);
    player.setUradora(uradora);
    DAO.savePlayer(player);
    MockOTB mockOTB = new MockOTB();
    UpdateEnabledTileInteractor interactor = new UpdateEnabledTileInteractor(DAO, mockOTB);

    // test for hand
    interactor.execute(new UpdateEnabledTileInputData(playerName, "hand"));
    Assertions.assertEquals(0, mockOTB.getResult().size());

    // test for dora
    interactor.execute(new UpdateEnabledTileInputData(playerName, "dora"));
    Assertions.assertEquals(0, mockOTB.getResult().size());

    // test for uradora
    interactor.execute(new UpdateEnabledTileInputData(playerName, "uradora"));
    Assertions.assertEquals(0, mockOTB.getResult().size());
  }

  @Test
  void FailTest() {
    // never happens
  }
}

class MockOTB implements UpdateEnabledTileOutputBoundary {
  private Set<BaseTile> result = null;

  public Set<BaseTile> getResult() {
    return result;
  }

  /**
   * Prepares the success view for the use case.
   *
   * @param outputData the output data
   */
  @Override
  public void prepareSuccessView(UpdateEnabledTileOutputData outputData) {
    result = outputData.getEnabledTiles();
  }

  /**
   * Prepares the failure view for the use case.
   *
   * @param errorMessage the explanation of the failure
   */
  @Override
  public void prepareFailView(String errorMessage) {
    // shouldn't happen
  }
}