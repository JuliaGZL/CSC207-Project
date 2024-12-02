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

package usecase.removetile;

import entity.Player;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;

/**
 * Interactor for the remove_tile use case.
 */
public class RemoveTileInteractor implements RemoveTileInputBoundary {
  // Where to remove the tile from - hand, dora or uradora
  public static final int HAND = 1;
  public static final int DORA = 2;
  public static final int URADORA = 3;
  private final int target;

  private RemoveTileDataAccessInterface dataAccessObj;
  private RemoveTileOutputBoundary presenter;

  /**
   * Constructs a RemoveTileInteractor.
   *
   * @param dataAccessObj the data access object
   * @param presenter     the presenter
   * @param target        the target location to remove the tile from
   */
  public RemoveTileInteractor(RemoveTileDataAccessInterface dataAccessObj,
      RemoveTileOutputBoundary presenter,
      int target) {
    this.dataAccessObj = dataAccessObj;
    this.presenter = presenter;
    this.target = target;
  }

  /**
   * Executes the remove_tile use case.
   *
   * @param inputData the input data
   */
  @Override
  public void execute(RemoveTileInputData inputData) {
    final String name = inputData.getPlayerName();
    final BaseTile id = inputData.getTileId();
    if (!dataAccessObj.existsByName(name)) {
      // This should never happen!
      throw new RuntimeException("Player name not found!");
    } else {
      final Player player = dataAccessObj.getPlayer(name);
      List<Tile> tileList;
      switch (target) {
        case HAND:
          tileList = player.getHand();
          break;
        case DORA:
          tileList = player.getDora();
          break;
        case URADORA:
          tileList = player.getUradora();
          break;
        default:
          throw new IllegalArgumentException("Invalid target: " + target);
      }
      removeTile(id, tileList, player, name, target);
    }
  }

  /**
   * Removes a tile from the specified list and updates the player.
   *
   * @param id       the tile id
   * @param tileList the list of tiles
   * @param player   the player
   * @param name     the player name
   * @param target   the target location to remove the tile from
   */
  private void removeTile(BaseTile id, List<Tile> tileList,
      Player player, String name, int target) {
    // id == null is a special case that just reads the list without altering it.
    if (id != null) {
      for (Tile tile : tileList) {
        if (Objects.equals(tile.getBaseTile(), id)) {
          tileList.remove(tile);
          break;
        }
      }
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
    }

    final List<BaseTile> idList = new ArrayList<BaseTile>();
    final List<String> nameList = new ArrayList<String>();
    final List<String> iconList = new ArrayList<String>();
    for (Tile tile : tileList) {
      idList.add(tile.getBaseTile());
      nameList.add(tile.toString());
      iconList.add(BaseTileToPathMapping.getTilePath(tile.getBaseTile()));
    }
    final RemoveTileOutputData output = new RemoveTileOutputData(
        false, name, idList, nameList, iconList);

    presenter.prepareSuccessView(output);
  }
}
