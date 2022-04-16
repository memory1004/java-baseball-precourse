package baseball;

import camp.nextstep.edu.missionutils.Console;
import java.util.NoSuchElementException;

/**
 * 사용자 클래스 생성
 */
public final class User {

    /**
     * 기본 생성자를 외부에서 생성하지 못하도록 막음
     */
    private User() {
    }

    /**
     * 정적 팩토리메소드 제공
     *
     * @return User 생성하여 전달
     */
    public static User getInstance() {
        return new User();
    }

    /**
     * 사용자의 입력값을 전달
     *
     * @return scanner 입력값 전달
     */
    public String getInputValue() {
        try {
            return Console.readLine();
        } catch (NoSuchElementException nse) {
            return null;
        }
    }
}
