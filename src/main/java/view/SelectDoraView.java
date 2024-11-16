package view;

import interface_adapter.edit_status.EditStatusController;
import interface_adapter.edit_status.SelectDoraController;
import interface_adapter.edit_status.SelectDoraState;
import interface_adapter.edit_status.SelectDoraViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;

public class SelectDoraView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "select dora indicators";
    private final SelectDoraViewModel selectDoraViewModel;

    private JLabel titleLabel = new JLabel(SelectDoraViewModel.TITLE_LABEL);

    private JPanel doraIndicatorPanel;
    private JButton[] tileButtons = new JButton[34];
    private JButton confirmButton = new JButton(SelectDoraViewModel.CONFIRM_BUTTON_LABEL);

    private SelectDoraController selectDoraController;

    public SelectDoraView(SelectDoraViewModel selectDoraViewModel, SelectDoraController selectDoraController) {
        this.selectDoraViewModel = selectDoraViewModel;
        this.selectDoraController = selectDoraController;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Configure doraIndicatorPanel
        doraIndicatorPanel = new JPanel();
        doraIndicatorPanel.setLayout(new GridLayout(4, 9));

        // Configure style of the title label
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Create the buttons for the dora indicators
        for (int i = 1; i < 10; i++) {
            tileButtons[i - 1] = createImageButton(SelectDoraViewModel.MAN_PATH + i + ".png");
        }
        for (int i = 1; i < 10; i++) {
            tileButtons[i + 8] = createImageButton(SelectDoraViewModel.PIN_PATH + i + ".png");
        }
        for (int i = 1; i < 10; i++) {
            tileButtons[i + 17] = createImageButton(SelectDoraViewModel.SOU_PATH + i + ".png");
        }
        tileButtons[27] = createImageButton(SelectDoraViewModel.TON_PATH);
        tileButtons[28] = createImageButton(SelectDoraViewModel.NAN_PATH);
        tileButtons[29] = createImageButton(SelectDoraViewModel.SHAA_PATH);
        tileButtons[30] = createImageButton(SelectDoraViewModel.PEI_PATH);
        tileButtons[31] = createImageButton(SelectDoraViewModel.HAKU_PATH);
        tileButtons[32] = createImageButton(SelectDoraViewModel.HATSU_PATH);
        tileButtons[33] = createImageButton(SelectDoraViewModel.CHUN_PATH);

        // Add the buttons to the panel
        for (JButton button : tileButtons) {
            doraIndicatorPanel.add(button);
        }

        // Add all components to the panel
        this.add(titleLabel);
        this.add(doraIndicatorPanel);
        this.add(confirmButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    /**
     * Creates a button with the appearance of the image under imagePath.
     * @param imagePath the path of the image to be displayed on the button
     * @return the button with the image
     */
    private JButton createImageButton(String imagePath) {
        // Load the image from the resources directory
        URL imageURL = getClass().getResource(imagePath);
        if (imageURL == null) {
            throw new IllegalArgumentException("Image not found: " + imagePath);
        }

        // Dimension of original image is 300 x 400
        // Resize the image to 60 x 80
        Image image = (new ImageIcon(imageURL)).getImage().getScaledInstance(SelectDoraViewModel.TILE_WIDTH,
                SelectDoraViewModel.TILE_HEIGHT, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);

        // Create the button with the icon and size
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(SelectDoraViewModel.TILE_WIDTH, SelectDoraViewModel.TILE_HEIGHT));

        // Remove button's default look
        button.setBorderPainted(true); // Remove border
        button.setFocusPainted(true);  // Remove focus border
        button.setContentAreaFilled(false); // Make the background transparent

        return button;
    }

    public String getViewName() {
        return viewName;
    }

    public void setSelectDoraController(SelectDoraController selectDoraController) {
        this.selectDoraController = selectDoraController;
    }
}
