package app;

import data_access.InMemoryUniversalDataAccessObject;
import entity.PlayerFactory;
import entity.Tile;
import interface_adapter.edit_status.EditStatusViewModel;
import interface_adapter.edit_tiles.*;
import mahjong.BaseTile;
import usecase.add_tile.AddTileInputBoundary;
import usecase.add_tile.AddTileInteractor;
import usecase.clear_tiles.ClearTilesInputBoundary;
import usecase.clear_tiles.ClearTilesInteractor;
import usecase.clear_tiles.ClearTilesOutputBoundary;
import usecase.remove_tile.RemoveTileInputBoundary;
import usecase.remove_tile.RemoveTileInteractor;
import utils.BaseTileToPathMapping;
import view.EditStatusView;
import view.TileDisplayView;
import view.TileSelectorView;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

// TODO: this is a very initial version.
public class AppBuilder {
    // data access
    private final InMemoryUniversalDataAccessObject DAO = new InMemoryUniversalDataAccessObject();

    // views
    private EditStatusView editStatusView;
    private TileDisplayView handDisplayView;
    private TileDisplayView doraDisplayView;
    private TileSelectorView tileSelectorView;

    // shared presenters
    private AddRemoveTilePresenter handPresenter;
    private AddRemoveTilePresenter doraPresenter;

    // factory
    private PlayerFactory playerFactory = new PlayerFactory();

    public AppBuilder() {

    }

    public AppBuilder addEditStatusView() {
        EditStatusViewModel model = new EditStatusViewModel();
        // TODO: controller
        this.editStatusView = new EditStatusView(model);
        return this;
    }

    public AppBuilder addHandDisplayView() {
        // instantiate view model
        TilesDisplayViewModel model = new TilesDisplayViewModel("Hand");

        // configure clear tile
        ClearTilesOutputBoundary clearPresenter = new ClearTilePresenter(model);
        ClearTilesInputBoundary clearInteractor = new ClearTilesInteractor(DAO, clearPresenter);
        ClearTilesController clearController = new ClearTilesController(clearInteractor);

        // configure remove tile
        handPresenter = new AddRemoveTilePresenter(model);
        RemoveTileInputBoundary removeInteractor = new RemoveTileInteractor(DAO, handPresenter);
        RemoveTileController removeController = new RemoveTileController(removeInteractor);

        // configure view
        handDisplayView = new TileDisplayView(model);
        handDisplayView.setClearTilesController(clearController);
        handDisplayView.setRemoveTileController(removeController);
        return this;
    }

    public AppBuilder addDoraDisplayView() {
        TilesDisplayViewModel model = new TilesDisplayViewModel("Dora");
        doraDisplayView = new TileDisplayView(model);

        // TODO: FOR TEMPORARY TESTING PURPOSE ONLY
        List<BaseTile> idList = new ArrayList<BaseTile>();
        idList.add(BaseTile._1p);
        List<String> nameList = new ArrayList<String>();
        nameList.add("test");
        List<String> iconList = new ArrayList<String>();
        iconList.add(BaseTileToPathMapping.getTilePath(BaseTile._1p));
        model.getState().setIdList(idList);
        model.getState().setNameList(nameList);
        model.getState().setIconList(iconList);
        model.firePropertyChanged("tiles");
        // TODO: REMOVE THIS LATER

        return this;
    }

    public AppBuilder addTileSelectorView() {
        // TODO: also support selecting dora

        // instantiate view model
        TileSelectorViewModel model = new TileSelectorViewModel();

        // configure add tile
        AddTileInputBoundary interactor = new AddTileInteractor(DAO, handPresenter);
        AddTileController controller = new AddTileController(interactor);

        // configure selector
        tileSelectorView = new TileSelectorView(model);
        tileSelectorView.setAddTileController(controller);
        return this;
    }

    public JFrame build() {
        // TODO: we have to fix this -- it should take BaseTile instead of Tile!
        DAO.savePlayer(playerFactory.create("default", 0, new ArrayList<Tile>()));

        JFrame app = new JFrame();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel appPanel = new JPanel();

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(tileSelectorView);
        leftPanel.add(handDisplayView);
        leftPanel.add(doraDisplayView);

        rightPanel.add(editStatusView);

        appPanel.setLayout(new BoxLayout(appPanel, BoxLayout.X_AXIS));
        appPanel.add(leftPanel);
        appPanel.add(rightPanel);

        app.add(appPanel);
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        return app;
    }
}
