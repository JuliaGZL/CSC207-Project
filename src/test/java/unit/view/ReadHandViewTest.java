package unit.view;

import data_access.InMemoryUniversalDataAccessObject;
import entity.Player;
import entity.Tile;
import interface_adapter.ViewManagerModel;
import interface_adapter.read_hand.ReadHandController;
import interface_adapter.read_hand.ReadHandPresenter;
import interface_adapter.read_hand.ReadHandViewModel;
import org.junit.jupiter.api.BeforeEach;
import usecase.read_hand.ReadHandDataAccessInterface;
import usecase.read_hand.ReadHandInputBoundary;
import usecase.read_hand.ReadHandInteractor;
import usecase.read_hand.ReadHandOutputBoundary;
import view.ReadHandView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ReadHandViewTest {
    ReadHandViewModel readHandViewModel;
    ViewManagerModel viewManagerModel;
    ReadHandView readHandView;

    @BeforeEach
    void setUp() {

    }

    public static void main(String[] args) {
        // Setting up the CLEAN Architecture components
        ReadHandDataAccessInterface DAO = new InMemoryUniversalDataAccessObject();

        ReadHandViewModel readHandViewModel = new ReadHandViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ReadHandOutputBoundary presenter = new ReadHandPresenter(readHandViewModel, viewManagerModel);
        ReadHandInputBoundary interactor = new ReadHandInteractor(DAO, presenter);
        ReadHandController readHandController = new ReadHandController(interactor);

        ReadHandView readHandView = new ReadHandView(readHandViewModel);
        readHandView.setReadHandController(readHandController);

        // Create dummy player
        List<Tile> hand = new ArrayList<>();
        Player player = new Player("default", 100, hand);
        Boolean[] attributes = {true, false, false, false, false, false, false, false, false};
        player.setAttributes(attributes);
        DAO.savePlayer(player);

        // Create frame to test this button
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(readHandView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
