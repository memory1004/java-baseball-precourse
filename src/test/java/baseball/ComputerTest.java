package baseball;

import java.util.LinkedList;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class ComputerTest {

    @Test
    @DisplayName(value = "중복된 원소 테스트")
    public void duplicateTest() {
        final List<List<String>> generateNumberList = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            generateNumberList.add(Computer.newInstance().generateNumber());
        }
        for (final List<String> numberList : generateNumberList) {
            for (final String number : numberList) {
                assertThat(numberList.stream().filter(x -> x.equals(number)).count() > 1).isFalse();
            }
        }
    }

}
