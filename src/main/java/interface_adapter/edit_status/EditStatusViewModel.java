package interface_adapter.edit_status;

import interface_adapter.ViewModel;

public class EditStatusViewModel extends ViewModel<EditStatusState> {

    public static final String[] ATTRIBUTES = {"Chii", "Pon", "Kan", "Riichi", "Tsumo"};
    public static final String BLANK_MAHJONG_PATH = "/mahjong-tiles/Front.png";

    public static final String MAN_LABEL = "Man";
    public static final String PIN_LABEL = "Pin";
    public static final String SOU_LABEL = "Sou";
    public static final String RED_DORA_LABEL = "Red Dora";
    public static final String SELECT_INDICATOR_LABEL = "Select Dora Indicators";

    public EditStatusViewModel() {
        super("edit status");
        setState(new EditStatusState());
    }

}
