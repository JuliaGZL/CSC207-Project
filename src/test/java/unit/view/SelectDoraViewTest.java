package unit.view;

import interface_adapter.edit_tiles.AddTileController;
import interface_adapter.edit_tiles.SelectDoraController;
import interface_adapter.edit_tiles.SelectDoraViewModel;
import view.SelectTileView;

import javax.swing.*;

public class SelectDoraViewTest {
    public static void main(String[] args) {
        SelectDoraViewModel selectDoraViewModel = new SelectDoraViewModel();
        SelectDoraController selectDoraController = new SelectDoraController();
        AddTileController addTileController = new AddTileController();
        SelectTileView selectTileView = new SelectTileView(selectDoraViewModel, selectDoraController, addTileController);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(selectTileView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
