package use_case.add_tile;

import entity.Player;

/**
 * DAI for the add tile use case.
 */
public interface AddTileDataAccessInterface {
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
    void save(Player player);

    /**
     * Returns the player with the given name.
     * @param playerName the username to look up
     * @return the user with the given username
     */
    Player get(String playerName);
}
