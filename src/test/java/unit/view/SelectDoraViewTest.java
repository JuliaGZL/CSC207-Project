package unit.view;

import data_access.InMemoryUniversalDataAccessObject;
import interface_adapter.edit_tiles.AddRemoveTilePresenter;
import interface_adapter.edit_tiles.AddTileController;
import interface_adapter.edit_tiles.SelectDoraController;
import interface_adapter.edit_tiles.TileSelectorViewModel;
import use_case.add_tile.AddTileInteractor;
import view.TileSelectorView;

import javax.swing.*;

public class SelectDoraViewTest {
    public static void main(String[] args) {
        TileSelectorViewModel tileSelectorViewModel = new TileSelectorViewModel();
        SelectDoraController selectDoraController = new SelectDoraController();

        AddTileInteractor interactor = new AddTileInteractor(
                new InMemoryUniversalDataAccessObject(), new AddRemoveTilePresenter()
        );
        AddTileController addTileController = new AddTileController(interactor);

        TileSelectorView tileSelectorView = new TileSelectorView(tileSelectorViewModel, selectDoraController, addTileController);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(tileSelectorView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
