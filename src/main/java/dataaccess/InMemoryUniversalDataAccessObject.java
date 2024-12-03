package dataaccess;

import java.util.HashMap;
import java.util.Map;

import entity.Player;
import usecase.add_tile.AddTileDataAccessInterface;
import usecase.change_player.ChangePlayerDataAccessInterface;
import usecase.clear_tiles.ClearTilesDataAccessInterface;
import usecase.edit_status.EditStatusDataAccessInterface;
import usecase.hu_solver.HuSolverDataAccessInterface;
import usecase.read_hand.ReadHandDataAccessInterface;
import usecase.remove_tile.RemoveTileDataAccessInterface;
import usecase.update_enabled_tiles.UpdateEnabledTileDataAccessInterface;

/**
 * Universal player data storage object for our project.
 *
 * This class manages in-memory storage of player data and supports
 * various data access interfaces for adding, removing, and updating
 * tile data, as well as changing player data and handling status
 * updates.
 */
public class InMemoryUniversalDataAccessObject implements
        AddTileDataAccessInterface,
        RemoveTileDataAccessInterface,
        ClearTilesDataAccessInterface,
        UpdateEnabledTileDataAccessInterface,
        ChangePlayerDataAccessInterface,
        EditStatusDataAccessInterface,
        HuSolverDataAccessInterface,
        ReadHandDataAccessInterface {

    /**
     * A map holding player information, indexed by player names.
     */
    private final Map<String, Player> players = new HashMap<String, Player>();

    /**
     * Default constructor for InMemoryUniversalDataAccessObject.
     *
     * Initializes the data storage for players.
     */
    public InMemoryUniversalDataAccessObject() {

    }

    /**
     * Return whether a player exists.
     * @param name name of the player
     * @return true if the player exists
     */
    @Override
    public boolean existsByName(final String name) {
        return players.containsKey(name);
    }

    /**
     * Get a player by name.
     * @param name name of the player.
     * @return the corresponding Player object.
     */
    @Override
    public Player getPlayer(final String name) {
        return players.get(name);
    }

    /**
     * Save a player. Append it into the storage if it wasn't already in,
     * otherwise update it.
     * @param player the player to insert or update.
     */
    @Override
    public void savePlayer(final Player player) {
        players.put(player.getName(), player);
    }

    /**
     * Updates the system to record this player's gameplay attributes.
     * @param player the player whose gameplay attributes are to be updated
     * @param newAttributes a boolean array of length 9
     *                      that are the new attributes to be set
     */
    @Override
    public void changeAttributes(final Player player,
                                 final Boolean[] newAttributes) {
        if (newAttributes.length != 9) {
            throw new IllegalArgumentException(
                    "The length of the new attributes must be 9.");
        }
        player.setAttributes(newAttributes);
    }
}
