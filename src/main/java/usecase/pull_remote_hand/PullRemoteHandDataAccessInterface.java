package usecase.pull_remote_hand;

import entity.Tile;
import usecase.add_tile.AddTileDataAccessInterface;

import java.util.List;

/**
 * DAI for the pull hand from discord use case.
 */
public interface PullRemoteHandDataAccessInterface extends AddTileDataAccessInterface {
    List<Tile> getHandFromDiscord();
}
