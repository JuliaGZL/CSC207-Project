package mahjong;

import java.util.List;

/**
 * Utility class for Mahjong-related operations.
 */
public class MahjongUtils {
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
   * Merges elements from one list into another.
   *
   * @param to   the list to merge elements into
   * @param from the list from which elements are taken
   * @param <T>  the type of elements in the lists
   */
  public static <T> void mergeInto(List<T> to, List<? extends T> from) {
    to.addAll(from);
  }
}
