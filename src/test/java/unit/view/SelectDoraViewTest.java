package unit.view;

import interface_adapter.edit_tiles.AddTileController;
import interface_adapter.edit_tiles.SelectDoraController;
import interface_adapter.edit_tiles.TileSelectorViewModel;
import view.TileSelectorView;

import javax.swing.*;

public class SelectDoraViewTest {
    public static void main(String[] args) {
        TileSelectorViewModel tileSelectorViewModel = new TileSelectorViewModel();
        SelectDoraController selectDoraController = new SelectDoraController();
        AddTileController addTileController = new AddTileController();
        TileSelectorView tileSelectorView = new TileSelectorView(tileSelectorViewModel, selectDoraController, addTileController);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(tileSelectorView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
