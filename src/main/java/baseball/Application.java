package baseball;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        final Game game = Game.getInstance(User.getInstance());
        game.startGame();
    }
}
