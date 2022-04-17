package baseball.model;

public class Game {

    private int nothingCount;
    private int strikeCount;
    private int ballCount;
    private static final int CLEAR_STRIKE_COUNT = 3;

    private Game() {
        initGameResult();
    }

    public static Game getNewInstance() {
        return new Game();
    }

    public void incrementNothingCount() {
        this.nothingCount++;
    }

    public void incrementStrikeCount() {
        this.strikeCount++;
    }

    public void incrementBallCount() {
        this.ballCount++;
    }

    public int getNothingCount() {
        return nothingCount;
    }

    public int getStrikeCount() {
        return strikeCount;
    }

    public int getBallCount() {
        return ballCount;
    }

    public boolean isSuccess() {
        return getStrikeCount() == CLEAR_STRIKE_COUNT;
    }

    public void initGameResult() {
        this.nothingCount = 0;
        this.ballCount = 0;
        this.strikeCount = 0;
    }
}
