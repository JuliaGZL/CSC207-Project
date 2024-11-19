package app;

import data_access.InMemoryUniversalDataAccessObject;
import interface_adapter.edit_status.EditStatusViewModel;
import interface_adapter.edit_tiles.TileSelectorViewModel;
import interface_adapter.edit_tiles.TilesDisplayViewModel;

import mahjong.BaseTileToPathMapping;
import view.EditStatusView;
import view.TileDisplayView;
import view.TileSelectorView;

import javax.swing.*;

// TODO: TEMPORARY
import mahjong.BaseTile;
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

    public AppBuilder() {

    }

    public AppBuilder addEditStatusView() {
        EditStatusViewModel model = new EditStatusViewModel();
        // TODO: controller
        this.editStatusView = new EditStatusView(model);
        return this;
    }

    public AppBuilder addHandDisplayView() {
        TilesDisplayViewModel model = new TilesDisplayViewModel("Hand");
        handDisplayView = new TileDisplayView(model);

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
        TileSelectorViewModel model = new TileSelectorViewModel();
        tileSelectorView = new TileSelectorView(model);

        return this;
    }

    public JFrame build() {
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
