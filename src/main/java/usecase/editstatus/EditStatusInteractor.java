package usecase.editstatus;

import entity.Player;

/**
 * The Edit Status Interactor.
 */
public class EditStatusInteractor implements EditStatusInputBoundary {
  private final EditStatusDataAccessInterface playerDataAccessObject;
  private final EditStatusOutputBoundary playerPresenter;

  /**
   * Constructs an EditStatusInteractor with the given data access object and presenter.
   *
   * @param playerDataAccessObject the data access object for player data
   * @param playerPresenter the presenter for output data
   */
  public EditStatusInteractor(EditStatusDataAccessInterface playerDataAccessObject,
      EditStatusOutputBoundary playerPresenter) {
    this.playerDataAccessObject = playerDataAccessObject;
    this.playerPresenter = playerPresenter;
  }

  /**
   * Executes the edit status use case with the given input data.
   *
   * @param editStatusInputData the input data for editing status
   */
  @Override
  public void execute(EditStatusInputData editStatusInputData) {
    final String name = editStatusInputData.getPlayerName();
    if (!playerDataAccessObject.existsByName(name)) {
      // This should never happen!
      throw new RuntimeException("Player name not found!");
    } else {
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

  /**
   * Executes the edit status use case with the given input data and update name.
   *
   * @param updateName the name to update
   * @param editStatusInputData the input data for editing status
   */
  public void execute(String updateName, EditStatusInputData editStatusInputData) {
    final String name = editStatusInputData.getPlayerName();
    if (!playerDataAccessObject.existsByName(name)) {
      // This should never happen!
      throw new RuntimeException("Player name not found!");
    } else {
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

  /**
   * Switches to the select dora view with the given input data.
   *
   * @param inputData the input data for switching view
   */
  @Override
  public void switchToSelectDoraView(EditStatusInputData inputData) {
    // playerPresenter.switchToSelectDoraView();
  }
}
