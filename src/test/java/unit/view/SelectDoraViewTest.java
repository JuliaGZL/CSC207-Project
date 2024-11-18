package unit.view;

import interface_adapter.edit_status.SelectDoraController;
import interface_adapter.edit_status.SelectDoraViewModel;
import view.SelectDoraView;

import javax.swing.*;

public class SelectDoraViewTest {
    public static void main(String[] args) {
        SelectDoraViewModel selectDoraViewModel = new SelectDoraViewModel();
        SelectDoraController selectDoraController = new SelectDoraController();
        SelectDoraView selectDoraView = new SelectDoraView(selectDoraViewModel, selectDoraController);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(selectDoraView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
