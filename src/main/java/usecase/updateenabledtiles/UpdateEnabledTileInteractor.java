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

package usecase.updateenabledtiles;

import entity.Player;
import entity.Tile;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import mahjong.BaseTile;

/**
 * Interactor of update enabled tile use case.
 */
public class UpdateEnabledTileInteractor implements UpdateEnabledTilesInputBoundary {

  private final UpdateEnabledTileDataAccessInterface dataAccessObj;
  private final UpdateEnabledTileOutputBoundary presenter;

  /**
   * Constructs an UpdateEnabledTileInteractor with the specified data access
   * object and presenter.
   *
   * @param dataAccessObj the data access object
   * @param presenter     the presenter
   */
  public UpdateEnabledTileInteractor(UpdateEnabledTileDataAccessInterface dataAccessObj,
      UpdateEnabledTileOutputBoundary presenter) {
    this.dataAccessObj = dataAccessObj;
    this.presenter = presenter;
  }

  /**
   * Executes the update enabled tile use case.
   *
   * @param inputData the input data
   */
  @Override
  public void execute(UpdateEnabledTileInputData inputData) {
    final Player player = dataAccessObj.getPlayer(inputData.getPlayerName());
    final String target = inputData.getTarget();

    Set<BaseTile> enabledTiles = new HashSet<BaseTile>();
    UpdateEnabledTileOutputData outputData;

    // check whether already full - if full, disable all tiles in tile selector.
    switch (target) {
      case "hand": {
        final int maxTiles = 14;
        if (player.getHand().size() >= maxTiles) {
          outputData = new UpdateEnabledTileOutputData(enabledTiles);
          presenter.prepareSuccessView(outputData);
          return;
        }
        break;
      }
      case "dora": {
        final int maxTiles = 4;
        if (player.getDora().size() >= maxTiles) {
          outputData = new UpdateEnabledTileOutputData(enabledTiles);
          presenter.prepareSuccessView(outputData);
          return;
        }
        break;
      }
      case "uradora": {
        final int maxTiles = 4;
        if (player.getUradora().size() >= maxTiles) {
          outputData = new UpdateEnabledTileOutputData(enabledTiles);
          presenter.prepareSuccessView(outputData);
          return;
        }
        break;
      }
      default:
        throw new IllegalArgumentException("Invalid target: " + target);
    }

    // build enabled tile set
    enabledTiles = buildEnabledSet(player.getHand(), player.getDora(), player.getUradora());
    presenter.prepareSuccessView(new UpdateEnabledTileOutputData(enabledTiles));
  }

  /**
   * Builds a set of enabled tiles based on the player's hand, dora, and uradora
   * tiles.
   *
   * @param hand    the player's hand tiles
   * @param dora    the player's dora tiles
   * @param uradora the player's uradora tiles
   * @return a set of enabled tiles
   */
  private Set<BaseTile> buildEnabledSet(List<Tile> hand,
      List<Tile> dora,
      List<Tile> uradora) {
    // A tile is enabled if and only if its total count in the three lists <4.
    Set<BaseTile> ret = new HashSet<BaseTile>();
    for (BaseTile t : BaseTile.values()) {
      int count = 0;
      for (Tile tile : hand) {
        if (tile.getBaseTile() == t) {
          count++;
        }
      }
      for (Tile tile : dora) {
        if (tile.getBaseTile() == t) {
          count++;
        }
      }
      for (Tile tile : uradora) {
        if (tile.getBaseTile() == t) {
          count++;
        }
      }
      if (count < 4) {
        ret.add(t);
      }
    }
    return ret;
  }
}
