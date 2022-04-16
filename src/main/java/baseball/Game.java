package baseball;

import java.util.List;

/**
 * 게임을 담당하는 클래스 생성
 */
public final class Game {


    private static final String END_GAME_CLOSING_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료\n게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private static final String OVER_INPUT_NUMBER = "숫자는 3자리만 입력가능합니다.";
    private static final String ONLY_INPUT_NUMBER = "숫자만 입력가능합니다.";
    private static final String INPUT_NUMBER_MESSSAGE = "숫자를 입력해주세요 : ";
    /**
     * 게임내에서 사용될 컴퓨터객체생성
     */
    private final Computer computer = Computer.newInstance();
    /**
     * 게임을 플레이할 사용자 선언
     */
    private final User player;
    /**
     * 이 게임의 낫싱카운트 저장변수
     */
    private int nothingCount = 0;
    /**
     * 이 게임의 스트라이크 저장변수
     */
    private int strikeCount = 0;
    /**
     * 이 게임의 볼 저장변수
     */
    private int ballCount = 0;
    /**
     * 이번 게임의 컴퓨터생성 변수 저장공간
     */
    private List<String> generatedValue;

    /**
     * 사용자를 입력받아 게임을 초기화하는 생성자
     * @param player
     */
    private Game(User player) {
        this.player = player;
        // 새로운 게임이 시작되면 컴퓨터생성 변수를 초기화
        reGenerateValue();
    }

    /**
     * 정적 팩토리 메서드 제공
     * @param user 게임 플레이어
     * @return 이번회차 게임객체 리턴
     */
    public static Game getInstance(final User user) {
        return new Game(user);
    }

    /**
     * 게임시작
     */
    public void startGame() {
        System.out.println(generatedValue);
        System.out.print(INPUT_NUMBER_MESSSAGE);
        final String inputValue = player.getInputValue();
        validInputValue(inputValue);
        processGame(inputValue, generatedValue);
        printResult();
        if ( !getResult()){
            startGame();
            return;
        }
        endGame();
    }

    /**
     * 컴퓨터의 난수 신규생성
     */
    private void reGenerateValue(){
        this.generatedValue = computer.generateNumber();
    }

    /**
     * 게임 종료로직
     */
    public void endGame() {
        System.out.println(END_GAME_CLOSING_MESSAGE);
        final String inputValue = player.getInputValue();
        if ( "1".equals(inputValue)){
            reGenerateValue();
            startGame();
        }
    }

    /**
     * 사용자의 입력 값 검증
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
     * @param inputNumber 사용자의 입력 값
     * @param computerNumber 컴퓨터가 생성한 값
     */
    private void processGame(String inputNumber, List<String> computerNumber) {
        this.nothingCount = 0;
        this.strikeCount = 0;
        this.ballCount = 0;
        final String[] seperatedInputNumer = inputNumber.split("");
        for (int i = 0; i < inputNumber.length(); i++) {
            if (!computerNumber.contains(seperatedInputNumer[i])) {
                this.nothingCount++;
                continue;
            }
            if (computerNumber.get(i).indexOf(seperatedInputNumer[i]) == 0) {
                this.strikeCount++;
                continue;
            }
            this.ballCount++;
        }
    }

    /**
     * 이번 게임의 결과를 출력
     */
    public void printResult(){
        if (this.nothingCount > 0 && this.ballCount == 0 && this.strikeCount == 0) {
            System.out.print("낫싱");
        }

        if (this.ballCount > 0) {
            System.out.print(this.ballCount + "볼 ");
        }

        if (this.strikeCount > 0) {
            System.out.print(strikeCount + "스트라이크");
        }
        System.out.println("");
    }

    /**
     * 이번 게임의 결과값을 반환
     * @return
     */
    public boolean getResult(){
        if (this.strikeCount == 3) {
            return true;
        }

        return false;
    }
}
