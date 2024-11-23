package unit.use_case;

import data_access.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.Tile;
import mahjong.BaseTile;
import usecase.add_tile.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddTileInteractorTest {
    final String name = "player1";
    final BaseTile tileId = BaseTile._1m;
    private final AddTileDataAccessInterface DAO = new InMemoryUniversalDataAccessObject();
    private final AddTileInputBoundary interactor = new AddTileInteractor(DAO, new dummyAddTileOTB(name));

    @Test
    void SuccessTest() {
        List<Tile> hand = new ArrayList<Tile>();
        DAO.savePlayer(new Player(name, 0, hand));
        interactor.execute(new AddTileInputData(tileId, name));
        assertEquals(tileId, DAO.getPlayer(name).getHand().get(0).getTile());
    }

    @Test
    void FailTest() {
        List<Tile> hand = new ArrayList<Tile>();
        for (int i = 0; i < 14; i++) {
            hand.add(new Tile(tileId, false, false, false));
        }
        DAO.savePlayer(new Player(name, 0, hand));
        assertThrows(ExpectedFail.class, () -> {interactor.execute(new AddTileInputData(tileId, name));});
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
