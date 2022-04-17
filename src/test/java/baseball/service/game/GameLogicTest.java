package baseball.service.game;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.io.ByteArrayInputStream;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameLogicTest extends NsTest {

    private static final GameLogic game = GameLogic.getInstance();


    @Test
    @DisplayName(value = "입력 값 검증 - 정상값")
    public void inputValueTest() {
        input("418");
        final String inputValue = game.getInputValue();
        assertThat(inputValue).isNotEmpty().isNotBlank().isEqualTo("418");
    }

    @Test
    @DisplayName(value = "입력 값 검증 - 오류값 ")
    public void inputValueErrorTest() {
        input("398234");
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
            game.getInputValue()).isInstanceOf(IllegalArgumentException.class).withMessageContaining("3자리만");
    }

    @Test
    @DisplayName(value = "게임 결과 출력 테스트 - (2볼 1스트라이크)")
    public void printInputValueTest1() {
        input("839");
        final String inputValue = game.getInputValue();
        game.processGame(inputValue, Lists.newArrayList("8","9","3"));
        game.printResult();
        assertThat(game.getResult()).isFalse();
        assertThat(output()).isEqualTo("2볼 1스트라이크");
    }

    @Test
    @DisplayName(value = "게임 결과 출력 테스트 - (2스트라이크)")
    public void printInputValueTest2() {
        input("893");
        final String inputValue = game.getInputValue();
        game.processGame(inputValue, Lists.newArrayList("8","9","1"));
        game.printResult();
        assertThat(game.getResult()).isFalse();
        assertThat(output()).isEqualTo("2스트라이크");
    }

    @Test
    @DisplayName(value = "게임 결과 출력 테스트 - (낫싱)")
    public void printInputValueTest3() {
        input("246");
        final String inputValue = game.getInputValue();
        game.processGame(inputValue, Lists.newArrayList("1","3","5"));
        game.printResult();
        assertThat(game.getResult()).isFalse();
        assertThat(output()).isEqualTo("낫싱");
    }

    @Test
    @DisplayName(value = "게임 결과 출력 테스트 - (3스트라이크)")
    public void printInputValueTest4() {
        input("968");
        final String inputValue = game.getInputValue();
        game.processGame(inputValue, Lists.newArrayList("9","6","8"));
        game.printResult();
        assertThat(game.getResult()).isTrue();
        assertThat(output()).isEqualTo("3스트라이크");
    }

    private void input(final String inputValue){
        final byte[] buf = String.join("\n", inputValue).getBytes();
        System.setIn(new ByteArrayInputStream(buf));
    }

    @Override
    protected void runMain() {

    }
}
