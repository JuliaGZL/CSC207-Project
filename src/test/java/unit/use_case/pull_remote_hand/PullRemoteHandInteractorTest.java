package unit.use_case.pull_remote_hand;

import data_access.InMemoryUniversalDataAccessObject;
import entity.PlayerFactory;
import entity.Tile;
import mahjong.BaseTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecase.pull_remote_hand.*;

import java.util.List;

public class PullRemoteHandInteractorTest {
    @Test
    void testUsual() {
        final PlayerFactory factory = new PlayerFactory();
        final String name = "new_player";
        final MockDAO DAO = new MockDAO();
        final MockOTB presenter = new MockOTB();
        final PullRemoteHandInputBoundary interactor = new PullRemoteHandInteractor(DAO, presenter);
        final List<BaseTile> inputBase = List.of(new BaseTile[] { BaseTile._1m });
        final List<Tile> input = List.of(new Tile[] { new Tile(BaseTile._1m) });

        DAO.savePlayer(factory.createEmpty(name));
        DAO.setInput(input);
        interactor.execute(new PullRemoteHandInputData(name));

        Assertions.assertEquals(presenter.getHandBaseTile(), inputBase);
        Assertions.assertEquals(DAO.getPlayer(name).getHand(), input);
    }
}

class MockOTB implements PullRemoteHandOutputBoundary {
    private List<BaseTile> handBaseTile;

    public List<BaseTile> getHandBaseTile() {
        return handBaseTile;
    }

    @Override
    public void prepareSuccessView(PullRemoteHandOutputData outputData) {
        handBaseTile = outputData.getPlayerHand();
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }
}

class MockDAO extends InMemoryUniversalDataAccessObject implements PullRemoteHandDataAccessInterface {
    private List<Tile> input;

    public void setInput(List<Tile> input) {
        this.input = input;
    }

    @Override
    public List<Tile> getHandFromDiscord() {
        return input;
    }
}
