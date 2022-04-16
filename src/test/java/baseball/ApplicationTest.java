package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {


    @Test
    void 서로다른수열테스트() {
        final List<List<String>> generateNumberList = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            generateNumberList.add(Computer.newInstance().generateNumber());
        }
        for (final List<String> numberList : generateNumberList) {
            for (final String numbers : numberList) {
                assertThat(!numberList.contains(numbers));
            }
        }
    }


    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("189", "597", "135", "108", "509", "504", "1", "2");
                    assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
                },
                108, 3, 108, 5, 8, 9
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1234"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
