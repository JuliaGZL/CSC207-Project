package unit.use_case.edit_status;

import data_access.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.Tile;
import interface_adapter.ViewManagerModel;
import interface_adapter.edit_status.EditStatusViewModel;
import interface_adapter.edit_tiles.SelectDoraViewModel;
import interface_adapter.edit_tiles.TileSelectorViewModel;
import mahjong.BaseTile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.edit_status.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for EditStatusInputBoundary.
 */
class EditStatusInteractorTest {
    EditStatusDataAccessInterface DAO;
    EditStatusOutputBoundary playerPresenter;

    EditStatusViewModel editStatusViewModel;
    SelectDoraViewModel selectDoraViewModel;
    TileSelectorViewModel tileSelectorViewModel;
    ViewManagerModel viewManagerModel;

    EditStatusInteractor interactor;

    @BeforeEach
    void setUp() {
        editStatusViewModel = new EditStatusViewModel();
        selectDoraViewModel = new SelectDoraViewModel();
        tileSelectorViewModel = new TileSelectorViewModel();
        viewManagerModel = new ViewManagerModel();

        DAO = new InMemoryUniversalDataAccessObject();
        playerPresenter = new EditStatusOutputBoundary() {

            @Override
            public void prepareSuccessView(EditStatusOutputData outputData) {
                System.out.println("Success");
            }

            @Override
            public void prepareSuccessView(String updateName, EditStatusOutputData outputData) {
                System.out.println("Success: " + updateName);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                System.out.println("Fail: " + errorMessage);
            }

            @Override
            public void switchToSelectDoraView(EditStatusOutputData outputData) {
                System.out.println("Switch to select dora view");
            }

            @Override
            public void switchToSelectForHand() {
                System.out.println("Switch to select for hand");
            }
        };

        interactor = new EditStatusInteractor(DAO, playerPresenter);
    }

    @AfterEach
    void tearDown() {
        DAO = null;
        playerPresenter = null;
        editStatusViewModel = null;
        selectDoraViewModel = null;
        tileSelectorViewModel = null;
        viewManagerModel = null;
        interactor = null;
    }

    /**
     * Test for execute method (success) with one input editStatusInputData.
     */
    @Test
    void testExecuteSuccessOneVar() {
        Boolean[] mockAttributes = {false, true, false, false, false, false, false, false, true, false};
        EditStatusInputData editStatusInputData = new EditStatusInputData(mockAttributes, 3,
                "East", "South", "Ron", "default");

        // Assume a mock player named "default" is created and saved in DAO
        List<Tile> hand = new ArrayList<>();
        hand.add(new Tile(BaseTile._1m, false, false, false));
        Player player = new Player("default", 100, hand);
        Boolean[] attributes = {true, false, false, false, false, false, false, false, false};
        player.setAttributes(attributes);
        DAO.savePlayer(player);

        interactor.execute(editStatusInputData);

        // Get the player and verify if all data have been updated
        Player updatedPlayer = DAO.getPlayer("default");
        assertEquals(mockAttributes, updatedPlayer.getAttributes());
        assertEquals(3, updatedPlayer.getNumAkaDora());
        assertEquals("East", updatedPlayer.getSeatWind());
        assertEquals("South", updatedPlayer.getRoundWind());
        assertEquals("Ron", updatedPlayer.getWinType());
    }

    /**
     * Test for execute method (success) with two inputs updateName and editStatusInputData.
     */
    @Test
    void testExecuteSuccessTwoVar() {
        Boolean[] mockAttributes = {false, true, false, false, false, false, false, false, true, false};
        EditStatusInputData editStatusInputData = new EditStatusInputData(mockAttributes, 3,
                "East", "South", "Ron", "default");

        // Assume a mock player named "default" is created and saved in DAO
        List<Tile> hand = new ArrayList<>();
        hand.add(new Tile(BaseTile._1m, false, false, false));
        Player player = new Player("default", 100, hand);
        Boolean[] attributes = {true, false, false, false, false, false, false, false, false};
        player.setAttributes(attributes);
        DAO.savePlayer(player);

        interactor.execute("editStatus", editStatusInputData);

        // Get the player and verify if all data have been updated
        Player updatedPlayer = DAO.getPlayer("default");
        assertEquals(mockAttributes, updatedPlayer.getAttributes());
        assertEquals(3, updatedPlayer.getNumAkaDora());
        assertEquals("East", updatedPlayer.getSeatWind());
        assertEquals("South", updatedPlayer.getRoundWind());
        assertEquals("Ron", updatedPlayer.getWinType());
    }

    /**
     * Test for execute method (failure) with one input editStatusInputData.
     */
    @Test
    void testExecuteFailOneVar() {
        Boolean[] mockAttributes = {false, true, false, false, false, false, false, false, true, false};
        EditStatusInputData editStatusInputData = new EditStatusInputData(mockAttributes, 3,
                "East", "South", "Ron", "dummy");

        // Try executing without player named "dummy"
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            interactor.execute(editStatusInputData);
        });

        assertEquals("Player name not found!", exception.getMessage());
    }

    /**
     * Test for execute method (failure) with two inputs updateName and editStatusInputData.
     */
    @Test
    void testExecuteFailTwoVar() {
        Boolean[] mockAttributes = {false, true, false, false, false, false, false, false, true, false};
        EditStatusInputData editStatusInputData = new EditStatusInputData(mockAttributes, 3,
                "East", "South", "Ron", "dummy");

        // Try executing without player named "dummy"
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            interactor.execute("editStatus", editStatusInputData);
        });

        assertEquals("Player name not found!", exception.getMessage());

    }

    /**
     * Test for switchToSelectDoraView method.
     */
    @Test
    void testSwitchToSelectDoraView() {
        Boolean[] mockAttributes = {false, true, false, false, false, false, false, false, true, false};
        EditStatusInputData editStatusInputData = new EditStatusInputData(mockAttributes, 3,
                "East", "South", "Ron", "dummy");
        interactor.switchToSelectDoraView(editStatusInputData);

        // Currently, this method does not have implementation, so the test to it should always be true.
        assertNotNull(interactor);
    }
}


