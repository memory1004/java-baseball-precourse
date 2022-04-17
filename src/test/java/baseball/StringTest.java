package baseball;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringTest {

    @Test
    @DisplayName("1,2를 Split하여 1과2가 모두 포함되었는지 테스트")
    public void splitContainsTest() {
        final String splitText = "1,2";
        final String[] splitTextArr = splitText.split(",");
        assertThat(splitTextArr).containsExactly("1", "2");
    }

    @Test
    @DisplayName("1를 Split하여 1이 배열로 잘 출력됐는지 확인")
    public void splitSameTypeTest() {
        final String splitText = "1";
        final String[] splitTextArr = splitText.split(",");
        assertThat(splitTextArr)
                .containsExactly("1")
                .isEqualTo(new String[]{splitText});
    }

    @Test
    @DisplayName("substring을 활용하여 괄호안의 숫자 확인")
    public void substring() {
        final String text = "(1,2)";
        assertThat(text.substring(1, 4))
                .isEqualTo("1,2");
    }

    @Test
    @DisplayName("charAt를 활용해 특정문자 출력")
    public void charAt() {
        final String text = "abc";
        for (int i = 0; i < text.length(); i++) {
            assertThat(text.charAt(i))
                    .isEqualTo(text.charAt(i));
        }
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> text.charAt(text.length()))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .withMessageContaining("index out of range");
    }
}
