package lotto;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static camp.nextstep.edu.missionutils.test.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";
    private InputStream originalSystemIn;
//    @BeforeEach
//    public void setUp() {
//        originalSystemIn = System.in;
//    }
//    @AfterEach
//    public void tearDown() {
//        System.setIn(originalSystemIn);
//    }
    @Test
    void getBuyMoney_테스트() {
        //given
        String case1 = "8000";
        String case2 = "15000";
        String case3 = "1000";
        InputStream inputStream1 = new ByteArrayInputStream(case1.getBytes());
        InputStream inputStream2 = new ByteArrayInputStream(case2.getBytes());
        InputStream inputStream3 = new ByteArrayInputStream(case3.getBytes());

        //when
        System.setIn(inputStream1);
        int result1 = Application.getBuyMoney();
        System.setIn(inputStream2);
        int result2 = Application.getBuyMoney();
        System.setIn(inputStream3);
        int result3 = Application.getBuyMoney();
        //then
        assertThat(result1).isEqualTo(8000);
        assertThat(result2).isEqualTo(15000);
        assertThat(result3).isEqualTo(1000);
    }

    @Test
    void getBuyMoney_에러상황_테스트() {
        //given
        String case1 = "500";
        String case2 = "8800";
        String case3 = "abc";
        String case4 = "";
        InputStream inputStream1 = new ByteArrayInputStream(case1.getBytes());
        InputStream inputStream2 = new ByteArrayInputStream(case2.getBytes());
        InputStream inputStream3 = new ByteArrayInputStream(case3.getBytes());
        InputStream inputStream4 = new ByteArrayInputStream(case4.getBytes());

        //when
        System.setIn(inputStream1);
        final Throwable thrown1 = catchThrowable(Application::getBuyMoney);
        System.setIn(inputStream2);
        final Throwable thrown2 = catchThrowable(Application::getBuyMoney);
        System.setIn(inputStream3);
        final Throwable thrown3 = catchThrowable(Application::getBuyMoney);
        System.setIn(inputStream4);
        final Throwable thrown4 = catchThrowable(Application::getBuyMoney);

        //then
        assertThat(thrown1)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        assertThat(thrown2)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        assertThat(thrown3)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
//        assertThat(thrown4)
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("[ERROR]");
    }
    @Test
    void getLottoNum_테스트() {
        //given
        //when
        List<Integer> result1 = Application.getLottoNum();
        List<Integer> result2 = Application.getLottoNum();
        List<Integer> result3 = Application.getLottoNum();
        //then
        assertThat(result1).doesNotHaveDuplicates()
                .hasSize(6)
                .allMatch(i -> i>=1 && i<=45)
                .isSorted();
        assertThat(result2).doesNotHaveDuplicates()
                .hasSize(6)
                .allMatch(i -> i>=1 && i<=45)
                .isSorted();
        assertThat(result3).doesNotHaveDuplicates()
                .hasSize(6)
                .allMatch(i -> i>=1 && i<=45)
                .isSorted();
    }
    @Test
    void getWinningNubers_테스트() {
        //given
        String case1 = "1,2,3,4,5,6";
        String case2 = "15,2,34,10,21,6";
        InputStream inputStream1 = new ByteArrayInputStream(case1.getBytes());
        InputStream inputStream2 = new ByteArrayInputStream(case2.getBytes());
        //when
        System.setIn(inputStream1);
        List<Integer> result1 = Application.getWinningNubers();
        System.setIn(inputStream2);
        List<Integer> result2 = Application.getWinningNubers();
        //then
        assertThat(result1).doesNotHaveDuplicates()
                .hasSize(6)
                .containsAll(Arrays.asList(1,2,3,4,5,6));
        assertThat(result2).doesNotHaveDuplicates()
                .hasSize(6)
                .containsAll(Arrays.asList(15,2,34,10,21,6));
    }

    @Test
    void getWinningNubers_예외상황_테스트() {
        //given
        String case1 = "1,1,3,4,5,6";
        String case2 = "0,2,34,10,21,6";
        String case3 = "0,2,34,10,21,90";
        String case4 = "a,2,34,10,21,90";
        String case5 = ",2,34,10,21,90";
        InputStream inputStream1 = new ByteArrayInputStream(case1.getBytes());
        InputStream inputStream2 = new ByteArrayInputStream(case2.getBytes());
        InputStream inputStream3 = new ByteArrayInputStream(case3.getBytes());
        InputStream inputStream4 = new ByteArrayInputStream(case4.getBytes());
        InputStream inputStream5 = new ByteArrayInputStream(case5.getBytes());
        //when
        System.setIn(inputStream1);
        Throwable thrown1 = catchThrowable(Application::getWinningNubers);
        System.setIn(inputStream2);
        Throwable thrown2 = catchThrowable(Application::getWinningNubers);
        System.setIn(inputStream3);
        Throwable thrown3 = catchThrowable(Application::getWinningNubers);
        System.setIn(inputStream4);
        Throwable thrown4 = catchThrowable(Application::getWinningNubers);
        System.setIn(inputStream5);
        Throwable thrown5 = catchThrowable(Application::getWinningNubers);
        //then
        assertThat(thrown1).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        assertThat(thrown2).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        assertThat(thrown3).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        assertThat(thrown4).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        assertThat(thrown5).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void getBonusNumber_테스트() {
        //given
        String case1 = "11";
        String case2 = "39";
        List<Integer> winningNumbers = Arrays.asList(1,2,3,4,5,6);
        InputStream inputStream1 = new ByteArrayInputStream(case1.getBytes());
        InputStream inputStream2 = new ByteArrayInputStream(case2.getBytes());
        //when
        System.setIn(inputStream1);
        int result1 = Application.getBonusNumbers(winningNumbers);
        System.setIn(inputStream2);
        int result2 = Application.getBonusNumbers(winningNumbers);
        //then
        assertThat(result1).isEqualTo(11)
                .isBetween(1, 45);
        assertThat(result2).isEqualTo(39)
                .isBetween(1, 45);
    }

    @Test
    void getBonusNumber_예외상황_테스트() {
        //given
        String case1 = "0";
        String case2 = "a";
        String case3 = "90";
        String case4 = "1";
        List<Integer> winningNumbers = Arrays.asList(1,2,3,4,5,6);
        InputStream inputStream1 = new ByteArrayInputStream(case1.getBytes());
        InputStream inputStream2 = new ByteArrayInputStream(case2.getBytes());
        InputStream inputStream3 = new ByteArrayInputStream(case3.getBytes());
        InputStream inputStream4 = new ByteArrayInputStream(case4.getBytes());
        //when
        System.setIn(inputStream1);
        Throwable thrown1 = catchThrowable(() -> Application.getBonusNumbers(winningNumbers));
        System.setIn(inputStream2);
        Throwable thrown2 = catchThrowable(() -> Application.getBonusNumbers(winningNumbers));
        System.setIn(inputStream3);
        Throwable thrown3 = catchThrowable(() -> Application.getBonusNumbers(winningNumbers));
        System.setIn(inputStream4);
        Throwable thrown4 = catchThrowable(() -> Application.getBonusNumbers(winningNumbers));
        //then
        assertThat(thrown1).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        assertThat(thrown2).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        assertThat(thrown3).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
        assertThat(thrown4).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }

    @Test
    void getResultCategory_and_getLottoResults_테스트() {
        //given
        List<Integer> winningNumbers = Arrays.asList(1,2,3,4,5,6);
        int bonusNumber = 7;
        Lotto lotto1 = new Lotto(Arrays.asList(11,12,13,14,15,7));
        Lotto lotto2 = new Lotto(Arrays.asList(1,12,13,14,15,7));
        Lotto lotto3 = new Lotto(Arrays.asList(1,2,13,14,15,7));
        Lotto lotto4 = new Lotto(Arrays.asList(1,2,3,14,15,7));
        Lotto lotto5 = new Lotto(Arrays.asList(1,2,3,4,15,7));
        Lotto lotto6 = new Lotto(Arrays.asList(1,2,3,4,5,17));
        Lotto lotto7 = new Lotto(Arrays.asList(1,2,3,4,5,7));
        Lotto lotto8 = new Lotto(Arrays.asList(1,2,3,4,5,6));
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(lotto1);
        lottos.add(lotto2);
        lottos.add(lotto3);
        lottos.add(lotto4);
        lottos.add(lotto5);
        lottos.add(lotto6);
        lottos.add(lotto7);
        lottos.add(lotto8);
        List<Integer> resultsOfLottos = Arrays.asList(0,0,0,0,0,0,0,0);
        //when
        int result1 = Application.getResultCategory(lotto1, winningNumbers, bonusNumber);
        int result2 = Application.getResultCategory(lotto2, winningNumbers, bonusNumber);
        int result3 = Application.getResultCategory(lotto3, winningNumbers, bonusNumber);
        int result4 = Application.getResultCategory(lotto4, winningNumbers, bonusNumber);
        int result5 = Application.getResultCategory(lotto5, winningNumbers, bonusNumber);
        int result6 = Application.getResultCategory(lotto6, winningNumbers, bonusNumber);
        int result7 = Application.getResultCategory(lotto7, winningNumbers, bonusNumber);
        int result8 = Application.getResultCategory(lotto8, winningNumbers, bonusNumber);
        resultsOfLottos = Application.getLottosResult(lottos, winningNumbers, bonusNumber);
        //then
        assertThat(result1).isEqualTo(0);
        assertThat(result2).isEqualTo(1);
        assertThat(result3).isEqualTo(2);
        assertThat(result4).isEqualTo(3);
        assertThat(result5).isEqualTo(4);
        assertThat(result6).isEqualTo(5);
        assertThat(result7).isEqualTo(6);
        assertThat(result8).isEqualTo(7);
        assertThat(resultsOfLottos).isEqualTo(Arrays.asList(1,1,1,1,1,1,1,1));
    }

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
