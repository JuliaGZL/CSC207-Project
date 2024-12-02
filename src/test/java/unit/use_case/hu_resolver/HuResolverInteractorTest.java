package unit.use_case.hu_resolver;

import data_access.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.PlayerFactory;
import entity.Tile;
import mahjong.BaseTile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import usecase.hu_solver.HuSolveOutputBoundary;
import usecase.hu_solver.HuSolverInputData;
import usecase.hu_solver.HuSolverInteractor;
import usecase.hu_solver.HuSolverOutputData;

import java.util.ArrayList;
import java.util.List;

public class HuResolverInteractorTest {
    private final PlayerFactory factory = new PlayerFactory();
    private final String playerName = "player";

    @Test
    void SuccessTest() {
        InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();
        Player player = factory.createEmpty(playerName);
        BaseTile[] handTiles = {
                BaseTile._1m, BaseTile._1m, BaseTile._1m,
                BaseTile._2m, BaseTile._2m, BaseTile._2m,
                BaseTile._3m, BaseTile._3m, BaseTile._3m,
                BaseTile._4m, BaseTile._4m, BaseTile._4m,
                BaseTile._5m, BaseTile._5m
        };
        List<Tile> hand = new ArrayList<>();
        for (BaseTile baseTile : handTiles) {
            hand.add(new Tile(baseTile));
        }
        player.setHand(hand);
        DAO.savePlayer(player);
        MockOTB mockOTB = new MockOTB();
        HuSolverInteractor interactor = new HuSolverInteractor(DAO, mockOTB);
        interactor.execute(new HuSolverInputData(playerName));
        Assertions.assertEquals(mockOTB.getScore(), 64000);

        // other round wind types
        player.setRoundWind("West");
        DAO.savePlayer(player);
        interactor.execute(new HuSolverInputData(playerName));
        Assertions.assertEquals(mockOTB.getScore(), 64000);
        player.setRoundWind("South");
        DAO.savePlayer(player);
        interactor.execute(new HuSolverInputData(playerName));
        Assertions.assertEquals(mockOTB.getScore(), 64000);
        player.setRoundWind("North");
        DAO.savePlayer(player);
        interactor.execute(new HuSolverInputData(playerName));
        Assertions.assertEquals(mockOTB.getScore(), 64000);
    }

    @Test
    void FailTestHandFull() {
        InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();
        Player player = factory.createEmpty(playerName);
        BaseTile[] handTiles = {
                BaseTile._1s, BaseTile._1m, BaseTile._1m,
                BaseTile._2m, BaseTile._2m, BaseTile._2m,
                BaseTile._3m, BaseTile._3m, BaseTile._3m,
                BaseTile._4m, BaseTile._4m, BaseTile._4m,
                BaseTile._5m, BaseTile._5m
        };
        List<Tile> hand = new ArrayList<>();
        for (BaseTile baseTile : handTiles) {
            hand.add(new Tile(baseTile));
        }
        player.setHand(hand);
        DAO.savePlayer(player);
        MockOTB mockOTB = new MockOTB();
        HuSolverInteractor interactor = new HuSolverInteractor(DAO, mockOTB);
        interactor.execute(new HuSolverInputData(playerName));
        Assertions.assertTrue(mockOTB.isFailed());
    }

    @Test
    void FailTestHandNotFull() {
        InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();
        Player player = factory.createEmpty(playerName);
        BaseTile[] handTiles = {
                BaseTile._1m
        };
        List<Tile> hand = new ArrayList<>();
        for (BaseTile baseTile : handTiles) {
            hand.add(new Tile(baseTile));
        }
        player.setHand(hand);
        DAO.savePlayer(player);
        MockOTB mockOTB = new MockOTB();
        HuSolverInteractor interactor = new HuSolverInteractor(DAO, mockOTB);
        interactor.execute(new HuSolverInputData(playerName));
        Assertions.assertTrue(mockOTB.isFailed());
    }
}

class MockOTB implements HuSolveOutputBoundary {
    private int score = 0;
    private boolean failed = false;

    /**
     * Prepares the success view for the use case.
     *
     * @param outputData the output data
     */
    @Override
    public void prepareSuccessView(HuSolverOutputData outputData) {
        score = outputData.getScore();
    }

    /**
     * Prepares the failure view for the use case.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        failed = true;
    }

    public int getScore() {
        return score;
    }

    public boolean isFailed() {
        return failed;
    }
}
