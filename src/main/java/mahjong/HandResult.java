package mahjong;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import utils.Pair;
import utils.ScoreDisplayFormatter;

/**
 * This class provides a method to compute the result of a Mahjong hand.
 */
public class HandResult {

  /**
   * Computes the result of a Mahjong hand based on the player's statistics.
   *
   * @param playerStats the statistics of the player
   * @return a list of strings representing the hand result, including the Yaku,
   *         scores, and formatted scores
   */
  public static List<String> computeHandResult(PlayerStats playerStats) {
    YakuCalculator inst = new YakuCalculator(playerStats);
    Pair<List<Yaku>, Pair<Integer, Integer>> result = inst.yakuCounter();
    ScoreCounter sc = new ScoreCounter(
        result.getSnd().getFst(),
        result.getSnd().getSnd(),
        playerStats.isOya(),
        playerStats.isTsumo());
    List<String> res = Arrays.asList(
        result.getFst().stream().map(Yaku::toString).collect(Collectors.joining(",")),
        ScoreDisplayFormatter.formatFan(result.getSnd().getFst()),
        ScoreDisplayFormatter.formatFu(result.getSnd().getSnd()),
        sc.toFormattedScores());
    return res;
  }
}
