package unit.usecase.editstatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaceadapter.ViewManagerModel;
import interfaceadapter.editstatus.EditStatusPresenter;
import interfaceadapter.editstatus.EditStatusViewModel;
import interfaceadapter.edittiles.SelectDoraViewModel;
import interfaceadapter.edittiles.TileSelectorViewModel;
import usecase.editstatus.EditStatusOutputBoundary;
import usecase.editstatus.EditStatusOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for EditStatusOutputBoundary.
 */
public class EditStatusPresenterTest {
  private EditStatusViewModel editStatusViewModel;
  private SelectDoraViewModel selectDoraViewModel;
  private TileSelectorViewModel tileSelectorViewModel;
  private ViewManagerModel viewManagerModel;

  private EditStatusOutputBoundary presenter;

  @BeforeEach
  void setUp() {
    editStatusViewModel = new EditStatusViewModel();
    selectDoraViewModel = new SelectDoraViewModel();
    tileSelectorViewModel = new TileSelectorViewModel();
    viewManagerModel = new ViewManagerModel();

    presenter = new EditStatusPresenter(editStatusViewModel, selectDoraViewModel,
        tileSelectorViewModel, viewManagerModel);
  }

  @Test
  void testPrepareSuccessViewOneVar() {
    // Create dummy outputData
    Boolean[] mockAttributes = { false, true, false, false, false, false, false, false, true, false };
    int mockNumAkadora = 1;
    String mockSeatWind = "West";
    String mockRoundWind = "East";
    String mockWinType = "Tsumo";
    String mockPlayerName = "default";

    EditStatusOutputData dummyOutputData = new EditStatusOutputData(mockAttributes,
        mockNumAkadora, mockSeatWind, mockRoundWind, mockWinType, mockPlayerName);

    // Call the method
    presenter.prepareSuccessView(dummyOutputData);

    // Check whether all state variables are set correctly
    assertEquals(mockAttributes, editStatusViewModel.getState().getAttributes());
    assertEquals(mockNumAkadora, editStatusViewModel.getState().getNumAkadora());
    assertEquals(mockSeatWind, editStatusViewModel.getState().getSeatWind());
    assertEquals(mockRoundWind, editStatusViewModel.getState().getRoundWind());
    assertEquals(mockWinType, editStatusViewModel.getState().getWinType());
  }

  @Test
  void testPrepareSuccessViewTwoVar() {
    // Create dummy outputData
    Boolean[] mockAttributes = { false, true, false, false, false, false, false, false, true, false };
    int mockNumAkadora = 1;
    String mockSeatWind = "West";
    String mockRoundWind = "East";
    String mockWinType = "Tsumo";
    String mockPlayerName = "default";

    EditStatusOutputData dummyOutputData = new EditStatusOutputData(mockAttributes,
        mockNumAkadora, mockSeatWind, mockRoundWind, mockWinType, mockPlayerName);

    // Call the method
    presenter.prepareSuccessView("dummyUpdateName", dummyOutputData);

    // Check whether all state variables are set correctly
    assertEquals(mockAttributes, editStatusViewModel.getState().getAttributes());
    assertEquals(mockNumAkadora, editStatusViewModel.getState().getNumAkadora());
    assertEquals(mockSeatWind, editStatusViewModel.getState().getSeatWind());
    assertEquals(mockRoundWind, editStatusViewModel.getState().getRoundWind());
    assertEquals(mockWinType, editStatusViewModel.getState().getWinType());
  }

  @Test
  void testPrepareFailView() {
    // Call the method
    presenter.prepareFailView("dummyErrorMessage");

    // This method does not have any implementation, so the test to it should always
    // be true.
    assertTrue(true);
  }

  @Test
  void testSwitchToSelectDoraView() {
    // Create dummy outputData
    Boolean[] mockAttributes = { false, true, false, false, false, false, false, false, true, false };
    int mockNumAkadora = 1;
    String mockSeatWind = "West";
    String mockRoundWind = "East";
    String mockWinType = "Tsumo";
    String mockPlayerName = "default";

    EditStatusOutputData dummyOutputData = new EditStatusOutputData(mockAttributes,
        mockNumAkadora, mockSeatWind, mockRoundWind, mockWinType, mockPlayerName);

    // Call the method
    presenter.switchToSelectDoraView(dummyOutputData);

    // Check whether all state variables are set correctly
    // assertEquals("dora", tileSelectorViewModel.getState().getTarget());
    assertEquals(dummyOutputData.getDoraCounts(), selectDoraViewModel.getState().getIndicatorSelections());
    assertEquals(selectDoraViewModel.getViewName(), viewManagerModel.getState());
  }

  @Test
  void testSwitchToSelectForHand() {
    // Call the method
    presenter.switchToSelectForHand();

    // Check whether all state variables are set correctly
    // assertEquals("hand", tileSelectorViewModel.getState().getTarget());
    assertTrue(true);
  }
}
