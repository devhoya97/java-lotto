package lotto;

public enum Prize {
    NO_PRIZE(0), FIRST(1), SECOND(2), THIRD(3), FOURTH(4), FIFTH(5);
    private final int indexOfLottosResult;
    Prize (int indexOfLottosResult) {
        this.indexOfLottosResult = indexOfLottosResult;
    }
    public static Prize of(int commonLottoNumbers, boolean doesContainBonusNumber) {
        if (commonLottoNumbers == 3)
            return FIFTH;
        if (commonLottoNumbers == 4)
            return FOURTH;
        if (commonLottoNumbers == 5) {
            if (doesContainBonusNumber)
                return SECOND;
            return THIRD;
        }
        if (commonLottoNumbers == 6)
            return FIRST;
        return NO_PRIZE;
    }

    public int getIndexOfLottosResult() {
        return indexOfLottosResult;
    }
}
