package lotto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Lotto {
    private final List<Integer> numbers;
    private static List<Integer> winningNumbers;
    private static int bonusNumber;
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
    public static void setWinningNumbers(List<Integer> numbers) {
        winningNumbers = numbers;
    }
    public static void setBonusNumber(int number) {
        bonusNumber = number;
    }
    public static List<Integer> getWinningNumbers() {
        return winningNumbers;
    }
    public static int getBonusNumber() {
        return bonusNumber;
    }
    public int howManyEqualsToWinningNumbers() {
        List<Integer> commonNumbers = new ArrayList<>(numbers);
        commonNumbers.retainAll(winningNumbers);
        return commonNumbers.size();
    }
    public Prize getPrizeOfLotto() {
        return Prize.of(howManyEqualsToWinningNumbers(), doesContainBonusNumber());
    }

    public boolean doesContainBonusNumber() {
        return numbers.contains(bonusNumber);
    }
}
