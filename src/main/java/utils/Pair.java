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
