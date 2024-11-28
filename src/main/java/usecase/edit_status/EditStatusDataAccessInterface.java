package usecase.edit_status;

import entity.AddTileManager;
import entity.Player;

/**
 * The interface of the DAO for the Edit Status Use Case.
 */
public interface EditStatusDataAccessInterface {

    /**
     * Changes the type of tile to be added to the hand.
     * @param addTileManager the AddTileManager object that manages the adding of tiles
     * @param addTileType the new type of tile to be added
     */
    void changeAddTileType(AddTileManager addTileManager, String addTileType);

    /**
     * Updates the system to record this player's gameplay attributes.
     * @param player the player whose gameplay attributes are to be updated
     * @param attributes the new attributes of the player
     */
    void changeAttributes(Player player, Boolean[] attributes);

    /**
     * Checks if a player with the given name exists.
     * @param playerName the name to look for
     * @return true if a player with the given name exists; false otherwise
     */
    boolean existsByName(String playerName);

    /**
     * Saves the player.
     * @param player the player to save
     */
    void savePlayer(Player player);

    /**
     * Returns the player with the given name.
     * @param playerName the username to look up
     * @return the user with the given username
     */
    Player getPlayer(String playerName);
//
//    /**
//     * Updates the system to record this user's red dora states.
//     * @param player the player whose red dora states are to be updated
//     */
//    void editRedDora(Player player);
//
//    /**
//     * Updates the system to record this user's dora tiles.
//     * @param player the player whose dora tiles are to be updated
//     */
//    void editDora(Player player);
}
