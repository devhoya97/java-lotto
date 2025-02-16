package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTest {
    @DisplayName("해당 로또가 몇 등짜리인지 반환한다.")
    @Test
    void getPrizeOfLotto() {
        //given
        Lotto.setWinningNumbers(Arrays.asList(1,2,3,4,5,6));
        Lotto.setBonusNumber(7);
        Lotto case1 = new Lotto(Arrays.asList(1,2,3,10,11,12));
        Lotto case2 = new Lotto(Arrays.asList(1,2,3,4,11,12));
        Lotto case3 = new Lotto(Arrays.asList(1,2,3,4,5,12));
        Lotto case4 = new Lotto(Arrays.asList(1,2,3,4,5,7));
        Lotto case5 = new Lotto(Arrays.asList(1,2,3,4,5,6));

        //when
        Prize result1 = case1.getPrizeOfLotto();
        Prize result2 = case2.getPrizeOfLotto();
        Prize result3 = case3.getPrizeOfLotto();
        Prize result4 = case4.getPrizeOfLotto();
        Prize result5 = case5.getPrizeOfLotto();

        //then
        assertThat(result1).isEqualTo(Prize.FIFTH);
        assertThat(result2).isEqualTo(Prize.FOURTH);
        assertThat(result3).isEqualTo(Prize.THIRD);
        assertThat(result4).isEqualTo(Prize.SECOND);
        assertThat(result5).isEqualTo(Prize.FIRST);
    }
    @DisplayName("로또 번호의 개수가 6개가 넘어가면 예외가 발생한다.")
    @Test
    void createLottoByOverSize() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void createLottoByDuplicatedNumber() {
        // TODO: 이 테스트가 통과할 수 있게 구현 코드 작성
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // 아래에 추가 테스트 작성 가능
    @DisplayName("로또 번호가 몇 개 맞았는지 리턴한다.")
    @Test
    void howManyEqualsToWinningNumbers_doesContainBonusNumber_테스트() {
        //given
        Lotto.setWinningNumbers(Arrays.asList(1,2,3,4,5,6));
        Lotto.setBonusNumber(7);
        Lotto case1 = new Lotto(Arrays.asList(1,2,3,10,11,12));
        Lotto case2 = new Lotto(Arrays.asList(1,2,3,4,11,12));
        Lotto case3 = new Lotto(Arrays.asList(1,2,3,4,5,12));
        Lotto case4 = new Lotto(Arrays.asList(1,2,3,4,5,7));
        Lotto case5 = new Lotto(Arrays.asList(1,2,3,4,5,6));

        //when
        int result1_a = case1.howManyEqualsToWinningNumbers();
        boolean result1_b = case1.doesContainBonusNumber();
        int result2_a = case2.howManyEqualsToWinningNumbers();
        boolean result2_b = case2.doesContainBonusNumber();
        int result3_a = case3.howManyEqualsToWinningNumbers();
        boolean result3_b = case3.doesContainBonusNumber();
        int result4_a = case4.howManyEqualsToWinningNumbers();
        boolean result4_b = case4.doesContainBonusNumber();
        int result5_a = case5.howManyEqualsToWinningNumbers();
        boolean result5_b = case5.doesContainBonusNumber();

        //then
        assertThat(result1_a).isEqualTo(3);
        assertThat(result1_b).isEqualTo(false);
        assertThat(result2_a).isEqualTo(4);
        assertThat(result2_b).isEqualTo(false);
        assertThat(result3_a).isEqualTo(5);
        assertThat(result3_b).isEqualTo(false);
        assertThat(result4_a).isEqualTo(5);
        assertThat(result4_b).isEqualTo(true);
        assertThat(result5_a).isEqualTo(6);
        assertThat(result5_b).isEqualTo(false);
    }
}
