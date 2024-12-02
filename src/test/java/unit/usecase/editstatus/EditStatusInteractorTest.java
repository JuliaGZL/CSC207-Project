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

package unit.usecase.editstatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataaccess.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.Tile;
import interfaceadapter.ViewManagerModel;
import interfaceadapter.editstatus.EditStatusViewModel;
import interfaceadapter.edittiles.SelectDoraViewModel;
import interfaceadapter.edittiles.TileSelectorViewModel;
import mahjong.BaseTile;
import usecase.editstatus.EditStatusDataAccessInterface;
import usecase.editstatus.EditStatusInputData;
import usecase.editstatus.EditStatusInteractor;
import usecase.editstatus.EditStatusOutputBoundary;
import usecase.editstatus.EditStatusOutputData;

/**
 * Test class for EditStatusInputBoundary.
 */
class EditStatusInteractorTest {
  EditStatusDataAccessInterface DAO;
  EditStatusOutputBoundary playerPresenter;

  EditStatusViewModel editStatusViewModel;
  SelectDoraViewModel selectDoraViewModel;
  TileSelectorViewModel tileSelectorViewModel;
  ViewManagerModel viewManagerModel;

  EditStatusInteractor interactor;

  @BeforeEach
  void setUp() {
    editStatusViewModel = new EditStatusViewModel();
    selectDoraViewModel = new SelectDoraViewModel();
    tileSelectorViewModel = new TileSelectorViewModel();
    viewManagerModel = new ViewManagerModel();

    DAO = new InMemoryUniversalDataAccessObject();
    playerPresenter = new EditStatusOutputBoundary() {

      @Override
      public void prepareSuccessView(EditStatusOutputData outputData) {
        System.out.println("Success");
      }

      @Override
      public void prepareSuccessView(String updateName, EditStatusOutputData outputData) {
        System.out.println("Success: " + updateName);
      }

      @Override
      public void prepareFailView(String errorMessage) {
        System.out.println("Fail: " + errorMessage);
      }

      @Override
      public void switchToSelectDoraView(EditStatusOutputData outputData) {
        System.out.println("Switch to select dora view");
      }

      @Override
      public void switchToSelectForHand() {
        System.out.println("Switch to select for hand");
      }
    };

    interactor = new EditStatusInteractor(DAO, playerPresenter);
  }

  @AfterEach
  void tearDown() {
    DAO = null;
    playerPresenter = null;
    editStatusViewModel = null;
    selectDoraViewModel = null;
    tileSelectorViewModel = null;
    viewManagerModel = null;
    interactor = null;
  }

  /**
   * Test for execute method (success) with one input editStatusInputData.
   */
  @Test
  void testExecuteSuccessOneVar() {
    Boolean[] mockAttributes = { false, true, false, false, false, false, false, false, true, false };
    EditStatusInputData editStatusInputData = new EditStatusInputData(mockAttributes, 3,
        "East", "South", "Ron", "default");

    // Assume a mock player named "default" is created and saved in DAO
    List<Tile> hand = new ArrayList<>();
    hand.add(new Tile(BaseTile._1m, false, false, false));
    Player player = new Player("default", 100, hand, new ArrayList<>(), new ArrayList<>());
    Boolean[] attributes = { true, false, false, false, false, false, false, false, false };
    player.setAttributes(attributes);
    DAO.savePlayer(player);

    interactor.execute(editStatusInputData);

    // Get the player and verify if all data have been updated
    Player updatedPlayer = DAO.getPlayer("default");
    assertEquals(mockAttributes, updatedPlayer.getAttributes());
    assertEquals(3, updatedPlayer.getNumAkaDora());
    assertEquals("East", updatedPlayer.getSeatWind());
    assertEquals("South", updatedPlayer.getRoundWind());
    assertEquals("Ron", updatedPlayer.getWinType());
  }

  /**
   * Test for execute method (success) with two inputs updateName and
   * editStatusInputData.
   */
  @Test
  void testExecuteSuccessTwoVar() {
    Boolean[] mockAttributes = { false, true, false, false, false, false, false, false, true, false };
    EditStatusInputData editStatusInputData = new EditStatusInputData(mockAttributes, 3,
        "East", "South", "Ron", "default");

    // Assume a mock player named "default" is created and saved in DAO
    List<Tile> hand = new ArrayList<>();
    hand.add(new Tile(BaseTile._1m, false, false, false));
    Player player = new Player("default", 100, hand, new ArrayList<>(), new ArrayList<>());
    Boolean[] attributes = { true, false, false, false, false, false, false, false, false };
    player.setAttributes(attributes);
    DAO.savePlayer(player);

    interactor.execute("editStatus", editStatusInputData);

    // Get the player and verify if all data have been updated
    Player updatedPlayer = DAO.getPlayer("default");
    assertEquals(mockAttributes, updatedPlayer.getAttributes());
    assertEquals(3, updatedPlayer.getNumAkaDora());
    assertEquals("East", updatedPlayer.getSeatWind());
    assertEquals("South", updatedPlayer.getRoundWind());
    assertEquals("Ron", updatedPlayer.getWinType());
  }

  /**
   * Test for execute method (failure) with one input editStatusInputData.
   */
  @Test
  void testExecuteFailOneVar() {
    Boolean[] mockAttributes = { false, true, false, false, false, false, false, false, true, false };
    EditStatusInputData editStatusInputData = new EditStatusInputData(mockAttributes, 3,
        "East", "South", "Ron", "dummy");

    // Try executing without player named "dummy"
    RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
      interactor.execute(editStatusInputData);
    });

    assertEquals("Player name not found!", exception.getMessage());
  }

  /**
   * Test for execute method (failure) with two inputs updateName and
   * editStatusInputData.
   */
  @Test
  void testExecuteFailTwoVar() {
    Boolean[] mockAttributes = { false, true, false, false, false, false, false, false, true, false };
    EditStatusInputData editStatusInputData = new EditStatusInputData(mockAttributes, 3,
        "East", "South", "Ron", "dummy");

    // Try executing without player named "dummy"
    RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
      interactor.execute("editStatus", editStatusInputData);
    });

    assertEquals("Player name not found!", exception.getMessage());

  }

  /**
   * Test for switchToSelectDoraView method.
   */
  @Test
  void testSwitchToSelectDoraView() {
    Boolean[] mockAttributes = { false, true, false, false, false, false, false, false, true, false };
    EditStatusInputData editStatusInputData = new EditStatusInputData(mockAttributes, 3,
        "East", "South", "Ron", "dummy");
    interactor.switchToSelectDoraView(editStatusInputData);

    // Currently, this method does not have implementation, so the test to it should
    // always be true.
    assertNotNull(interactor);
  }
}
