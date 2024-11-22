package unit.use_case;

import data_access.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.Tile;
import mahjong.BaseTile;

import org.junit.jupiter.api.Test;
import use_case.remove_tile.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoveTileInteractorTest {
    final String name = "player1";
    final BaseTile tileId = BaseTile._1m;
    private final RemoveTileDataAccessInterface DAO = new InMemoryUniversalDataAccessObject();
    private final RemoveTileInputBoundary interactor = new RemoveTileInteractor(DAO, new dummyRemoveTileOTB(name));

    @Test
    void SuccessTest() {
        List<Tile> hand = new ArrayList<Tile>();
        final Tile newTile = new Tile(tileId, false, false, false);
        for (int i = 0; i < 10; i++) {
            hand.add(newTile);
        }
        DAO.savePlayer(new Player(name, 0, hand));
        interactor.execute(new RemoveTileInputData(tileId, name));
        assertEquals(9, DAO.getPlayer(name).getHand().size());
    }

    @Test
    void FailTest() {
        // shouldn't happen.
    }
}

class dummyRemoveTileOTB implements RemoveTileOutputBoundary {
    private final String expected;

    dummyRemoveTileOTB(String expected) {
        this.expected = expected;
    }

    @Override
    public void prepareSuccessView(RemoveTileOutputData outputData) {
        assertEquals(expected, outputData.getPlayerName());
    }

    @Override
    public void prepareFailView(String errorMessage) {
        throw new ExpectedFail();
    }
}
