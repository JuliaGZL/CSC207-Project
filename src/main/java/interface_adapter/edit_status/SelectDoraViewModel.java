package interface_adapter.edit_status;

import interface_adapter.ViewModel;

public class SelectDoraViewModel extends ViewModel<SelectDoraState> {

    public static final String MAN_PATH = "/mahjong-tiles/Man";
    public static final String PIN_PATH = "/mahjong-tiles/Pin";
    public static final String SOU_PATH = "/mahjong-tiles/Sou";
    public static final String TON_PATH = "/mahjong-tiles/Ton.png";
    public static final String NAN_PATH = "/mahjong-tiles/Nan.png";
    public static final String SHAA_PATH = "/mahjong-tiles/Shaa.png";
    public static final String PEI_PATH = "/mahjong-tiles/Pei.png";
    public static final String HAKU_PATH = "/mahjong-tiles/Haku.png";
    public static final String HATSU_PATH = "/mahjong-tiles/Hatsu.png";
    public static final String CHUN_PATH = "/mahjong-tiles/Chun.png";

    public static final int TILE_WIDTH = 60;
    public static final int TILE_HEIGHT = 80;

    public static final String TITLE_LABEL = "Select Dora Indicators";
    public static final String CONFIRM_BUTTON_LABEL = "Confirm";

    public SelectDoraViewModel() {
        super("select dora");
        setState(new SelectDoraState());
    }
}
