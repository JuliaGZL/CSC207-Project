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

package mahjong;

import entity.PlayerStats;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import utils.Pair;

/**
 * This class provides a method to compute the result of a Mahjong hand.
 */
public class HandResult {
  /**
   * The result of the hand,
   * containing a list of Yaku and a pair of integers representing the fan and fu.
   */
  private Pair<List<Yaku>, Pair<Integer, Integer>> result;

  /**
   * The score counter for the hand.
   */
  private ScoreCounter sc;

  /**
   * The singleton instance of HandResult.
   */
  private static HandResult instance;

  /**
   * Private constructor to initialize HandResult with player statistics.
   *
   * @param playerStats the statistics of the player
   */
  private HandResult(PlayerStats playerStats) {
    YakuCalculator inst = new YakuCalculator(playerStats);
    this.result = inst.yakuCounter();
    this.sc = new ScoreCounter(
        result.getSnd().getFst(),
        result.getSnd().getSnd(),
        playerStats.isOya(),
        playerStats.isTsumo(),
        inst.hasYakuman());
  }

  /**
   * Returns the singleton instance of HandResult, initializing it if necessary.
   *
   * @param playerStats the statistics of the player
   * @return the singleton instance of HandResult
   */
  public static HandResult getInstance(PlayerStats playerStats) {
    instance = new HandResult(playerStats);
    return instance;
  }

  /**
   * Returns the singleton instance of HandResult.
   *
   * @return the singleton instance of HandResult
   * @throws IllegalStateException if the instance has not been initialized
   */
  public static HandResult getInstance() {
    if (instance == null) {
      throw new IllegalStateException("HandResult instance has not been initialized.");
    }
    return instance;
  }

  /**
   * Resets the singleton instance of HandResult.
   */
  public static void resetInstance() {
    instance = null;
  }

  /**
   * Displays the result of the hand in a formatted list of strings.
   *
   * @return a list of strings representing the hand result
   */
  public List<String> displayHandResult() {
    List<String> res = Arrays.asList(
        sc.getScoreLevel().toText(),
        result.getFst().stream().map(Yaku::toText).collect(Collectors.joining(",")),
        ScoreDisplayFormatter.formatFan(result.getSnd().getFst()),
        ScoreDisplayFormatter.formatFu(result.getSnd().getSnd()),
        sc.toFormattedScores());
    return res;
  }

  /**
   * Gets the total score of the hand result.
   *
   * @return the total score
   */
  public int getHandScoreResult() {
    return sc.getScores().get(0);
  }

  /**
   * Gets the list of Yaku for the hand.
   *
   * @return the list of Yaku
   */
  public List<Yaku> getHandYakuList() {
    return result.getFst();
  }

  /**
   * Gets the fan value of the hand.
   *
   * @return the fan value
   */
  public int getHandFan() {
    return result.getSnd().getFst();
  }

  /**
   * Gets the fu value of the hand.
   *
   * @return the fu value
   */
  public int getHandFu() {
    return result.getSnd().getSnd();
  }

  /**
   * Retrieves the score level of the current hand.
   *
   * @return the score level of the current hand as a {@link ScoreLevel} object.
   */
  public ScoreLevel getScoreLevel() {
    return sc.getScoreLevel();
  }
}