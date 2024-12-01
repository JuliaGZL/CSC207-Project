package usecase.hu_solver;

import entity.Player;
import entity.PlayerStats;
import entity.PlayerStatsBuilder;
import mahjong.BaseTile;
import mahjong.HandResult;

/**
 * Interactor for the Hu solver.
 */
public class HuSolverInteractor implements HuSolverInputBoundary {
    private final HuSolverDataAccessInterface dataAccessObj;
    private final HuSolveOutputBoundary presenter;

    public HuSolverInteractor(HuSolverDataAccessInterface dataAccessObj, HuSolveOutputBoundary presenter) {
        this.dataAccessObj = dataAccessObj;
        this.presenter = presenter;
    }

    /**
     * Executes the add_tile use case.
     *
     * @param inputData the input data
     */
    @Override
    public void execute(HuSolverInputData inputData) {
        // get attributes
        Player player = dataAccessObj.getPlayer(inputData.getPlayerName());

        // if hand not full, fails.
        if (player.getHand().size() != 14) {
            presenter.prepareFailView("Hand must contain 14 tiles!");
            return;
        }

        Boolean[] attributes = player.getAttributes();
        boolean isTsumo = (player.getWinType().equals("Tsumo"));
        boolean isOya = (player.getSeatWind().equals("East"));

        // get wind
        BaseTile selfWind = getWindBaseTile(player.getSeatWind());
        BaseTile prevalentWind = getWindBaseTile(player.getRoundWind());

        // build stats
        PlayerStats stats = new PlayerStatsBuilder().setHand(player.getHand())
                .setDoraList(player.getDora())
                .setUraDoraList(player.getUradora())
                .setRiichi(attributes[0])
                .setDoubleRiichi(attributes[1])
                .setIppatsu(attributes[2])
                .setHaitei(attributes[3])
                .setHoutei(attributes[4])
                .setRinshan(attributes[5])
                .setChankan(attributes[6])
                .setTenhou(attributes[7])
                .setChiihou(attributes[8])
                .setTsumo(isTsumo)
                .setOya(isOya)
                .setSelfWind(selfWind)
                .setPrevalentWind(prevalentWind)
                .build();

        // get results
        HandResult handResult;
        int score;
        try {
            handResult = HandResult.getInstance(stats);
            score = handResult.getHandScoreResult();
        } catch (RuntimeException e) {
            presenter.prepareFailView(e.getMessage());
            return;
        }
        if (score == 0) {
            presenter.prepareFailView("Current hand is not valid for Hu!");
        } else {
            StringBuilder messageBuilder = new StringBuilder();
            for (String s : handResult.displayHandResult()) {
                messageBuilder.append(s).append("\n");
            }
            String message = messageBuilder.toString();
            dataAccessObj.savePlayer(player);
            presenter.prepareSuccessView(new HuSolverOutputData(message, player.getScore() + score));
        }
    }

    private BaseTile getWindBaseTile(String wind) {
        BaseTile windBaseTile = BaseTile.windTon;
        switch (wind) {
            case "East":
                break;
            case "West":
                windBaseTile = BaseTile.windShaa;
                break;
            case "North":
                windBaseTile = BaseTile.windPei;
                break;
            case "South":
                windBaseTile = BaseTile.windNan;
                break;
        }
        return windBaseTile;
    }
}
