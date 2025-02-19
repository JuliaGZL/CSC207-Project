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

package unit.usecase.readhand;

import dataaccess.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.PlayerFactory;
import entity.Tile;
import java.util.List;
import mahjong.BaseTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.readhand.ReadHandDataAccessInterface;
import usecase.readhand.ReadHandInputBoundary;
import usecase.readhand.ReadHandInputData;
import usecase.readhand.ReadHandInteractor;
import usecase.readhand.ReadHandOutputBoundary;
import usecase.readhand.ReadHandOutputData;

public class ReadHandInteractorTest {
  private final PlayerFactory factory = new PlayerFactory();
  private final String name = "new_player";
  private final String[] attrib = {
      "Riichi", "Double Riichi", "One-shot", "Under the Sea",
      "Under the River", "After a Kan", "Robbing a Kan", "Tenhou", "Chiihou"
  };

  private ReadHandDataAccessInterface DAO;
  private Player player;
  private MockOTB presenter;
  private ReadHandInputBoundary interactor;

  @BeforeEach
  void setup() {
    player = factory.createEmpty(name);
    DAO = new InMemoryUniversalDataAccessObject();
    presenter = new MockOTB();
    interactor = new ReadHandInteractor(DAO, presenter);
  }

  @Test
  void testHandEmptyAllFalse() {
    DAO.savePlayer(player);
    interactor.execute(new ReadHandInputData(name, attrib));
    final String expected = "Your hand is empty."
        + "There are no dora tiles.There are no uradora tiles."
        + "That's all tiles.You have no special attributes.";
    Assertions.assertEquals(expected, presenter.getMessage());
  }

  @Test
  void testHandEmpty() {
    Boolean[] newAttrib = {
        true, false, false,
        false, false, false,
        false, false, false
    };
    player.setAttributes(newAttrib);
    DAO.savePlayer(player);
    interactor.execute(new ReadHandInputData(name, attrib));
    final String expected = "Your hand is empty."
        + "There are no dora tiles.There are no uradora tiles."
        + "That's all tiles.You have the following special attributes: Riichi. Finish. ";
    Assertions.assertEquals(expected, presenter.getMessage());
  }

  @Test
  void testHandNonEmptyAllFalse() {
    List<Tile> hand = List.of(new Tile[] { new Tile(BaseTile._1m) });
    player.setHand(hand);
    DAO.savePlayer(player);
    interactor.execute(new ReadHandInputData(name, attrib));
    final String expected = "Your hand includes 1 Man."
        + "There are no dora tiles.There are no uradora tiles."
        + "That's all tiles.You have no special attributes.";
    Assertions.assertEquals(expected, presenter.getMessage());
  }

  @Test
  void testHandNonEmpty() {
    Boolean[] newAttrib = {
        true, true, false,
        false, false, false,
        false, false, false
    };
    player.setAttributes(newAttrib);
    List<Tile> hand = List.of(new Tile[] { new Tile(BaseTile._2m) });
    player.setHand(hand);
    List<Tile> dora = List.of(new Tile[] { new Tile(BaseTile._2m) });
    player.setDora(dora);
    List<Tile> uradora = List.of(new Tile[] { new Tile(BaseTile._2m) });
    player.setUradora(uradora);
    DAO.savePlayer(player);
    interactor.execute(new ReadHandInputData(name, attrib));
    final String expected = "Your hand includes 2 Man. The dora tiles are 2 Man. " +
        "The uradora tiles are 2 Man. That's all tiles." +
        "You have the following special attributes: Riichi, Double Riichi. Finish. ";
    Assertions.assertEquals(expected, presenter.getMessage());
  }
}

class MockOTB implements ReadHandOutputBoundary {
  private String message;

  @Override
  public void prepareSuccessView(ReadHandOutputData outputData) {
    message = outputData.getHandInfo();
  }

  @Override
  public void prepareFailView(String errorMessage) {

  }

  public String getMessage() {
    return message;
  }
}
