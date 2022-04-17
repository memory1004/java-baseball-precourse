package baseball;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

/**
 * 컴퓨터의 역할을 담당할 클래스 생성
 */
public final class Computer {
    /**
     * 숫자 생성 최소 범위
     */
    private static final int START_NUM = 100;
    /**
     * 숫자 생성 최대 범위
     */
    private static final int LAST_NUM = 999;

    /**
     * 기본 생성자를 외부에서 생성하지 못하도록 막음
     */
    private Computer() {
    }

    /**
     *
     */
    public static class ComputerInstanceHolder {
        private static final Computer INSTANCE = new Computer();
    }

    /**
     * 정적 팩토리 메서드 제공
     *
     * @return
     */
    public static Computer newInstance() {
        return ComputerInstanceHolder.INSTANCE;
    }

    /**
     * 랜덤하며 중복되지 않는 난수의 3자리 목록생성
     */
    public List<String> generateNumber() {
        int generatedNumber = Randoms.pickNumberInRange(START_NUM, LAST_NUM);
        List<String> numberList = new ArrayList<>();
        final String[] numberToString = String.valueOf(generatedNumber).split("");
        for (final String numberStr : numberToString) {
            checkDuplicateAdd(numberList, numberStr);
        }
        if (numberList.size() < 3) {
            return generateNumber();
        }
        return numberList;
    }

    private void checkDuplicateAdd(final List<String> source, final String target) {
        if (!source.contains(target)) {
            source.add(target);
        }
    }

}
