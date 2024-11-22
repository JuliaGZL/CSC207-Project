package utils;

import java.util.List;

import mahjong.mahjong.BaseTile;

/**
 * Utility class for Mahjong-related operations.
 */
public class Algorithm {
  /**
   * Determines if two lists contain the same elements in the same order.
   *
   * @param a   the first list
   * @param b   the second list
   * @param <T> the type of elements in the lists
   * @return {@code true} if both lists have the same size and corresponding
   *         elements are equal, {@code false} otherwise
   */
  public static <T> boolean isSameContainer(List<T> a, List<T> b) {
    if (a.size() != b.size()) {
      return false;
    }
    for (int i = 0; i < a.size(); i++) {
      if (a.get(i) != b.get(i)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Checks if an array contains a specific element.
   *
   * @param a   the array to search
   * @param b   the element to find
   * @param <T> the type of elements in the array
   * @return {@code true} if the array contains the element, {@code false}
   *         otherwise
   */
  public static <T> boolean arrayContains(T[] a, T b) {
    for (T element : a) {
      if (element == b) {
        return true;
      }
    }
    return false;
  }

  /**
   * Merges all elements from the source list into the destination list.
   *
   * @param <T> the type of elements in the lists
   * @param to the destination list where elements will be added
   * @param from the source list from which elements will be taken
   */
  public static <T> void mergeInto(List<T> to, List<? extends T> from) {
    to.addAll(from);
  }

  /**
   * Removes a specified number of occurrences of a element from the list.
   *
   * @param <T> the type of elements in the lists
   * @param elementList the list of elements
   * @param element the element to remove
   * @param n the number of times to remove the tile
   */
  public static <T> void eraseN(List<T> elementList, T element, int n) {
    for (int i = 0; i < n; i++) {
      elementList.remove(element);
    }
  }
}
