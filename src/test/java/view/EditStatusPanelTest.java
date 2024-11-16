package view;

import interface_adapter.edit_status.EditStatusController;
import interface_adapter.edit_status.EditStatusViewModel;

import javax.swing.*;

public class EditStatusPanelTest {
    public static void main(String[] args) {
        EditStatusViewModel editStatusViewModel = new EditStatusViewModel();
        EditStatusController editStatusController = new EditStatusController();
        EditStatusView editStatusView = new EditStatusView(editStatusViewModel, editStatusController);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(editStatusView);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
