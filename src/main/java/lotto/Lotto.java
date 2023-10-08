package lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
        if ((new HashSet<Integer>(numbers)).size() != 6){
            throw new IllegalArgumentException();
        }
    }
    // TODO: 추가 기능 구현
    public int howManyEqualsToWinningNumbers(List<Integer> winningNumbers) {
        List<Integer> commonNumbers = new ArrayList<>(numbers);
        commonNumbers.retainAll(winningNumbers);
        return commonNumbers.size();
    }

    public boolean doesContainBonusNumber(int bonusNumber) {
        return numbers.contains(bonusNumber);
    }
}
