package unit.view;

import static org.junit.Assert.assertEquals;

import dataaccess.InMemoryUniversalDataAccessObject;
import interfaceadapter.ViewManagerModel;
import interfaceadapter.editstatus.EditStatusController;
import interfaceadapter.editstatus.EditStatusPresenter;
import interfaceadapter.editstatus.EditStatusViewModel;
import interfaceadapter.edittiles.SelectDoraViewModel;
import interfaceadapter.edittiles.TileSelectorViewModel;
import javax.swing.JFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import usecase.editstatus.EditStatusDataAccessInterface;
import usecase.editstatus.EditStatusInputBoundary;
import usecase.editstatus.EditStatusInteractor;
import usecase.editstatus.EditStatusOutputBoundary;
import view.EditStatusView;

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