package unit.use_case;

import data_access.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.Tile;
import mahjong.BaseTile;
import usecase.clear_tiles.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearTilesInteractorTest {
    final String name = "player1";
    final BaseTile tileId = BaseTile._1m;
    private final ClearTilesDataAccessInterface DAO = new InMemoryUniversalDataAccessObject();
    private final ClearTilesInputBoundary interactor = new ClearTilesInteractor(DAO, new dummyClearTilesOTB(name));

    @Test
    void SuccessTest() {
        List<Tile> hand = new ArrayList<Tile>();
        final Tile newTile = new Tile(tileId, false, false, false);
        for (int i = 0; i < 10; i++) {
            hand.add(newTile);
        }
        DAO.savePlayer(new Player(name, 0, hand));
        interactor.execute(new ClearTilesInputData(name));
        assertEquals(0, DAO.getPlayer(name).getHand().size());
    }

    @Test
    void FailTest() {
        // shouldn't happen.
    }
}

class dummyClearTilesOTB implements ClearTilesOutputBoundary {
    private final String expected;

    dummyClearTilesOTB(String expected) {
        this.expected = expected;
    }

    @Override
    public void prepareSuccessView(ClearTilesOutputData outputData) {
        assertEquals(expected, outputData.getPlayerName());
    }

    @Override
    public void prepareFailView(String errorMessage) {
        throw new ExpectedFail();
    }
}
