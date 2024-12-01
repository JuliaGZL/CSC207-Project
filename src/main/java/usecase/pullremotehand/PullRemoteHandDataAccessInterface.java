package usecase.pullremotehand;

import entity.Tile;
import java.util.List;
import usecase.addtile.AddTileDataAccessInterface;

/**
 * DAI for the pull hand from discord use case.
 */
public interface PullRemoteHandDataAccessInterface extends AddTileDataAccessInterface {
  /**
   * Retrieves the hand from Discord.
   *
   * @return a list of tiles representing the hand from Discord.
   */
  List<Tile> getHandFromDiscord();
}
