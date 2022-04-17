package baseball;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SetTest {
    private Set<Integer> numberSet;

    @BeforeEach
    void init() {
        numberSet = new HashSet<>();
        numberSet.add(1);
        numberSet.add(3);
        numberSet.add(1);
        numberSet.add(2);
        numberSet.add(4);
        numberSet.add(2);
        numberSet.add(5);
        numberSet.add(6);
        numberSet.add(7);
    }

    @Test
    @DisplayName("Set크기 확인")
    public void checkSetSize() {
        assertThat(numberSet).hasSize(7);
        assertThat(numberSet).hasSize(numberSet.size());
        assertThat(numberSet).size().isGreaterThanOrEqualTo(numberSet.size());
        assertThat(numberSet).size().isLessThanOrEqualTo(9);
    }

    @Test
    @DisplayName("Set포함 확인")
    public void includingSetValue() {
        assertThat(numberSet.contains(1)).isTrue();
        assertThat(numberSet.contains(2)).isTrue();
        assertThat(numberSet.contains(102)).isFalse();
    }

    @ParameterizedTest
    @DisplayName("Set포함 확인(Use Parameterized)")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7})
    public void includingSetValueWithParameterized(Integer number) {
        assertThat(numberSet.contains(number)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("Set 포함/미포함 확인(Use Parameterized)")
    @ValueSource(ints = {1, 2, 313, 44, 512, 67, 71, 8, 9, 10, 102, 302})
    public void includingSetValueWithParameterized2(int actual) {
        boolean isEqualValue = numberSet.contains(actual);
        assertThat(isEqualValue).isEqualTo(numberSet.contains(actual));

    }

    @ParameterizedTest
    @DisplayName("csv형식의 입력소스 검사")
    @CsvSource(value = {"test:test", "tEst:test", "Java:java"}, delimiter = ':')
    public void includingSetValueWithParameterized2(String actual, String expected) {
        boolean isEqualValue = actual.equals(expected);
        assertThat(isEqualValue).isEqualTo(actual.equals(expected));

    }




}
