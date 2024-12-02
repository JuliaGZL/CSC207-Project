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

package unit.view;

import dataaccess.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.Tile;
import interfaceadapter.ViewManagerModel;
import interfaceadapter.readhand.ReadHandController;
import interfaceadapter.readhand.ReadHandPresenter;
import interfaceadapter.readhand.ReadHandViewModel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import mahjong.BaseTile;
import org.junit.jupiter.api.BeforeEach;
import usecase.readhand.ReadHandDataAccessInterface;
import usecase.readhand.ReadHandInputBoundary;
import usecase.readhand.ReadHandInteractor;
import usecase.readhand.ReadHandOutputBoundary;
import view.ReadHandView;

public class ReadHandViewTest {
  ReadHandViewModel readHandViewModel;
  ViewManagerModel viewManagerModel;
  ReadHandView readHandView;

  @BeforeEach
  void setUp() {

  }

  public static void main(String[] args) {
    // Setting up the CLEAN Architecture components
    ReadHandDataAccessInterface DAO = new InMemoryUniversalDataAccessObject();

    ReadHandViewModel readHandViewModel = new ReadHandViewModel();
    ReadHandOutputBoundary presenter = new ReadHandPresenter(readHandViewModel);
    ReadHandInputBoundary interactor = new ReadHandInteractor(DAO, presenter);
    ReadHandController readHandController = new ReadHandController(interactor);

    ReadHandView readHandView = new ReadHandView(readHandViewModel);
    readHandView.setReadHandController(readHandController);

    // Create dummy player
    List<Tile> hand = new ArrayList<>();
    hand.add(new Tile(BaseTile._1m, false, false, false));
    Player player = new Player("default", 100, hand, new ArrayList<>(), new ArrayList<>());
    Boolean[] attributes = { true, false, false, false, false, false, false, false, false };
    player.setAttributes(attributes);
    DAO.savePlayer(player);

    // Create frame to test this button
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(readHandView);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
