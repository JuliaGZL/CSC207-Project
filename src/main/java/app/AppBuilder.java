/*
 Copyright (c) 2024 YAizhou, li-tianchu, JuliaGZL, TurkeyBilly

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package app;

import dataaccess.InMemoryUniversalDataAccessObject;
import dataaccess.discordbot.PullRemoteHandDataAccessObject;
import entity.PlayerFactory;
import interfaceadapter.ViewManagerModel;
import interfaceadapter.displaytiles.PullRemoteHandPresenter;
import interfaceadapter.displaytiles.TileSelectorPropertyUpdateNotifier;
import interfaceadapter.displaytiles.TilesDisplayViewModel;
import interfaceadapter.editstatus.EditStatusController;
import interfaceadapter.editstatus.EditStatusPresenter;
import interfaceadapter.editstatus.EditStatusViewModel;
import interfaceadapter.edittiles.AddRemoveTilePresenter;
import interfaceadapter.edittiles.AddTileController;
import interfaceadapter.edittiles.ClearTilePresenter;
import interfaceadapter.edittiles.ClearTilesController;
import interfaceadapter.edittiles.RemoveTileController;
import interfaceadapter.edittiles.SelectDoraViewModel;
import interfaceadapter.edittiles.TileSelectorViewModel;
import interfaceadapter.edittiles.UpdateEnabledTileController;
import interfaceadapter.edittiles.UpdateEnabledTilePresenter;
import interfaceadapter.playerevents.ChangePlayerController;
import interfaceadapter.playerevents.ChangePlayerPresenter;
import interfaceadapter.playerevents.HuSolverController;
import interfaceadapter.playerevents.HuSolverPresenter;
import interfaceadapter.playerevents.PlayerEventsViewModel;
import interfaceadapter.playerevents.PullRemoteHandController;
import interfaceadapter.readhand.ReadHandController;
import interfaceadapter.readhand.ReadHandPresenter;
import interfaceadapter.readhand.ReadHandViewModel;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import usecase.addtile.AddTileInputBoundary;
import usecase.addtile.AddTileInteractor;
import usecase.changeplayer.ChangePlayerInteractor;
import usecase.cleartiles.ClearTilesInputBoundary;
import usecase.cleartiles.ClearTilesInteractor;
import usecase.cleartiles.ClearTilesOutputBoundary;
import usecase.editstatus.EditStatusInputBoundary;
import usecase.editstatus.EditStatusInteractor;
import usecase.editstatus.EditStatusOutputBoundary;
import usecase.husolver.HuSolveOutputBoundary;
import usecase.husolver.HuSolverInputBoundary;
import usecase.husolver.HuSolverInteractor;
import usecase.pullremotehand.PullRemoteHandInteractor;
import usecase.readhand.ReadHandInputBoundary;
import usecase.readhand.ReadHandInteractor;
import usecase.readhand.ReadHandOutputBoundary;
import usecase.removetile.RemoveTileInputBoundary;
import usecase.removetile.RemoveTileInteractor;
import usecase.updateenabledtiles.UpdateEnabledTileInteractor;
import view.EditStatusView;
import view.PlayerEventsView;
import view.ReadHandView;
import view.TileDisplayView;
import view.TileSelectorView;

/**
 * The AppBuilder class is responsible for building the application.
 */
public class AppBuilder {
  // data access
  private final InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();

  // views
  private EditStatusView editStatusView;
  private PlayerEventsView playerEventsView;
  private TileDisplayView handDisplayView;
  private TileDisplayView doraDisplayView;
  private TileDisplayView uradoraDisplayView;
  private TileSelectorView tileSelectorView;
  private ReadHandView readHandView;

  private TileSelectorViewModel tileSelectorViewModel;
  private final ViewManagerModel viewManagerMode = new ViewManagerModel();

  // shared presenters
  private AddRemoveTilePresenter handPresenter;
  private AddRemoveTilePresenter doraPresenter;
  private AddRemoveTilePresenter uradoraPresenter;
  private final ChangePlayerPresenter changePlayerPresenter = new ChangePlayerPresenter(
      new ArrayList<TilesDisplayViewModel>());

  // shared controller
  private PullRemoteHandController pullRemoteHandController;

  // factory
  private final PlayerFactory playerFactory = new PlayerFactory();

  /**
   * Default constructor for AppBuilder.
   */
  public AppBuilder() {
    // Default constructor
  }

  /**
   * Adds the EditStatusView to the application.
   *
   * @return the AppBuilder instance
   */
  public AppBuilder addEditStatusView() {
    // view models
    EditStatusViewModel editStatusViewModel = new EditStatusViewModel();
    SelectDoraViewModel selectDoraViewModel = new SelectDoraViewModel();
    EditStatusOutputBoundary presenter = new EditStatusPresenter(editStatusViewModel,
        selectDoraViewModel, tileSelectorViewModel, viewManagerMode);

    EditStatusInputBoundary interactor = new EditStatusInteractor(DAO, presenter);
    this.editStatusView = new EditStatusView(editStatusViewModel);
    editStatusView.setEditStatusController(new EditStatusController(interactor));
    return this;
  }

  /**
   * Adds the PlayerEventsView to the application.
   *
   * @return the AppBuilder instance
   */
  public AppBuilder addPlayerEventsView() {
    PlayerEventsViewModel model = new PlayerEventsViewModel();
    HuSolveOutputBoundary presenter = new HuSolverPresenter(model);
    HuSolverInputBoundary interactor = new HuSolverInteractor(DAO, presenter);
    HuSolverController controller = new HuSolverController(interactor);
    playerEventsView = new PlayerEventsView(model);
    changePlayerPresenter.setPlayerEventsViewModel(model);
    playerEventsView.setHuSolverController(controller);
    return this;
  }

  /**
   * Adds the HandDisplayView to the application.
   *
   * @return the AppBuilder instance
   */
  public AppBuilder addHandDisplayView() {
    // instantiate view model
    TilesDisplayViewModel model = new TilesDisplayViewModel("Hand");

    // configure clear tile
    ClearTilesOutputBoundary clearPresenter = new ClearTilePresenter(model);
    ClearTilesInputBoundary clearInteractor = new ClearTilesInteractor(
        DAO, clearPresenter, ClearTilesInteractor.HAND);
    final ClearTilesController clearController = new ClearTilesController(clearInteractor);

    // configure remove tile
    handPresenter = new AddRemoveTilePresenter(model);
    RemoveTileInputBoundary removeInteractor = new RemoveTileInteractor(
        DAO, handPresenter, RemoveTileInteractor.HAND);
    final RemoveTileController removeController = new RemoveTileController(removeInteractor);

    // configure pull remote hand
    PullRemoteHandDataAccessObject pullRemoteHandDAO = new PullRemoteHandDataAccessObject(DAO);
    PullRemoteHandPresenter pullRemoteHandPresenter = new PullRemoteHandPresenter(handPresenter);
    PullRemoteHandInteractor pullRemoteHandInteractor = new PullRemoteHandInteractor(
        pullRemoteHandDAO, pullRemoteHandPresenter);
    pullRemoteHandController = new PullRemoteHandController(pullRemoteHandInteractor);

    // configure change player
    changePlayerPresenter.addTileDisplayViewModel(model);

    // configure view
    handDisplayView = new TileDisplayView(model);
    handDisplayView.setClearTilesController(clearController);
    handDisplayView.setRemoveTileController(removeController);
    return this;
  }

  /**
   * Adds the DoraDisplayView to the application.
   *
   * @return the AppBuilder instance
   */
  public AppBuilder addDoraDisplayView() {
    // instantiate view model
    TilesDisplayViewModel model = new TilesDisplayViewModel("Dora");

    // configure clear tile
    ClearTilesOutputBoundary clearPresenter = new ClearTilePresenter(model);
    ClearTilesInputBoundary clearInteractor = new ClearTilesInteractor(
        DAO, clearPresenter, ClearTilesInteractor.DORA);
    final ClearTilesController clearController = new ClearTilesController(clearInteractor);

    // configure remove tile
    doraPresenter = new AddRemoveTilePresenter(model);
    RemoveTileInputBoundary removeInteractor = new RemoveTileInteractor(
        DAO, doraPresenter, RemoveTileInteractor.DORA);
    final RemoveTileController removeController = new RemoveTileController(removeInteractor);

    // configure change player
    changePlayerPresenter.addTileDisplayViewModel(model);

    // configure view
    doraDisplayView = new TileDisplayView(model);
    doraDisplayView.setClearTilesController(clearController);
    doraDisplayView.setRemoveTileController(removeController);
    return this;
  }

  /**
   * Adds the UradoraDisplayView to the application.
   *
   * @return the AppBuilder instance
   */
  public AppBuilder addUradoraDisplayView() {
    // instantiate view model
    TilesDisplayViewModel model = new TilesDisplayViewModel("Uradora");

    // configure clear tile
    ClearTilesOutputBoundary clearPresenter = new ClearTilePresenter(model);
    ClearTilesInputBoundary clearInteractor = new ClearTilesInteractor(
        DAO, clearPresenter, ClearTilesInteractor.URADORA);
    final ClearTilesController clearController = new ClearTilesController(clearInteractor);

    // configure remove tile
    uradoraPresenter = new AddRemoveTilePresenter(model);
    RemoveTileInputBoundary removeInteractor = new RemoveTileInteractor(
        DAO, uradoraPresenter, RemoveTileInteractor.URADORA);
    final RemoveTileController removeController = new RemoveTileController(removeInteractor);

    // configure change player
    changePlayerPresenter.addTileDisplayViewModel(model);

    // configure view
    uradoraDisplayView = new TileDisplayView(model);
    uradoraDisplayView.setClearTilesController(clearController);
    uradoraDisplayView.setRemoveTileController(removeController);
    return this;
  }

  /**
   * Adds the TileSelectorView to the application.
   *
   * @return the AppBuilder instance
   */
  public AppBuilder addTileSelectorView() {
    // instantiate view model
    tileSelectorViewModel = new TileSelectorViewModel();

    // configure add tile
    AddTileInputBoundary handInteractor = new AddTileInteractor(
        DAO, handPresenter, AddTileInteractor.HAND);
    AddTileController handController = new AddTileController(handInteractor);

    AddTileInputBoundary doraInteractor = new AddTileInteractor(
        DAO, doraPresenter, AddTileInteractor.DORA);
    AddTileController doraController = new AddTileController(doraInteractor);

    AddTileInputBoundary uradoraInteractor = new AddTileInteractor(
        DAO, uradoraPresenter, AddTileInteractor.URADORA);
    final AddTileController uradoraController = new AddTileController(uradoraInteractor);

    // configure update_enabled_tiles
    UpdateEnabledTilePresenter updateEnabledTilePresenter = new UpdateEnabledTilePresenter(
        tileSelectorViewModel);
    UpdateEnabledTileInteractor updateEnabledTileInteractor = new UpdateEnabledTileInteractor(DAO,
        updateEnabledTilePresenter);
    final UpdateEnabledTileController updateEnabledTileController = new UpdateEnabledTileController(
        updateEnabledTileInteractor);

    // configure selector
    tileSelectorView = new TileSelectorView(tileSelectorViewModel);
    tileSelectorView.setAddToHandController(handController);
    tileSelectorView.setAddToDoraController(doraController);
    tileSelectorView.setAddToUradoraController(uradoraController);
    tileSelectorView.setUpdateEnabledTileController(updateEnabledTileController);

    // configure update notification event
    TileSelectorPropertyUpdateNotifier notifier = new TileSelectorPropertyUpdateNotifier(
        tileSelectorViewModel);
    handDisplayView.setNotifier(notifier);
    doraDisplayView.setNotifier(notifier);
    uradoraDisplayView.setNotifier(notifier);
    editStatusView.setNotifier(notifier);

    // configure change player
    changePlayerPresenter.setTileSelectorViewModel(tileSelectorViewModel);

    return this;
  }

  /**
   * Adds support for changing players.
   *
   * @return the AppBuilder instance
   */
  public AppBuilder addChangePlayerSupport() {
    ChangePlayerInteractor interactor = new ChangePlayerInteractor(DAO, changePlayerPresenter);
    ChangePlayerController controller = new ChangePlayerController(interactor);
    playerEventsView.setChangePlayerController(controller);
    playerEventsView.setPullRemoteHandController(pullRemoteHandController);
    return this;
  }

  /**
   * Adds the HandReader to the application.
   *
   * @return the AppBuilder instance
   */
  public AppBuilder addHandReader() {
    ReadHandViewModel model = new ReadHandViewModel();
    ReadHandOutputBoundary presenter = new ReadHandPresenter(model);
    ReadHandInputBoundary interactor = new ReadHandInteractor(DAO, presenter);
    ReadHandController controller = new ReadHandController(interactor);
    readHandView = new ReadHandView(model);
    readHandView.setReadHandController(controller);
    return this;
  }

  /**
   * Builds the application and returns the main JFrame.
   *
   * @return the main JFrame of the application
   */
  public JFrame build() {
    DAO.savePlayer(playerFactory.createEmpty("default"));

    final JFrame app = new JFrame();
    final JPanel upperPanel = new JPanel();
    final JPanel upperRightPanel = new JPanel();
    final JPanel lowerPanel = new JPanel();
    final JPanel appPanel = new JPanel();

    upperRightPanel.setLayout(new BoxLayout(upperRightPanel, BoxLayout.Y_AXIS));
    upperRightPanel.add(playerEventsView);
    upperRightPanel.add(editStatusView);
    upperRightPanel.add(readHandView);

    upperPanel.setLayout(new BoxLayout(upperPanel, BoxLayout.X_AXIS));
    upperPanel.add(tileSelectorView);
    upperPanel.add(upperRightPanel);

    lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
    lowerPanel.add(handDisplayView);
    lowerPanel.add(doraDisplayView);
    lowerPanel.add(uradoraDisplayView);

    appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.Y_AXIS));
    appPanel.add(upperPanel);
    appPanel.add(lowerPanel);

    app.add(appPanel);
    app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    app.setResizable(false);

    return app;
  }
}
