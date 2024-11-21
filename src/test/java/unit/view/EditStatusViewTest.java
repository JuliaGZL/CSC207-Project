package unit.view;

import data_access.InMemoryUniversalDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.edit_status.EditStatusController;
import interface_adapter.edit_status.EditStatusPresenter;
import interface_adapter.edit_status.EditStatusViewModel;
import use_case.edit_status.EditStatusDataAccessInterface;
import use_case.edit_status.EditStatusInputBoundary;
import use_case.edit_status.EditStatusInteractor;
import use_case.edit_status.EditStatusOutputBoundary;
import view.EditStatusView;

import javax.swing.*;

public class EditStatusViewTest {
    public static void main(String[] args) {
        EditStatusViewModel editStatusViewModel = new EditStatusViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        EditStatusDataAccessInterface playerDataAccessObject = new InMemoryUniversalDataAccessObject();
        EditStatusOutputBoundary playerPresenter = new EditStatusPresenter(editStatusViewModel, viewManagerModel);

        EditStatusInputBoundary editStatusUseCaseInteractor = new EditStatusInteractor(playerDataAccessObject, playerPresenter);


        EditStatusController editStatusController = new EditStatusController(editStatusUseCaseInteractor);
        EditStatusView editStatusView = new EditStatusView(editStatusViewModel, editStatusController);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(editStatusView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
