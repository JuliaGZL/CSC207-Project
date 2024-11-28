package usecase.edit_status;

import entity.Player;

/**
 * The Edit Status Interactor.
 */
public class EditStatusInteractor implements EditStatusInputBoundary {
    private final EditStatusDataAccessInterface playerDataAccessObject;
    private final EditStatusOutputBoundary playerPresenter;

    public EditStatusInteractor(EditStatusDataAccessInterface playerDataAccessObject, EditStatusOutputBoundary playerPresenter) {
        this.playerDataAccessObject = playerDataAccessObject;
        this.playerPresenter = playerPresenter;
    }

    @Override
    public void execute(EditStatusInputData editStatusInputData) {
        final String name = editStatusInputData.getPlayerName();
        if (!playerDataAccessObject.existsByName(name)) {
            // This should never happen!
            throw new RuntimeException("Player name not found!");
        }
        else {
            final Player player = playerDataAccessObject.getPlayer(name);
            // Update data
            player.setWinType(editStatusInputData.getWinType());
            player.setRoundWind(editStatusInputData.getRoundWind());
            player.setSeatWind(editStatusInputData.getSeatWind());
            player.setNumAkaDora(editStatusInputData.getNumAkadora());
            player.setAttributes(editStatusInputData.getAttributes());
            // Save updated data
            playerDataAccessObject.savePlayer(player);

            final EditStatusOutputData editStatusOutputData = new EditStatusOutputData(player);
            playerPresenter.prepareSuccessView(editStatusOutputData);
        }
    }

    public void execute(String updateName, EditStatusInputData editStatusInputData) {
        final String name = editStatusInputData.getPlayerName();
        if (!playerDataAccessObject.existsByName(name)) {
            // This should never happen!
            throw new RuntimeException("Player name not found!");
        }
        else {
            final Player player = playerDataAccessObject.getPlayer(name);
            // Update data
            player.setWinType(editStatusInputData.getWinType());
            player.setRoundWind(editStatusInputData.getRoundWind());
            player.setSeatWind(editStatusInputData.getSeatWind());
            player.setNumAkaDora(editStatusInputData.getNumAkadora());
            player.setAttributes(editStatusInputData.getAttributes());
            // Save updated data
            playerDataAccessObject.savePlayer(player);

            final EditStatusOutputData editStatusOutputData = new EditStatusOutputData(player);
            playerPresenter.prepareSuccessView(updateName, editStatusOutputData);
        }
    }

    @Override
    public void switchToSelectDoraView(EditStatusInputData inputData) {


//        playerPresenter.switchToSelectDoraView();
    }
}
