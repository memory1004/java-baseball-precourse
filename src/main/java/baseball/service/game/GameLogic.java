package baseball.service.game;

import baseball.model.Game;
import baseball.service.computer.Computer;
import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 게임을 담당하는 클래스 생성
 */
public final class GameLogic {


    private static final String END_GAME_CLOSING_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료\n게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private static final String OVER_INPUT_NUMBER = "숫자는 3자리만 입력가능합니다.";
    private static final String ONLY_INPUT_NUMBER = "숫자만 입력가능합니다.";
    private static final String INPUT_NUMBER_MESSSAGE = "숫자를 입력해주세요 : ";
    private Game playGame;
    private final Computer computer = Computer.newInstance();
    private List<String> generatedValue;

    private GameLogic() {
        // 새로운 게임이 시작되면 컴퓨터생성 변수를 초기화
        this.playGame = Game.getNewInstance();
        reGenerateValue();
    }

    /**
     * 정적 팩토리 메서드 제공
     *
     * @param user 게임 플레이어
     * @return 이번회차 게임객체 리턴
     */
    public static GameLogic getInstance() {
        return new GameLogic();
    }

    /**
     * 게임시작
     */
    public void startGame() {
        printGameMessage(INPUT_NUMBER_MESSSAGE);
        final String inputValue = getInputValue();
        printGameMessageWithLine(inputValue);
        processGame(inputValue);
        printResult();
        if (!getResult()) {
            startGame();
            return;
        }
        endGame();
    }

    String getInputValue() {
        try {
            final String inputValue = Console.readLine();
            validInputValue(inputValue);
            return inputValue;
        } catch (NoSuchElementException nse) {
            return null;
        }
    }

    /**
     * 컴퓨터의 난수 신규생성
     */
    void reGenerateValue() {
        this.generatedValue = computer.generateNumber();
    }

    /**
     * 게임 종료로직
     */
    void endGame() {
        printGameMessageWithLine(END_GAME_CLOSING_MESSAGE);
        final String inputValue = getInputValue();
        if ("1".equals(inputValue)) {
            reGenerateValue();
            startGame();
        }
    }

    /**
     * 사용자의 입력 값 검증
     *
     * @param string 입력 값
     */
    private void validInputValue(String string) {
        if (string.length() > 3) {
            throw new IllegalArgumentException(OVER_INPUT_NUMBER);
        }
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                throw new IllegalArgumentException(ONLY_INPUT_NUMBER);
            }
        }
    }

    /**
     * 게임 처리 로직
     *
     * @param inputNumber    사용자의 입력 값
     * @param computerNumber 컴퓨터가 생성한 값
     */
    void processGame(final String inputNumber) {
        this.playGame.initGameResult();
        final String[] seperatedInputNumer = inputNumber.split("");
        for (int i = 0; i < inputNumber.length(); i++) {
            if (generatedValue.get(i).indexOf(seperatedInputNumer[i]) == 0) {
                this.playGame.incrementStrikeCount();
                continue;
            }
            if (!generatedValue.contains(seperatedInputNumer[i])) {
                this.playGame.incrementNothingCount();
                continue;
            }
            this.playGame.incrementBallCount();
        }
    }

    /**
     * 사용자가 컴퓨터의 값을 입력한 값으로 게임 진행 시
     * @param inputNumber
     * @param injectComputerValue
     */
    void processGame(final String inputNumber, final List<String> injectComputerValue) {
        this.generatedValue = injectComputerValue;
        processGame(inputNumber);
    }

    /**
     * 이번 게임의 결과를 출력
     */
    void printResult() {
        if (this.playGame.getNothingCount() > 0
                && (this.playGame.getBallCount() + this.playGame.getStrikeCount() == 0)) {
            printGameMessage("낫싱");
        }

        if (this.playGame.getBallCount() > 0) {
            printGameMessage(this.playGame.getBallCount() + "볼 ");
        }

        if (this.playGame.getStrikeCount() > 0) {
            printGameMessage(this.playGame.getStrikeCount() + "스트라이크");
        }
        printGameMessageWithLine("");
    }

    /**
     * 이번 게임의 결과값을 반환
     *
     * @return
     */
    boolean getResult() {
        return playGame.isSuccess();
    }

    void printGameMessageWithLine(final String message) {
        System.out.println(message);
    }

    void printGameMessage(final String message) {
        System.out.print(message);
    }
}
