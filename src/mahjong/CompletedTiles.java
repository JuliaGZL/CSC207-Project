package mahjong;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a completed set of tiles in Mahjong, consisting of a head and body
 * groups.
 */
class CompletedTiles implements Comparable<CompletedTiles> {

  /**
   * The head pair of the completed tiles.
   */
  private TileGroup head;

  /**
   * The body groups (sequence or triplets) of the completed tiles.
   */
  private List<TileGroup> body;

  /**
   * Constructs an empty {@code CompletedTiles} object.
   */
  public CompletedTiles() {
    this.body = new ArrayList<>();
  }

  /**
   * Sets the head of the completed tiles.
   *
   * @param head the {@code TileGroup} representing the head pair
   */
  public void setHead(TileGroup head) {
    this.head = head;
  }

  /**
   * Adds a body group to the completed tiles and sorts the body groups.
   *
   * @param group the {@code TileGroup} to add to the body
   */
  public void addBodyGroup(TileGroup group) {
    this.body.add(group);
    this.body.sort(TileGroup.comparator());
  }

  /**
   * Retrieves the head of the completed tiles.
   *
   * @return the head {@code TileGroup}
   */
  public TileGroup getHead() {
    return head;
  }

  /**
   * Retrieves the body groups of the completed tiles.
   *
   * @return the list of body {@code TileGroup}s
   */
  public List<TileGroup> getBody() {
    return body;
  }

  /**
   * Sorts the tiles within each TileGroup in the body and then sorts the body
   * itself.
   * This method first calls the sortTiles method on each TileGroup in the body to
   * sort the tiles within each group.
   * After that, it sorts the body list itself.
   */
  public void sortBody() {
    for (TileGroup g : body) {
      g.sortTiles();
    }
    body.sort(null);
  }

  /**
   * This method processes a list of BaseTile objects to find all possible
   * completed tile sets.
   * A completed tile set is a valid Mahjong hand that can be formed from the
   * given tiles.
   * 
   * @param tiles the list of BaseTile objects to be processed. The list must
   *              contain a number of tiles
   *              that is congruent to 2 modulo 3 (i.e., the size of the list must
   *              be of the form 3n + 2).
   * @return a list of unique CompletedTiles objects representing all possible
   *         completed tile sets.
   * @throws RuntimeException if the number of tiles is not congruent to 2 modulo
   *                          3.
   */
  public static List<CompletedTiles> getCompletedTiles(List<BaseTile> tiles) {
    if (tiles.size() % 3 != 2) {
      throw new RuntimeException("Not Enough Tiles");
    }
    tiles.sort(null);
    TileSplitter inst = TileSplitter.getInstance();
    inst.reset();
    List<CompletedTiles> completedTiles = inst.getAllCompletedTiles(tiles);
    for (CompletedTiles completed : completedTiles) {
      completed.sortBody();
    }
    completedTiles.sort(null);
    List<CompletedTiles> uniqueCompletedTiles = new ArrayList<>();
    for (CompletedTiles completed : completedTiles) {
      if (!uniqueCompletedTiles.contains(completed)) {
        uniqueCompletedTiles.add(completed);
      }
    }
    completedTiles = uniqueCompletedTiles;
    return completedTiles;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(head.toString() + " ");
    for (TileGroup group : body) {
      sb.append(group.toString()).append(" ");
    }
    return sb.toString().trim();
  }

  @Override
  public int compareTo(CompletedTiles other) {
    if (this.head.compareTo(other.head) != 0) {
      return this.head.compareTo(other.head);
    }
    if (this.body.size() > other.body.size()) {
      return 1;
    }
    if (this.body.size() < other.body.size()) {
      return -1;
    }

    for (int i = 0; i < this.body.size(); i++) {
      if (this.body.get(i).compareTo(other.body.get(i)) != 0) {
        return this.body.get(i).compareTo(other.body.get(i));
      }
    }
    return 0;
  }
}