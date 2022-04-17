package baseball.service.game;

public class GameLauncher {

    private final static GameLauncher INSTANCE = new GameLauncher();

    private GameLauncher() {

    }

    public static GameLauncher getInstance() {
        return INSTANCE;
    }

    public void run() {
        GameLogic.getInstance().startGame();
    }

}
