package unit.view;

import data_access.InMemoryUniversalDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.edit_status.EditStatusController;
import interface_adapter.edit_status.EditStatusPresenter;
import interface_adapter.edit_status.EditStatusViewModel;
import interface_adapter.edit_tiles.SelectDoraViewModel;
import interface_adapter.edit_tiles.TileSelectorViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.edit_status.EditStatusDataAccessInterface;
import usecase.edit_status.EditStatusInputBoundary;
import usecase.edit_status.EditStatusInteractor;
import usecase.edit_status.EditStatusOutputBoundary;
import view.EditStatusView;

import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;
import static org.junit.Assert.assertEquals;

public class EditStatusViewTest {
    EditStatusViewModel editStatusViewModel;
    SelectDoraViewModel selectDoraViewModel;
    TileSelectorViewModel tileSelectorViewModel;
    ViewManagerModel viewManagerModel;
    EditStatusView editStatusView;

    @BeforeEach
    void setUp() {
        editStatusViewModel = new EditStatusViewModel();
        selectDoraViewModel = new SelectDoraViewModel();
        tileSelectorViewModel = new TileSelectorViewModel();
        viewManagerModel = new ViewManagerModel();

        EditStatusDataAccessInterface playerDataAccessObject = new InMemoryUniversalDataAccessObject();
        EditStatusOutputBoundary playerPresenter = new EditStatusPresenter(editStatusViewModel,
                selectDoraViewModel, tileSelectorViewModel, viewManagerModel);

        EditStatusInputBoundary editStatusUseCaseInteractor = new EditStatusInteractor(playerDataAccessObject,
                playerPresenter);
        editStatusView = new EditStatusView(editStatusViewModel);
    }

    public static void main(String[] args) {
        EditStatusViewModel editStatusViewModel = new EditStatusViewModel();
        SelectDoraViewModel selectDoraViewModel = new SelectDoraViewModel();
        TileSelectorViewModel tileSelectorViewModel = new TileSelectorViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        EditStatusDataAccessInterface playerDataAccessObject = new InMemoryUniversalDataAccessObject();
        EditStatusOutputBoundary playerPresenter = new EditStatusPresenter(editStatusViewModel,
                selectDoraViewModel, tileSelectorViewModel, viewManagerModel);

        EditStatusInputBoundary editStatusUseCaseInteractor = new EditStatusInteractor(playerDataAccessObject,
                playerPresenter);

        EditStatusController editStatusController = new EditStatusController(editStatusUseCaseInteractor);
        EditStatusView editStatusView = new EditStatusView(editStatusViewModel);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(editStatusView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // showMessageDialog(null, editStatusView.checkBoxesToString());
    }

    @Test
    public void testCheckBoxesToString() {
        assertEquals("The number of checkboxes should be equal to the number of attributes",
                EditStatusViewModel.ATTRIBUTES.length, editStatusView.checkBoxesToString().split(", ").length);

    }

    @Test
    void testCheckBoxName() {
        assertEquals("The first checkbox should be 'Riichi'",
                "Riichi", editStatusView.getCheckBoxes().get(0).getText());
    }
}