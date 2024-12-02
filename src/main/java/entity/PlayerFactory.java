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

package entity;

import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;

/**
 * Factory for creating Player objects.
 */
public class PlayerFactory {

  /**
   * Creates a Player object with the specified name, score, hand, dora, and
   * uradora tiles.
   *
   * @param name    the name of the player
   * @param score   the score of the player
   * @param hand    the hand tiles of the player
   * @param dora    the dora tiles of the player
   * @param uradora the uradora tiles of the player
   * @return a new Player object
   */
  public Player create(String name,
      int score,
      List<BaseTile> hand,
      List<BaseTile> dora,
      List<BaseTile> uradora) {
    List<Tile> handTiles = new ArrayList<Tile>();
    for (BaseTile t : hand) {
      handTiles.add(new Tile(t));
    }

    List<Tile> doraTiles = new ArrayList<Tile>();
    for (BaseTile t : dora) {
      handTiles.add(new Tile(t));
      doraTiles.add(new Tile(t));
    }

    List<Tile> uradoraTiles = new ArrayList<Tile>();
    for (BaseTile t : uradora) {
      handTiles.add(new Tile(t));
      uradoraTiles.add(new Tile(t));
    }

    return new Player(name, score, handTiles, doraTiles, uradoraTiles);
  }

  /**
   * Creates an empty Player object with the specified name and a score of 0.
   *
   * @param name the name of the player
   * @return a new Player object with empty hand, dora, and uradora tiles
   */
  public Player createEmpty(String name) {
    List<Tile> handTiles = new ArrayList<Tile>();
    List<Tile> doraTiles = new ArrayList<Tile>();
    List<Tile> uradoraTiles = new ArrayList<Tile>();
    return new Player(name, 0, handTiles, doraTiles, uradoraTiles);
  }
}
