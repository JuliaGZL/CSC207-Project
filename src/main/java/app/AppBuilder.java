package app;

import data_access.InMemoryUniversalDataAccessObject;
import entity.PlayerFactory;
import entity.Tile;
import interface_adapter.ViewManagerModel;
import interface_adapter.edit_status.EditStatusController;
import interface_adapter.edit_status.EditStatusPresenter;
import interface_adapter.edit_status.EditStatusViewModel;
import interface_adapter.edit_tiles.*;
import interface_adapter.player_events.*;
import usecase.add_tile.AddTileInputBoundary;
import usecase.add_tile.AddTileInteractor;
import usecase.change_player.ChangePlayerInteractor;
import usecase.clear_tiles.ClearTilesInputBoundary;
import usecase.clear_tiles.ClearTilesInteractor;
import usecase.clear_tiles.ClearTilesOutputBoundary;
import usecase.edit_status.EditStatusInteractor;
import usecase.edit_status.EditStatusInputBoundary;
import usecase.edit_status.EditStatusOutputBoundary;
import usecase.hu_solver.HuSolveOutputBoundary;
import usecase.hu_solver.HuSolverInputBoundary;
import usecase.hu_solver.HuSolverInteractor;
import usecase.remove_tile.RemoveTileInputBoundary;
import usecase.remove_tile.RemoveTileInteractor;
import usecase.update_enabled_tiles.UpdateEnabledTileInteractor;
import view.EditStatusView;
import view.PlayerEventsView;
import view.TileDisplayView;
import view.TileSelectorView;

import javax.swing.*;

import java.util.ArrayList;

// TODO: this is very messy, let's improve it later.
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

    // view models
    private EditStatusViewModel editStatusViewModel;
    private SelectDoraViewModel selectDoraViewModel;
    private TileSelectorViewModel tileSelectorViewModel;
    private ViewManagerModel viewManagerMode = new ViewManagerModel();

    // shared presenters
    private AddRemoveTilePresenter handPresenter;
    private AddRemoveTilePresenter doraPresenter;
    private AddRemoveTilePresenter uradoraPresenter;
    private ChangePlayerPresenter changePlayerPresenter =
            new ChangePlayerPresenter(new ArrayList<TilesDisplayViewModel>());

    // factory
    private final PlayerFactory playerFactory = new PlayerFactory();

    public AppBuilder() {

    }

    public AppBuilder addEditStatusView() {
        editStatusViewModel = new EditStatusViewModel();
        selectDoraViewModel = new SelectDoraViewModel();
        EditStatusOutputBoundary presenter = new EditStatusPresenter(editStatusViewModel,
                selectDoraViewModel, tileSelectorViewModel, viewManagerMode);

        //        EditStatusInputBoundary interactor = new EditStatusPresenter(model, new SelectDoraViewModel());
        EditStatusInputBoundary interactor = new EditStatusInteractor(DAO, presenter);
        this.editStatusView = new EditStatusView(editStatusViewModel);
        editStatusView.setEditStatusController(new EditStatusController(interactor));
        return this;
    }

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

    public AppBuilder addHandDisplayView() {
        // instantiate view model
        TilesDisplayViewModel model = new TilesDisplayViewModel("Hand");

        // configure clear tile
        ClearTilesOutputBoundary clearPresenter = new ClearTilePresenter(model);
        ClearTilesInputBoundary clearInteractor = new ClearTilesInteractor(
                DAO, clearPresenter, ClearTilesInteractor.HAND);
        ClearTilesController clearController = new ClearTilesController(clearInteractor);

        // configure remove tile
        handPresenter = new AddRemoveTilePresenter(model);
        RemoveTileInputBoundary removeInteractor = new RemoveTileInteractor(
                DAO, handPresenter, RemoveTileInteractor.HAND);
        RemoveTileController removeController = new RemoveTileController(removeInteractor);

        // configure change player
        changePlayerPresenter.addTileDisplayViewModel(model);

        // configure view
        handDisplayView = new TileDisplayView(model);
        handDisplayView.setClearTilesController(clearController);
        handDisplayView.setRemoveTileController(removeController);
        return this;
    }

    public AppBuilder addDoraDisplayView() {
        // instantiate view model
        TilesDisplayViewModel model = new TilesDisplayViewModel("Dora");

        // configure clear tile
        ClearTilesOutputBoundary clearPresenter = new ClearTilePresenter(model);
        ClearTilesInputBoundary clearInteractor = new ClearTilesInteractor(
                DAO, clearPresenter, ClearTilesInteractor.DORA);
        ClearTilesController clearController = new ClearTilesController(clearInteractor);

        // configure remove tile
        doraPresenter = new AddRemoveTilePresenter(model);
        RemoveTileInputBoundary removeInteractor = new RemoveTileInteractor(
                DAO, doraPresenter, RemoveTileInteractor.DORA);
        RemoveTileController removeController = new RemoveTileController(removeInteractor);

        // configure change player
        changePlayerPresenter.addTileDisplayViewModel(model);

        // configure view
        doraDisplayView = new TileDisplayView(model);
        doraDisplayView.setClearTilesController(clearController);
        doraDisplayView.setRemoveTileController(removeController);
        return this;
    }

    public AppBuilder addUradoraDisplayView() {
        // instantiate view model
        TilesDisplayViewModel model = new TilesDisplayViewModel("Uradora");

        // configure clear tile
        ClearTilesOutputBoundary clearPresenter = new ClearTilePresenter(model);
        ClearTilesInputBoundary clearInteractor = new ClearTilesInteractor(
                DAO, clearPresenter, ClearTilesInteractor.URADORA);
        ClearTilesController clearController = new ClearTilesController(clearInteractor);

        // configure remove tile
        uradoraPresenter = new AddRemoveTilePresenter(model);
        RemoveTileInputBoundary removeInteractor = new RemoveTileInteractor(
                DAO, uradoraPresenter, RemoveTileInteractor.URADORA);
        RemoveTileController removeController = new RemoveTileController(removeInteractor);

        // configure change player
        changePlayerPresenter.addTileDisplayViewModel(model);

        // configure view
        uradoraDisplayView = new TileDisplayView(model);
        uradoraDisplayView.setClearTilesController(clearController);
        uradoraDisplayView.setRemoveTileController(removeController);
        return this;
    }

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
        AddTileController uradoraController = new AddTileController(uradoraInteractor);

        // configure update_enabled_tiles
        UpdateEnabledTilePresenter updateEnabledTilePresenter = new UpdateEnabledTilePresenter(tileSelectorViewModel);
        UpdateEnabledTileInteractor updateEnabledTileInteractor =
                new UpdateEnabledTileInteractor(DAO, updateEnabledTilePresenter);
        UpdateEnabledTileController updateEnabledTileController =
                new UpdateEnabledTileController(updateEnabledTileInteractor);

        // configure selector
        tileSelectorView = new TileSelectorView(tileSelectorViewModel);
        tileSelectorView.setAddToHandController(handController);
        tileSelectorView.setAddToDoraController(doraController);
        tileSelectorView.setAddToUradoraController(uradoraController);
        tileSelectorView.setUpdateEnabledTileController(updateEnabledTileController);

        // configure update notification event
        TileSelectorPropertyUpdateNotifier notifier = new TileSelectorPropertyUpdateNotifier(tileSelectorViewModel);
        handDisplayView.setNotifier(notifier);
        doraDisplayView.setNotifier(notifier);
        uradoraDisplayView.setNotifier(notifier);
        editStatusView.setNotifier(notifier);

        // configure change player
        changePlayerPresenter.setTileSelectorViewModel(tileSelectorViewModel);

        return this;
    }

    public AppBuilder addChangePlayerSupport() {
        ChangePlayerInteractor interactor = new ChangePlayerInteractor(DAO, changePlayerPresenter);
        ChangePlayerController controller = new ChangePlayerController(interactor);
        playerEventsView.setChangePlayerController(controller);
        return this;
    }

    public JFrame build() {
        DAO.savePlayer(playerFactory.createEmpty("default"));

        JFrame app = new JFrame();
        JPanel upperPanel = new JPanel();
        JPanel upperRightPanel = new JPanel();
        JPanel lowerPanel = new JPanel();
        JPanel appPanel = new JPanel();

        upperRightPanel.setLayout(new BoxLayout(upperRightPanel, BoxLayout.Y_AXIS));
        upperRightPanel.add(playerEventsView);
        upperRightPanel.add(editStatusView);

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
