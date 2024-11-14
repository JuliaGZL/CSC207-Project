package view;

import interface_adapter.table_center.EditStatusController;
import interface_adapter.table_center.EditStatusViewModel;

import javax.swing.*;

public class EditStatusPanelTest {
    public static void main(String[] args) {
        EditStatusViewModel editStatusViewModel = new EditStatusViewModel();
        EditStatusController editStatusController = new EditStatusController();
        EditStatusPanel editStatusPanel = new EditStatusPanel(editStatusViewModel, editStatusController);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(editStatusPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
