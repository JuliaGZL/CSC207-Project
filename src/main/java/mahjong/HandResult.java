package mahjong;

import entity.PlayerStats;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import utils.Pair;
import utils.ScoreDisplayFormatter;

/**
 * This class provides a method to compute the result of a Mahjong hand.
 */
public class HandResult {
  private Pair<List<Yaku>, Pair<Integer, Integer>> result;
  private ScoreCounter sc;

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
            playerStats.isTsumo());
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
            result.getFst().stream().map(Yaku::toString).collect(Collectors.joining(",")),
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
    Pair<Integer, Integer> scores = sc.getScores();
    return scores.getFst() + scores.getSnd();
  }
}