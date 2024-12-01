package unit.use_case.clear_tiles;

import data_access.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.PlayerFactory;
import entity.Tile;
import mahjong.BaseTile;
import org.junit.jupiter.api.Assertions;
import usecase.clear_tiles.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClearTilesInteractorTest {
    private final String name = "player1";
    private final BaseTile tileId = BaseTile._1m;
    private final PlayerFactory factory = new PlayerFactory();
    private final ClearTilesDataAccessInterface DAO = new InMemoryUniversalDataAccessObject();

    @Test
    void SuccessTestHand() {
        ClearTilesInputBoundary interactor = new ClearTilesInteractor(
                DAO, new dummyClearTilesOTB(name), ClearTilesInteractor.HAND);
        List<Tile> hand = new ArrayList<Tile>();
        final Tile newTile = new Tile(tileId, false, false, false);
        for (int i = 0; i < 10; i++) {
            hand.add(newTile);
        }
        Player player = factory.createEmpty(name);
        player.setHand(hand);
        DAO.savePlayer(player);
        interactor.execute(new ClearTilesInputData(name));
        assertEquals(0, DAO.getPlayer(name).getHand().size());
    }

    @Test
    void SuccessTestDora() {
        ClearTilesInputBoundary interactor = new ClearTilesInteractor(
                DAO, new dummyClearTilesOTB(name), ClearTilesInteractor.DORA);
        List<Tile> dora = new ArrayList<Tile>();
        final Tile newTile = new Tile(tileId, false, false, false);
        for (int i = 0; i < 10; i++) {
            dora.add(newTile);
        }
        Player player = factory.createEmpty(name);
        player.setDora(dora);
        DAO.savePlayer(player);
        interactor.execute(new ClearTilesInputData(name));
        assertEquals(0, DAO.getPlayer(name).getDora().size());
    }

    @Test
    void SuccessTestUradora() {
        ClearTilesInputBoundary interactor = new ClearTilesInteractor(
                DAO, new dummyClearTilesOTB(name), ClearTilesInteractor.URADORA);
        List<Tile> uradora = new ArrayList<Tile>();
        final Tile newTile = new Tile(tileId, false, false, false);
        for (int i = 0; i < 10; i++) {
            uradora.add(newTile);
        }
        Player player = factory.createEmpty(name);
        player.setUradora(uradora);
        DAO.savePlayer(player);
        interactor.execute(new ClearTilesInputData(name));
        assertEquals(0, DAO.getPlayer(name).getUradora().size());
    }

    @Test
    void FailTestPlayerNotExist() {
        ClearTilesInputBoundary interactorHand = new ClearTilesInteractor(
                DAO, new dummyClearTilesOTB(name), ClearTilesInteractor.HAND);
        Assertions.assertThrows(
                RuntimeException.class,
                () -> {
                    interactorHand.execute(new ClearTilesInputData("no_exist"));
                }
        );
    }

    @Test
    void FailTestInvalidTarget() {
        ClearTilesInputBoundary interactorInvalid = new ClearTilesInteractor(
                DAO, new dummyClearTilesOTB(name), 233);
        Player player = factory.createEmpty(name);
        DAO.savePlayer(player);
        Assertions.assertThrows(
                RuntimeException.class,
                () -> {
                    interactorInvalid.execute(new ClearTilesInputData(name));
                }
        );
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

class ExpectedFail extends RuntimeException {}