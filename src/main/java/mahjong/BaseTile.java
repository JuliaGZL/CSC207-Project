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
 * An enumeration of base tiles used in Mahjong, including numbered tiles for
 * the Man (m),
 * Pin (p), Sou (s) suits, and honor tiles (z).
 */
public enum BaseTile {
  /** The 1 of Man (characters) suit. */
  _1m,
  /** The 2 of Man (characters) suit. */
  _2m,
  /** The 3 of Man (characters) suit. */
  _3m,
  /** The 4 of Man (characters) suit. */
  _4m,
  /** The 5 of Man (characters) suit. */
  _5m,
  /** The 6 of Man (characters) suit. */
  _6m,
  /** The 7 of Man (characters) suit. */
  _7m,
  /** The 8 of Man (characters) suit. */
  _8m,
  /** The 9 of Man (characters) suit. */
  _9m,
  /** The 1 of Pin (circles) suit. */
  _1p,
  /** The 2 of Pin (circles) suit. */
  _2p,
  /** The 3 of Pin (circles) suit. */
  _3p,
  /** The 4 of Pin (circles) suit. */
  _4p,
  /** The 5 of Pin (circles) suit. */
  _5p,
  /** The 6 of Pin (circles) suit. */
  _6p,
  /** The 7 of Pin (circles) suit. */
  _7p,
  /** The 8 of Pin (circles) suit. */
  _8p,
  /** The 9 of Pin (circles) suit. */
  _9p,
  /** The 1 of Sou (bamboos) suit. */
  _1s,
  /** The 2 of Sou (bamboos) suit. */
  _2s,
  /** The 3 of Sou (bamboos) suit. */
  _3s,
  /** The 4 of Sou (bamboos) suit. */
  _4s,
  /** The 5 of Sou (bamboos) suit. */
  _5s,
  /** The 6 of Sou (bamboos) suit. */
  _6s,
  /** The 7 of Sou (bamboos) suit. */
  _7s,
  /** The 8 of Sou (bamboos) suit. */
  _8s,
  /** The 9 of Sou (bamboos) suit. */
  _9s,
  /** The 1st honor tile (east wind). */
  _1z,
  /** The 2nd honor tile (south wind). */
  _2z,
  /** The 3rd honor tile (west wind). */
  _3z,
  /** The 4th honor tile (north wind). */
  _4z,
  /** The 5th honor tile (white dragon). */
  _5z,
  /** The 6th honor tile (green dragon). */
  _6z,
  /** The 7th honor tile (red dragon). */
  _7z;

  private static final BaseTile[] vals = values();

  /** The east wind tile. */
  public static final BaseTile windTon = _1z;
  /** The south wind tile. */
  public static final BaseTile windNan = _2z;
  /** The west wind tile. */
  public static final BaseTile windShaa = _3z;
  /** The north wind tile. */
  public static final BaseTile windPei = _4z;

  /**
   * Returns the next tile in the enumeration.
   *
   * @return the next base tile
   */
  public BaseTile getNext() {
    return vals[(this.ordinal() + 1) % vals.length];
  }

  /**
   * Returns the previous tile in the enumeration.
   *
   * @return the previous base tile
   */
  public BaseTile getPrev() {
    return vals[(this.ordinal() + 1) % vals.length];
  }

  /**
   * Localization object for translating tile identifiers to localized strings.
   */
  private static Localization<BaseTile> localization = new Localization<>(BaseTile::valueOf);

  static {
    localization.initializeTranslator("/localization/tiles_l_english.yaml");
  }

  /**
   * Returns the localized string representation of this base tile.
   *
   * @return the localized name of the tile
   */
  public String toText() {
    return localization.toText(this);
  }

  /**
   * Returns the basic string representation of this base tile.
   *
   * @return the basic name of the tile
   */
  @Override
  public String toString() {
    return this.name().substring(1);
  }
}