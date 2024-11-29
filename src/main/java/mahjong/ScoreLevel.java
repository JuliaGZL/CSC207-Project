package mahjong;

import utils.Localization;

/**
 * Enum representing different score levels in Mahjong.
 */
public enum ScoreLevel {
  None,
  Mangan,
  Haneman,
  Baiman,
  Sanbaiman,
  Kazoeyakuman,
  Yakuman1,
  Yakuman2,
  Yakuman3,
  Yakuman4,
  Yakuman5,
  Yakuman6;

  
  /**
   * Localization object for translating tile identifiers to localized strings.
   */
  private static Localization<ScoreLevel> localization = new Localization<>(ScoreLevel::valueOf);

  static {
    localization.initializeTranslator("/localization/scoreLevel_l_english.yaml");
  }

  
  /**
   * Returns the basic string representation of this ScoreLevel.
   *
   * @return the basic name of the ScoreLevel
   */
  @Override
  public String toString() {
    return this.name();
  }

  /**
   * Returns the localized string representation of this ScoreLevel.
   *
   * @return the localized name of the ScoreLevel.
   */
  public String toText() {
    return localization.toText(this);
  }

}
