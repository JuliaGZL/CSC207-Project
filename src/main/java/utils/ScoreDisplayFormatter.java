package utils;

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
    return String.format(getLocalization().toString("displayFan"), fan);
  }

  /**
   * Formats the fu value into a localized display string.
   *
   * @param fu the fu value
   * @return the formatted fu string
   */
  public static String formatFu(int fu) {
    return String.format(getLocalization().toString("displayFu"), fu);
  }

  /**
   * Formats the score into a localized display string based on the player's status and scores.
   *
   * @param isOya   true if the player is the dealer (Oya)
   * @param isTsumo true if the win was by self-draw (Tsumo)
   * @param scores  a pair containing the main and secondary scores
   * @return the formatted score string
   */
  public static String formatScore(boolean isOya, boolean isTsumo, Pair<Integer, Integer> scores) {
    if (isOya) {
      if (isTsumo) {
        return String.format(getLocalization().toString("displayScoresOyaTsumo"), scores.getFst());
      } else {
        return String.format(getLocalization().toString("displayScoresOyaRon"), scores.getFst());
      }
    } else {
      if (isTsumo) {
        return String.format(
                getLocalization().toString("displayScoresChildTsumo"),
                scores.getFst(), scores.getSnd());
      } else {
        return String.format(getLocalization().toString("displayScoresChildRon"), scores.getFst());
      }
    }
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