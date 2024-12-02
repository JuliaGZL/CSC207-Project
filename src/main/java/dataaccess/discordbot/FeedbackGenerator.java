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

package dataaccess.discordbot;

import entity.PlayerStats;
import entity.PlayerStatsBuilder;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import mahjong.HandResult;

/**
 * This class generates feedback for a given set of Mahjong tiles.
 */
public class FeedbackGenerator {

  /**
   * Generates a list of result strings based on the given tiles.
   *
   * @param tiles a string representing the tiles
   * @return a list of result strings
   */
  public static List<String> getResultString(String tiles) {
    List<Tile> tilesList = getTiles(tiles);
    PlayerStats playerStats = new PlayerStatsBuilder().setHand(tilesList).build();
    HandResult handResult = HandResult.getInstance(playerStats);
    return handResult.displayHandResult();
  }

  /**
   * Converts a string of tiles into a list of Tile objects.
   *
   * @param tiles a string representing the tiles
   * @return a list of Tile objects
   */
  public static List<Tile> getTiles(String tiles) {
    List<Tile> tilesList = new ArrayList<>();
    String[] tokens = tiles.split(" ");
    List<Integer> numbers = new ArrayList<>();
    for (String token : tokens) {
      if (Character.isDigit(token.charAt(0))) {
        numbers.add(Integer.parseInt(token));
      } else {
        char suit = token.charAt(0);
        for (int num : numbers) {
          String tile = "_" + num + suit;
          Tile newTile = new Tile(BaseTile.valueOf(tile), false, false, false);
          tilesList.add(newTile);
        }
        numbers.clear();
      }
    }
    return tilesList;
  }
}
