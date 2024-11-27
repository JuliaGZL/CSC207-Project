package interface_adapter.edit_status;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Edit Status View.
 */
public class EditStatusViewModel extends ViewModel<EditStatusState> {

    public static final String[] TILE_TYPES = {"Hand", "Chii", "Pon", "Kan", "Ankan", "Dora", "Uradora", "Akadora"};
    public static final String[] WIN_TYPES = {"Tsumo", "Ron"};
    public static final String[] WINDS = {"Ton", "Nan", "Shaa", "Pei"};
    public static final String[] ATTRIBUTES = {"Riichi", "Double Riichi", "One-shot", "Under the Sea",
            "Under the River", "After a Kan", "Robbing a Kan", "Tenhou", "Chiihou"};

    public static final int RIICHI_INDEX = 0;
    public static final int DOUBLE_RIICHI_INDEX = 1;
    public static final int ONE_SHOT_INDEX = 2;
    public static final int UNDER_THE_SEA_INDEX = 3;
    public static final int UNDER_THE_RIVER_INDEX = 4;
    public static final int AFTER_A_KAN_INDEX = 5;
    public static final int ROBBING_A_KAN_INDEX = 6;
    public static final int TENHOU_INDEX = 7;
    public static final int CHIIHOU_INDEX = 8;

    public EditStatusViewModel() {
        super("edit status");
        setState(new EditStatusState());
    }
}
