/*
 Copyright (c) 2024 YAizhou, li-tianchu, JuliaGZL, TurkeyBilly

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package dataaccess.discordbot;

import dataaccess.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.Tile;
import java.util.List;
import usecase.pullremotehand.PullRemoteHandDataAccessInterface;

/**
 * DAO for the pull hand from discord use case.
 */
public class PullRemoteHandDataAccessObject implements PullRemoteHandDataAccessInterface {
  private final InMemoryUniversalDataAccessObject universalDAO;

  /**
   * Constructs a new PullRemoteHandDataAccessObject with the given universal DAO.
   *
   * @param universalDAO the universal data access object
   */
  public PullRemoteHandDataAccessObject(InMemoryUniversalDataAccessObject universalDAO) {
    this.universalDAO = universalDAO;
  }

  /**
   * Retrieves the hand from Discord.
   *
   * @return a list of tiles representing the hand from Discord
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
