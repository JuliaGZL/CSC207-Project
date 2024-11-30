package data_access.discord_bot;

import data_access.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.Tile;
import usecase.pull_remote_hand.PullRemoteHandDataAccessInterface;

import java.util.List;

/**
 * DAO for the pull hand from discord use case.
 */
public class PullRemoteHandDataAccessObject implements PullRemoteHandDataAccessInterface {
    private final InMemoryUniversalDataAccessObject universalDAO;

    public PullRemoteHandDataAccessObject(InMemoryUniversalDataAccessObject universalDAO) {
        this.universalDAO = universalDAO;
    }

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
    public boolean existsByName(String playerName) {
        return universalDAO.existsByName(playerName);
    }

    /**
     * Saves the player.
     *
     * @param player the player to save
     */
    @Override
    public void savePlayer(Player player) {
        universalDAO.savePlayer(player);
    }

    /**
     * Returns the player with the given name.
     *
     * @param playerName the username to look up
     * @return the user with the given username
     */
    @Override
    public Player getPlayer(String playerName) {
        return universalDAO.getPlayer(playerName);
    }
}
