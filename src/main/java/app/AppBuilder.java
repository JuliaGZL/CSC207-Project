package app;

import data_access.InMemoryUniversalDataAccessObject;
import entity.PlayerFactory;
import interface_adapter.edit_status.EditStatusViewModel;
import interface_adapter.edit_tiles.*;
import interface_adapter.player_events.ChangePlayerController;
import interface_adapter.player_events.ChangePlayerPresenter;
import interface_adapter.player_events.PlayerEventsViewModel;
import mahjong.BaseTile;
import usecase.add_tile.AddTileInputBoundary;
import usecase.add_tile.AddTileInteractor;
import usecase.change_player.ChangePlayerInteractor;
import usecase.clear_tiles.ClearTilesInputBoundary;
import usecase.clear_tiles.ClearTilesInteractor;
import usecase.clear_tiles.ClearTilesOutputBoundary;
import usecase.remove_tile.RemoveTileInputBoundary;
import usecase.remove_tile.RemoveTileInteractor;
import usecase.update_enabled_tiles.UpdateEnabledTileInteractor;
import utils.BaseTileToPathMapping;
import view.EditStatusView;
import view.PlayerEventsView;
import view.TileDisplayView;
import view.TileSelectorView;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

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

    // shared presenters
    private AddRemoveTilePresenter handPresenter;
    private AddRemoveTilePresenter doraPresenter;
    private AddRemoveTilePresenter uradoraPresenter;

    // shared view model
    private PlayerEventsViewModel playerEventsViewModel;

    // factory
    private final PlayerFactory playerFactory = new PlayerFactory();

    public AppBuilder() {

    }

    public AppBuilder addEditStatusView() {
        EditStatusViewModel model = new EditStatusViewModel();
        this.editStatusView = new EditStatusView(model);
        return this;
    }

    public AppBuilder addPlayerEventsView() {
        playerEventsViewModel = new PlayerEventsViewModel();
        this.playerEventsView = new PlayerEventsView(playerEventsViewModel);
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

        // configure view
        uradoraDisplayView = new TileDisplayView(model);
        uradoraDisplayView.setClearTilesController(clearController);
        uradoraDisplayView.setRemoveTileController(removeController);
        return this;
    }

    public AppBuilder addTileSelectorView() {
        // instantiate view model
        TileSelectorViewModel model = new TileSelectorViewModel();

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
        UpdateEnabledTilePresenter updateEnabledTilePresenter = new UpdateEnabledTilePresenter(model);
        UpdateEnabledTileInteractor updateEnabledTileInteractor =
                new UpdateEnabledTileInteractor(DAO, updateEnabledTilePresenter);
        UpdateEnabledTileController updateEnabledTileController =
                new UpdateEnabledTileController(updateEnabledTileInteractor);

        // configure selector
        tileSelectorView = new TileSelectorView(model);
        tileSelectorView.setAddToHandController(handController);
        tileSelectorView.setAddToDoraController(doraController);
        tileSelectorView.setAddToUradoraController(uradoraController);
        tileSelectorView.setUpdateEnabledTileController(updateEnabledTileController);

        // configure update notification event
        TileSelectorPropertyUpdateNotifier notifier = new TileSelectorPropertyUpdateNotifier(model);
        handDisplayView.setNotifier(notifier);
        doraDisplayView.setNotifier(notifier);
        uradoraDisplayView.setNotifier(notifier);
        editStatusView.setNotifier(notifier);

        // configure change player (for the player events panel)
        ChangePlayerPresenter changePlayerPresenter = new ChangePlayerPresenter(playerEventsViewModel, model);
        ChangePlayerInteractor changePlayerInteractor = new ChangePlayerInteractor(DAO, changePlayerPresenter);
        ChangePlayerController changePlayerController = new ChangePlayerController(changePlayerInteractor);
        playerEventsView.setChangePlayerController(changePlayerController);

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
