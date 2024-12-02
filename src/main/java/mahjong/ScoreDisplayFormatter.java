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

import java.util.List;
import utils.Localization;

/**
 * Utility class for formatting display strings in the Mahjong game.
 */
public class ScoreDisplayFormatter {
  private static Localization<String> localization = new Localization<>(String::valueOf);
  private static boolean isInitialized = false;

  /**
   * Formats the fan value into a localized display string.
   *
   * @param fan the fan value
   * @return the formatted fan string
   */
  public static String formatFan(int fan) {
    return String.format(getLocalization().toText("displayFan"), fan);
  }

  /**
   * Formats the fu value into a localized display string.
   *
   * @param fu the fu value
   * @return the formatted fu string
   */
  public static String formatFu(int fu) {
    return String.format(getLocalization().toText("displayFu"), fu);
  }

  /**
   * Formats the score into a localized display string based on the player's
   * status and scores.
   *
   * @param isOya   true if the player is the dealer (Oya)
   * @param isTsumo true if the win was by self-draw (Tsumo)
   * @param scores  a pair containing the main and secondary scores
   * @return the formatted score string
   */
  public static String formatScore(boolean isOya, boolean isTsumo, List<Integer> scores) {
    String res;
    if (isOya) {
      if (isTsumo) {
        res = String.format(getLocalization().toText("displayScoresOyaTsumo"), scores.get(1));
      } else {
        res = String.format(getLocalization().toText("displayScoresOyaRon"), scores.get(1));
      }
    } else {
      if (isTsumo) {
        res = String.format(
            getLocalization().toText("displayScoresChildTsumo"),
            scores.get(1), scores.get(2));
      } else {
        res = String.format(getLocalization().toText("displayScoresChildRon"), scores.get(1));
      }
    }
    return getLocalization().toText("displayScorePrefix") + res;
  }

  /**
   * Retrieves the localization instance used for string translations.
   *
   * @return the localization instance
   */
  public static Localization<String> getLocalization() {
    if (!isInitialized) {
      localization.initializeTranslator("/localization/scoreDisplay_l_english.yaml");
      isInitialized = true;
    }
    return localization;
  }
}