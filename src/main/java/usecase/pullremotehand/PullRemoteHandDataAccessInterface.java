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

import entity.Tile;
import java.util.List;
import usecase.addtile.AddTileDataAccessInterface;

/**
 * DAI for the pull hand from discord use case.
 */
public interface PullRemoteHandDataAccessInterface extends AddTileDataAccessInterface {
  /**
   * Retrieves the hand from Discord.
   *
   * @return a list of tiles representing the hand from Discord.
   */
  List<Tile> getHandFromDiscord();
}
