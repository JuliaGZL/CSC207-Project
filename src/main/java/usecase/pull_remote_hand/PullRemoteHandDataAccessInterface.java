package usecase.pull_remote_hand;

import entity.Tile;

import java.util.List;

/**
 * DAI for the pull hand from discord use case.
 */
public interface PullRemoteHandDataAccessInterface {
    List<Tile> getHandFromDiscord();
}
