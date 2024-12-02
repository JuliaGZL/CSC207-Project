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

package utils;

/**
 * A generic pair of objects.
 *
 * @param <K> the type of the first element
 * @param <V> the type of the second element
 */
public class Pair<K, V> {

  private final K element0;
  private final V element1;

  /**
   * Creates a new pair with the given elements.
   *
   * @param element0 the first element
   * @param element1 the second element
   * @return a new pair containing the specified elements
   */
  public static <K, V> Pair<K, V> createPair(K element0, V element1) {
    return new Pair<K, V>(element0, element1);
  }

  /**
   * Constructs a pair with the specified elements.
   *
   * @param element0 the first element
   * @param element1 the second element
   */
  public Pair(K element0, V element1) {
    this.element0 = element0;
    this.element1 = element1;
  }

  /**
   * Returns the first element of this pair.
   *
   * @return the first element
   */
  public K getFst() {
    return element0;
  }

  /**
   * Returns the second element of this pair.
   *
   * @return the second element
   */
  public V getSnd() {
    return element1;
  }

}
