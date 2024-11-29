package data_access.discord_bot;

import entity.Tile;
import usecase.pull_remote_hand.PullRemoteHandDataAccessInterface;

import java.util.List;

public class PullRemoteHandDataAccessObject implements PullRemoteHandDataAccessInterface {
    @Override
    public List<Tile> getHandFromDiscord() {
        DefaultBotBuilder chatbotBuilder = new DefaultBotBuilder();
        chatbotBuilder.activateBot();
        return FeedbackGenerator.getTiles(chatbotBuilder.getMessage());
    }
}
