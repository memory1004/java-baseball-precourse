package baseball;

import baseball.service.game.GameLauncher;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        GameLauncher.getInstance().run();
    }
}
