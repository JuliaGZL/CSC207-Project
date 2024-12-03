package dataaccess.discordbot;

import dataaccess.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.Tile;
import usecase.pull_remote_hand.PullRemoteHandDataAccessInterface;

import java.util.List;

/**
 * DAO for the pull hand from discord use case.
 */
public class PullRemoteHandDataAccessObject implements
        PullRemoteHandDataAccessInterface {

    /**
     * In-memory data access object used for player data management.
     */
    private final InMemoryUniversalDataAccessObject universalDAO;

    /**
     * Constructor for PullRemoteHandDataAccessObject.
     *
     * @param universalDAO the in-memory data access object used for player
     *                     data operations
     */
    public PullRemoteHandDataAccessObject(
            final InMemoryUniversalDataAccessObject universalDAO) {
        this.universalDAO = universalDAO;
    }

    /**
     * Retrieves the hand from Discord by activating the bot and obtaining
     * the message from it.
     *
     * @return a list of tiles based on the Discord bot message
     */
    @Override
    public List<Tile> getHandFromDiscord() {
        DefaultBotBuilder chatbotBuilder = new DefaultBotBuilder();
        chatbotBuilder.activateBot();
        return FeedbackGenerator.getTiles(chatbotBuilder.getMessage());
    }

    /**
     * Checks if a player with the given name exists.
     *
     * @param playerName the name to look for
     * @return true if a player with the given name exists; false otherwise
     */
    @Override
    public boolean existsByName(final String playerName) {
        return universalDAO.existsByName(playerName);
    }

    /**
     * Saves the player.
     *
     * @param player the player to save
     */
    @Override
    public void savePlayer(final Player player) {
        universalDAO.savePlayer(player);
    }

    /**
     * Returns the player with the given name.
     *
     * @param playerName the username to look up
     * @return the user with the given username
     */
    @Override
    public Player getPlayer(final String playerName) {
        return universalDAO.getPlayer(playerName);
    }
}
