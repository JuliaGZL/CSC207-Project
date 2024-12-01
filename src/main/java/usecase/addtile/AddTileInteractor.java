package usecase.addtile;

import entity.Player;
import entity.Tile;
import java.util.ArrayList;
import java.util.List;
import mahjong.BaseTile;
import mahjong.BaseTileToPathMapping;

/**
 * Interactor for the add_tile use case.
 */
public class AddTileInteractor implements AddTileInputBoundary {
  // Where to add the tile - hand, dora or uradora
  public static final int HAND = 1;
  public static final int DORA = 2;
  public static final int URADORA = 3;
  private final int target;

  private final AddTileDataAccessInterface dataAccessObj;
  private final AddTileOutputBoundary presenter;

  /**
   * Constructs an AddTileInteractor.
   *
   * @param dataAccessObj the data access object
   * @param presenter the presenter
   * @param target the target (HAND, DORA, or URADORA)
   */
  public AddTileInteractor(AddTileDataAccessInterface dataAccessObj,
      AddTileOutputBoundary presenter,
      int target) {
    this.dataAccessObj = dataAccessObj;
    this.presenter = presenter;
    this.target = target;
  }

  /**
   * Executes the add_tile use case.
   *
   * @param inputData the input data
   */
  @Override
  public void execute(AddTileInputData inputData) {
    final String name = inputData.getPlayerName();
    final BaseTile id = inputData.getTileId();
    if (!dataAccessObj.existsByName(name)) {
      // This should never happen!
      throw new RuntimeException("Player name not found!");
    } else {
      final Player player = dataAccessObj.getPlayer(name);
      List<Tile> tileList;
      switch (target) {
        case HAND:
          tileList = player.getHand();
          break;
        case DORA:
          tileList = player.getDora();
          break;
        case URADORA:
          tileList = player.getUradora();
          break;
        default:
          throw new IllegalArgumentException("Invalid target: " + target);
      }
      addTile(id, tileList, player, name, target);
    }
  }

  private void addTile(BaseTile id, List<Tile> tileList,
      Player player, String name, int target) {
    final Tile newTile = new Tile(id);
    tileList.add(newTile);

    // add to hand, dora or uradora according to target
    switch (target) {
      case HAND:
        player.setHand(tileList);
        break;
      case DORA:
        player.setDora(tileList);
        break;
      case URADORA:
        player.setUradora(tileList);
        break;
      default:
        throw new IllegalArgumentException("Invalid target: " + target);
    }
    dataAccessObj.savePlayer(player);

    final List<BaseTile> idList = new ArrayList<BaseTile>();
    final List<String> nameList = new ArrayList<String>();
    final List<String> iconList = new ArrayList<String>();
    for (Tile tile : tileList) {
      idList.add(tile.getBaseTile());
      nameList.add(tile.toString());
      iconList.add(BaseTileToPathMapping.getTilePath(tile.getBaseTile()));
    }
    final AddTileOutputData output = new AddTileOutputData(false, name, idList, nameList, iconList);

    presenter.prepareSuccessView(output);
  }
}
