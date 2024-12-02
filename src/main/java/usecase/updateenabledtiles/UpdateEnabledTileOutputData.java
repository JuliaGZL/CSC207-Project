package usecase.updateenabledtiles;

import java.util.Set;
import mahjong.BaseTile;

/**
 * Output data structure for update enabled tile use case.
 */
public class UpdateEnabledTileOutputData {
  private final Set<BaseTile> enabledTiles;

  /**
   * Constructs an UpdateEnabledTileOutputData with the specified enabled tiles.
   *
   * @param enabledTiles the set of enabled tiles
   */
  public UpdateEnabledTileOutputData(Set<BaseTile> enabledTiles) {
    this.enabledTiles = enabledTiles;
  }

  /**
   * Returns the set of enabled tiles.
   *
   * @return the set of enabled tiles
   */
  public Set<BaseTile> getEnabledTiles() {
    return enabledTiles;
  }
}
