package unit.use_case.add_tile;

import data_access.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.PlayerFactory;
import entity.Tile;
import mahjong.BaseTile;
import usecase.add_tile.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddTileInteractorTest {
    private final String name = "player1";
    private final BaseTile tileId = BaseTile._1m;
    private final PlayerFactory factory = new PlayerFactory();
    private final AddTileDataAccessInterface DAO = new InMemoryUniversalDataAccessObject();

    @Test
    void SuccessTestHand() {
        AddTileInputBoundary interactorHand = new AddTileInteractor(
                DAO, new dummyAddTileOTB(name), AddTileInteractor.HAND);
        List<Tile> hand = new ArrayList<Tile>();
        Player player = factory.createEmpty(name);
        player.setHand(hand);
        DAO.savePlayer(player);
        interactorHand.execute(new AddTileInputData(tileId, name));
        assertEquals(tileId, DAO.getPlayer(name).getHand().get(0).getTile());
    }

    @Test
    void SuccessTestDora() {
        AddTileInputBoundary interactorDora = new AddTileInteractor(
                DAO, new dummyAddTileOTB(name), AddTileInteractor.DORA);
        List<Tile> dora = new ArrayList<Tile>();
        Player player = factory.createEmpty(name);
        player.setDora(dora);
        DAO.savePlayer(player);
        interactorDora.execute(new AddTileInputData(tileId, name));
        assertEquals(tileId, DAO.getPlayer(name).getDora().get(0).getTile());
    }

    @Test
    void SuccessTestUradora() {
        AddTileInputBoundary interactorUradora = new AddTileInteractor(
                DAO, new dummyAddTileOTB(name), AddTileInteractor.URADORA);
        List<Tile> uradora = new ArrayList<Tile>();
        Player player = factory.createEmpty(name);
        player.setUradora(uradora);
        DAO.savePlayer(player);
        interactorUradora.execute(new AddTileInputData(tileId, name));
        assertEquals(tileId, DAO.getPlayer(name).getUradora().get(0).getTile());
    }

    @Test
    void FailTest() {
        // shouldn't happen
    }
}

class dummyAddTileOTB implements AddTileOutputBoundary {
    private final String expected;

    dummyAddTileOTB(String expected) {
        this.expected = expected;
    }

    @Override
    public void prepareSuccessView(AddTileOutputData outputData) {
        assertEquals(expected, outputData.getPlayerName());
    }

    @Override
    public void prepareFailView(String errorMessage) {
        throw new ExpectedFail();
    }
}

class ExpectedFail extends RuntimeException {}
