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

package usecase.cleartiles;

import entity.Player;
import entity.Tile;
import java.util.ArrayList;

/**
 * Interactor for the clear_tile use case.
 */
public class ClearTilesInteractor implements ClearTilesInputBoundary {
  // Where to clear the tiles - hand, dora or uradora
  public static final int HAND = 1;
  public static final int DORA = 2;
  public static final int URADORA = 3;
  private final int target;

  private ClearTilesDataAccessInterface dataAccessObj;
  private ClearTilesOutputBoundary presenter;

  /**
   * Constructs a ClearTilesInteractor.
   *
   * @param dataAccessObj the data access object
   * @param presenter     the presenter
   * @param target        the target to clear tiles from
   */
  public ClearTilesInteractor(ClearTilesDataAccessInterface dataAccessObj,
      ClearTilesOutputBoundary presenter,
      int target) {
    this.dataAccessObj = dataAccessObj;
    this.presenter = presenter;
    this.target = target;
  }

  /**
   * Executes the add_tile use case.
   *
   * @param inputData the input data
   */
  @Override
  public void execute(ClearTilesInputData inputData) {
    final String name = inputData.getPlayerName();
    if (!dataAccessObj.existsByName(name)) {
      // This should never happen!
      throw new RuntimeException("Player name not found!");
    } else {
      final Player player = dataAccessObj.getPlayer(name);
      ArrayList<Tile> tileList = new ArrayList<Tile>();
      switch (target) {
        case HAND:
          player.setHand(tileList);
          break;
        case DORA:
          player.setDora(tileList);
          break;
        case URADORA:
          player.setUradora(tileList);
          break;
        default:
          throw new IllegalArgumentException("Invalid target: " + target);
      }
      dataAccessObj.savePlayer(player);

      presenter.prepareSuccessView(new ClearTilesOutputData(false, name));
    }
  }
}
