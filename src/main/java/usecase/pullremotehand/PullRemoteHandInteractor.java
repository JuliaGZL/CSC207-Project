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

package usecase.pullremotehand;

import entity.Player;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;

/**
 * Interactor of the pull hand from discord use case.
 */
public class PullRemoteHandInteractor implements PullRemoteHandInputBoundary {
  private final PullRemoteHandDataAccessInterface dataAccessObj;
  private final PullRemoteHandOutputBoundary presenter;

  /**
   * Constructs a PullRemoteHandInteractor.
   *
   * @param dataAccessObj the data access object
   * @param presenter     the presenter
   */
  public PullRemoteHandInteractor(PullRemoteHandDataAccessInterface dataAccessObj,
      PullRemoteHandOutputBoundary presenter) {
    this.dataAccessObj = dataAccessObj;
    this.presenter = presenter;
  }

  /**
   * Executes the use case.
   *
   * @param inputData the input data
   */
  @Override
  public void execute(PullRemoteHandInputData inputData) {
    List<Tile> newHand = dataAccessObj.getHandFromDiscord();
    Player player = dataAccessObj.getPlayer(inputData.getPlayerName());
    player.setHand(newHand);
    dataAccessObj.savePlayer(player);

    List<BaseTile> baseTileList = new ArrayList<BaseTile>();
    List<String> nameList = new ArrayList<String>();
    List<String> iconList = new ArrayList<String>();

    for (Tile tile : newHand) {
      baseTileList.add(tile.getTile());
      nameList.add(tile.getTile().toText());
      iconList.add(BaseTileToPathMapping.getTilePath(tile.getTile()));
    }

    PullRemoteHandOutputData outputData = new PullRemoteHandOutputData(
        inputData.getPlayerName(), baseTileList, nameList, iconList);
    presenter.prepareSuccessView(outputData);
  }
}
