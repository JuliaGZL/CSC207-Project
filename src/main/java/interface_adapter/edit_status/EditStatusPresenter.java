package interface_adapter.edit_status;

import interface_adapter.ViewManagerModel;
import interface_adapter.edit_tiles.SelectDoraState;
import interface_adapter.edit_tiles.SelectDoraViewModel;
import interface_adapter.edit_tiles.TileSelectorState;
import interface_adapter.edit_tiles.TileSelectorViewModel;
import use_case.edit_status.EditStatusOutputBoundary;
import use_case.edit_status.EditStatusOutputData;

/**
 * The Presenter for the Edit Status Use Case.
 */
public class EditStatusPresenter implements EditStatusOutputBoundary  {

    private final EditStatusViewModel editStatusViewModel;
    private final SelectDoraViewModel selectDoraViewModel;
    private final TileSelectorViewModel tileSelectorViewModel;
    private final ViewManagerModel  viewManagerModel;

    public EditStatusPresenter(EditStatusViewModel editStatusViewModel,
                               SelectDoraViewModel selectDoraViewModel,
                               TileSelectorViewModel tileSelectorViewModel,
                               ViewManagerModel viewManagerModel) {
        this.editStatusViewModel = editStatusViewModel;
        this.selectDoraViewModel = selectDoraViewModel;
        this.tileSelectorViewModel = tileSelectorViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EditStatusOutputData outputData) {
        // This use case currently does not need to prepare a success view.
        editStatusViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // This use case currently cannot fail.
    }

    @Override
    public void switchToSelectDoraView(EditStatusOutputData outputData) {
        // Tell the tile selector to now add new tiles to dora.
        final TileSelectorState tileSelectorState = tileSelectorViewModel.getState();
        tileSelectorState.setTarget("dora");
        tileSelectorViewModel.setState(tileSelectorState);
        tileSelectorViewModel.firePropertyChanged("target");

        final SelectDoraState selectDoraState = selectDoraViewModel.getState();
        selectDoraState.setIndicatorSelections(outputData.getDoraCounts());
        selectDoraViewModel.setState(selectDoraState);
        selectDoraViewModel.firePropertyChanged();

        viewManagerModel.setState(selectDoraViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToSelectForHand() {
        // Tell the tile selector to now add new tiles to hand.
        final TileSelectorState tileSelectorState = tileSelectorViewModel.getState();
        tileSelectorState.setTarget("hand");
        tileSelectorViewModel.setState(tileSelectorState);
        tileSelectorViewModel.firePropertyChanged("target");
    }
}
