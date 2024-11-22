package mahjong.mahjong;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import utils.Algorithm;

/**
 * A singleton class responsible for splitting tiles into completed sets in Mahjong.
 */
public class TileSplitter {

  /**
   * The singleton instance of {@code TileSplitter}.
   */
  private static TileSplitter instance = null;

  /**
   * Stores the current combination of completed tiles.
   */
  private CompletedTiles completedTiles;

  /**
   * Indicates whether a head (pair) has been found.
   */
  private boolean hasHead;

  // Singleton pattern

  /**
   * Private constructor to enforce the singleton pattern.
   */
  private TileSplitter() {
    reset();
  }

  /**
   * Returns the singleton instance of {@code TileSplitter}.
   *
   * @return the singleton instance.
   */
  public static TileSplitter getInstance() {
    if (instance == null) {
      instance = new TileSplitter();
    }
    return instance;
  }

  /**
   * Resets the state of the {@code TileSplitter}.
   */
  public void reset() {
    completedTiles = new CompletedTiles();
    hasHead = false;
  }

  /**
   * Recursively finds all possible combinations of completed tiles from the given list.
   *
   * @param tiles the list of tiles to process
   * @return a list of all possible {@code CompletedTiles} combinations
   */
  public List<CompletedTiles> getAllCompletedTiles(List<BaseTile> tiles) {
    if (tiles.isEmpty()) {
      // i hate pointer semantics...
      CompletedTiles ct = new CompletedTiles();
      TileGroup headGroup = new TileGroup(completedTiles.getHead().getType());
      headGroup.setTiles(new ArrayList<>(completedTiles.getHead().getTiles()));
      ct.setHead(headGroup);
      ct.getBody().addAll(completedTiles.getBody());
      return Collections.singletonList(ct);
    }
    List<CompletedTiles> ret = new ArrayList<>();
    List<CompletedTiles> allCompletedTiles;
    List<BaseTile> tmpTiles;
    boolean flag = false;

    for (int index = 0; index < tiles.size(); ++index) {
      if (index > 0 && tiles.get(index).equals(tiles.get(index - 1))) {
        continue;
      }

      BaseTile thisTile = tiles.get(index);
      // 1. Find Toitsu (Head)
      if (!hasHead) {
        if (Collections.frequency(tiles, thisTile) >= 2) {
          flag = true;
          tmpTiles = new ArrayList<>(tiles);
          TileGroup tmpGroup = new TileGroup(TileGroup.Type.TOITSU);

          // Set global state head and remove pair
          tmpGroup.setTiles(List.of(thisTile, thisTile));
          Algorithm.eraseN(tmpTiles, thisTile, 2);
          hasHead = true;
          completedTiles.setHead(tmpGroup);

          // Recursively find all completed tiles
          allCompletedTiles = getAllCompletedTiles(tmpTiles);
          ret.addAll(allCompletedTiles);

          // Restore state
          hasHead = false;
        }
      }

      // 2. Find Koutsu
      if (Collections.frequency(tiles, thisTile) >= 3) {
        flag = true;
        tmpTiles = new ArrayList<>(tiles);
        TileGroup tmpGroup = new TileGroup(TileGroup.Type.KOUTSU);

        // Set global state and remove triplet
        tmpGroup.setTiles(List.of(thisTile, thisTile, thisTile));
        Algorithm.eraseN(tmpTiles, thisTile, 3);
        completedTiles.getBody().add(tmpGroup);

        // Recursively find all completed tiles
        allCompletedTiles = getAllCompletedTiles(tmpTiles);
        ret.addAll(allCompletedTiles);

        // Restore state
        completedTiles.getBody().remove(completedTiles.getBody().size() - 1);
      }

      // 3. Find Shuntsu
      if (!Rule.isShuntsuBadHead(thisTile)
          && tiles.contains(thisTile.getNext())
          && tiles.contains(thisTile.getNext().getNext())) {
        flag = true;
        tmpTiles = new ArrayList<>(tiles);
        TileGroup tmpGroup = new TileGroup(TileGroup.Type.SHUNTSU);
        tmpGroup.setTiles(List.of(thisTile, thisTile.getNext(), thisTile.getNext().getNext()));
        Algorithm.eraseN(tmpTiles, thisTile, 1);
        Algorithm.eraseN(tmpTiles, thisTile.getNext(), 1);
        Algorithm.eraseN(tmpTiles, thisTile.getNext().getNext(), 1);
        completedTiles.getBody().add(tmpGroup);

        // Recursively find all completed tiles
        allCompletedTiles = getAllCompletedTiles(tmpTiles);
        ret.addAll(allCompletedTiles);

        // Restore state
        completedTiles.getBody().remove(completedTiles.getBody().size() - 1);
      }

      if (!flag) {
        return Collections.emptyList();
      }
    }

    return ret;
  }



}
