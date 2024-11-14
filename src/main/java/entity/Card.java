package entity;

/**
 * Entity that represents a card.
 *  - id: ID of the card.
 *  - name: Name of the card to be displayed on the GUI.
 *  - iconPath: Path to the icon file to be displayed on the GUI.
 */
public class Card {
    private int id;
    private String name;
    private String iconPath;

    public Card(int id, String displayName, String iconPath) {
        this.id = id;
        this.name = displayName;
        this.iconPath = iconPath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIconPath() {
        return iconPath;
    }
}
