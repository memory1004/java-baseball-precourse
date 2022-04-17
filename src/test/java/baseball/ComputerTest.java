package baseball;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ComputerTest {

    private static final List<List<String>> generateNumberList = new LinkedList<>();

    @BeforeEach
    public void init(){
        for (int i = 0; i < 100; i++) {
            generateNumberList.add(Computer.newInstance().generateNumber());
        }
    }

    @Test
    @DisplayName(value = "같은 인스턴스가 반환되는지 테스트 ")
    public void sameInstanceTest() {
        Computer oldComputer = Computer.newInstance();
        for (int i = 0; i < 100; i++) {
            Computer newComputer = Computer.newInstance();
            assertThat(oldComputer)
                    .isExactlyInstanceOf(Computer.class)
                    .isEqualTo(newComputer);

        }
    }

    @Test
    @DisplayName(value = "값 포함 테스트")
    public void hasElementTest() {
        for (final List<String> numberList : generateNumberList) {
            assertThat(generateNumberList)
                    .contains(numberList);
            for (final String number : numberList) {
                assertThat(numberList)
                        .isNotEmpty()
                        .contains(number);
            }
        }
    }


    @Test
    @DisplayName(value = "중복된 원소 테스트")
    public void duplicateTest() {
        for (final List<String> numberList : generateNumberList) {
            for (final String number : numberList) {
                assertThat(numberList.stream().filter(x -> x.equals(number)).count() > 1).isFalse();
            }
        }
    }

}
