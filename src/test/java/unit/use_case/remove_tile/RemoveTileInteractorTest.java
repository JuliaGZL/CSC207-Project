package unit.use_case.remove_tile;

import data_access.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.PlayerFactory;
import entity.Tile;
import mahjong.BaseTile;
import org.junit.jupiter.api.Assertions;
import usecase.remove_tile.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RemoveTileInteractorTest {
    private final String name = "player1";
    private final BaseTile tileId = BaseTile._1m;
    private final PlayerFactory factory = new PlayerFactory();
    private final RemoveTileDataAccessInterface DAO = new InMemoryUniversalDataAccessObject();

    @Test
    void SuccessTestHand() {
        RemoveTileInputBoundary interactor = new RemoveTileInteractor(
                DAO, new dummyRemoveTileOTB(name), RemoveTileInteractor.HAND);
        List<Tile> hand = new ArrayList<Tile>();
        final Tile newTile = new Tile(tileId, false, false, false);
        for (int i = 0; i < 10; i++) {
            hand.add(newTile);
        }
        Player player = factory.createEmpty(name);
        player.setHand(hand);
        DAO.savePlayer(player);
        interactor.execute(new RemoveTileInputData(tileId, name));
        assertEquals(9, DAO.getPlayer(name).getHand().size());
    }

    @Test
    void SuccessTestDora() {
        RemoveTileInputBoundary interactor = new RemoveTileInteractor(
                DAO, new dummyRemoveTileOTB(name), RemoveTileInteractor.DORA);
        List<Tile> dora = new ArrayList<Tile>();
        final Tile newTile = new Tile(tileId, false, false, false);
        for (int i = 0; i < 3; i++) {
            dora.add(newTile);
        }
        Player player = factory.createEmpty(name);
        player.setDora(dora);
        DAO.savePlayer(player);
        interactor.execute(new RemoveTileInputData(tileId, name));
        assertEquals(2, DAO.getPlayer(name).getDora().size());
    }

    @Test
    void SuccessTestUradora() {
        RemoveTileInputBoundary interactor = new RemoveTileInteractor(
                DAO, new dummyRemoveTileOTB(name), RemoveTileInteractor.URADORA);
        List<Tile> uradora = new ArrayList<Tile>();
        final Tile newTile = new Tile(tileId, false, false, false);
        for (int i = 0; i < 3; i++) {
            uradora.add(newTile);
        }
        Player player = factory.createEmpty(name);
        player.setUradora(uradora);
        DAO.savePlayer(player);
        interactor.execute(new RemoveTileInputData(tileId, name));
        assertEquals(2, DAO.getPlayer(name).getUradora().size());
    }

    @Test
    void FailTestPlayerNotExist() {
        RemoveTileInputBoundary interactorHand = new RemoveTileInteractor(
                DAO, new dummyRemoveTileOTB(name), RemoveTileInteractor.HAND);
        Assertions.assertThrows(
                RuntimeException.class,
                () -> {
                    interactorHand.execute(new RemoveTileInputData(tileId, "no_exist"));
                });
    }

    @Test
    void FailTestInvalidTarget() {
        RemoveTileInputBoundary interactorInvalid = new RemoveTileInteractor(
                DAO, new dummyRemoveTileOTB(name), 233);
        Player player = factory.createEmpty(name);
        DAO.savePlayer(player);
        Assertions.assertThrows(
                RuntimeException.class,
                () -> {
                    interactorInvalid.execute(new RemoveTileInputData(tileId, name));
                });
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

class ExpectedFail extends RuntimeException {
}