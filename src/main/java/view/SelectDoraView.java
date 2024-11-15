package view;

import interface_adapter.edit_status.EditStatusController;
import interface_adapter.edit_status.SelectDoraController;
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

    private JLabel titleLabel = new JLabel("Select Dora Indicators");

    private JPanel doraIndicatorPanel;
    private JButton[] tileButtons = new JButton[34];
    private JButton confirmButton = new JButton("Confirm");

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
            tileButtons[i - 1] = createImageButton("/mahjong-tiles/Man" + i + ".png");
        }
        for (int i = 1; i < 10; i++) {
            tileButtons[i + 8] = createImageButton("/mahjong-tiles/Pin" + i + ".png");
        }
        for (int i = 1; i < 10; i++) {
            tileButtons[i + 17] = createImageButton("/mahjong-tiles/Sou" + i + ".png");
        }
        tileButtons[27] = createImageButton("/mahjong-tiles/Ton.png");
        tileButtons[28] = createImageButton("/mahjong-tiles/Nan.png");
        tileButtons[29] = createImageButton("/mahjong-tiles/Shaa.png");
        tileButtons[30] = createImageButton("/mahjong-tiles/Pei.png");
        tileButtons[31] = createImageButton("/mahjong-tiles/Haku.png");
        tileButtons[32] = createImageButton("/mahjong-tiles/Hatsu.png");
        tileButtons[33] = createImageButton("/mahjong-tiles/Chun.png");

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
        Image image = (new ImageIcon(imageURL)).getImage().getScaledInstance(60, 80, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);

        // Create the button with the icon and size
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(60, 80));

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
