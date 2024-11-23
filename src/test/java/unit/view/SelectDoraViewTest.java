package unit.view;

import data_access.InMemoryUniversalDataAccessObject;
import interface_adapter.edit_tiles.AddRemoveTilePresenter;
import interface_adapter.edit_tiles.AddTileController;
import interface_adapter.edit_tiles.SelectDoraController;
import interface_adapter.edit_tiles.TileSelectorViewModel;
import usecase.add_tile.AddTileInteractor;
import view.TileSelectorView;

import javax.swing.*;

public class SelectDoraViewTest {
    public static void main(String[] args) {
        TileSelectorViewModel tileSelectorViewModel = new TileSelectorViewModel();

        TileSelectorView tileSelectorView = new TileSelectorView(tileSelectorViewModel);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(tileSelectorView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
