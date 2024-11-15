package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.Player;
import use_case.add_tile.AddTileDataAccessInterface;
import use_case.remove_tile.RemoveTileDataAccessInterface;

/**
 * Universal player data storage object for our project.
 */
public class InMemoryUniversalDataAccessObject implements AddTileDataAccessInterface,
        RemoveTileDataAccessInterface {
    private final Map<String, Player> players = new HashMap<String, Player>();

    public InMemoryUniversalDataAccessObject() {

    }

    /**
     * Return whether a player exists.
     * @param name name of the player
     * @return true if the player exists
     */
    @Override
    public boolean existsByName(String name) {
        return players.containsKey(name);
    }

    /**
     * Get a player by name.
     * @param name name of the player.
     * @return the corresponding Player object.
     */
    @Override
    public Player getPlayer(String name) {
        return players.get(name);
    }

    /**
     * Save a player. Append it into the storage if it wasn't already in, otherwise update it.
     * @param player the player to insert or update.
     */
    @Override
    public void savePlayer(Player player) {
        players.put(player.getName(), player);
    }
}
