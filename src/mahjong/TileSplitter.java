package mahjong;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class TileSplitter {

  private static TileSplitter instance = null;

  private CompletedTiles completedTiles;
  private boolean hasHead;

  // Singleton pattern
  private TileSplitter() {
    reset();
  }

  public static TileSplitter getInstance() {
    if (instance == null) {
      instance = new TileSplitter();
    }
    return instance;
  }

  public void reset() {
    completedTiles = new CompletedTiles();
    hasHead = false;
  }

}
