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
