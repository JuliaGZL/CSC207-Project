package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.AddTileManager;
import entity.Player;
import usecase.add_tile.AddTileDataAccessInterface;
import usecase.change_player.ChangePlayerDataAccessInterface;
import usecase.clear_tiles.ClearTilesDataAccessInterface;
import usecase.edit_status.EditStatusDataAccessInterface;
import usecase.hu_solver.HuSolverDataAccessInterface;
import usecase.remove_tile.RemoveTileDataAccessInterface;
import usecase.update_enabled_tiles.UpdateEnabledTileDataAccessInterface;

/**
 * Universal player data storage object for our project.
 */
public class InMemoryUniversalDataAccessObject implements AddTileDataAccessInterface,
        RemoveTileDataAccessInterface,
        ClearTilesDataAccessInterface,
        UpdateEnabledTileDataAccessInterface,
        ChangePlayerDataAccessInterface,
        EditStatusDataAccessInterface,
        HuSolverDataAccessInterface {
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


    /**
     * Changes the type of tile to be added to the hand.
     * @param addTileManager the AddTileManager object that manages the adding of tiles
     */
    @Override
    public void changeAddTileType(AddTileManager addTileManager, String newAddTileType) {
        addTileManager.setAddTileType(newAddTileType);
    }

    /**
     * Updates the system to record this player's gameplay attributes.
     * @param player the player whose gameplay attributes are to be updated
     * @param newAttributes a boolean array of length 9 that are the new attributes to be set
     */
    @Override
    public void changeAttributes(Player player, Boolean[] newAttributes) {
        if (newAttributes.length != 9) {
            throw new IllegalArgumentException("The length of the new attributes must be 9.");
        }
        player.setAttributes(newAttributes);
    }
}
