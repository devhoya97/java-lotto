package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static final int LOTTO_PRICE = 1000;
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        try {
            runLotto();
        }catch(IllegalArgumentException e){
            System.out.println("[ERROR] IllegalArgumentException");
        }
    }
    public static void runLotto() {
        int buyMoney = getBuyMoney();
        List<Lotto> lottos = getLottos(buyMoney);
        getWinningNubers();
        List<Integer> lottosResult = getLottosResult(lottos);
        printLottosResult(buyMoney, lottosResult);
    }
    public static void printLottosResult(int buyMoney, List<Integer> lottosResult) {
        System.out.println("당첨 통계");
        System.out.println("---");
        System.out.printf("3개 일치 (5,000원) - %d개\n", lottosResult.get(5));
        System.out.printf("4개 일치 (50,000원) - %d개\n", lottosResult.get(4));
        System.out.printf("5개 일치 (1,500,000원) - %d개\n", lottosResult.get(3));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개\n", lottosResult.get(2));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개\n", lottosResult.get(1));
        System.out.printf("총 수익률은 %.1f%%입니다.", getRateOfProfit(buyMoney, lottosResult));
    }
    public static double getRateOfProfit(int buyMoney, List<Integer> lottosResult) {
        long profit = 0;
        profit += lottosResult.get(5) * 5000;
        profit += lottosResult.get(4) * 50000;
        profit += lottosResult.get(3) * 1500000;
        profit += lottosResult.get(2) * 30000000;
        profit += lottosResult.get(1) * 2000000000;
        return Math.round(profit / (double)buyMoney * 100 * 100) / 100.0;
    }
    public static List<Integer> getLottosResult(List<Lotto> lottos) {
        List<Integer> lottosResult = Arrays.asList(0,0,0,0,0,0);
        for(Lotto lotto : lottos) {
            Prize prize = lotto.getPrizeOfLotto();
            int indexOfLottosResult = prize.getIndexOfLottosResult();
            int incrementByOne = lottosResult.get(indexOfLottosResult) + 1;
            lottosResult.set(indexOfLottosResult, incrementByOne);
        }
        return lottosResult;
    }
//    public static int getResultCategory(Lotto lotto, List<Integer> winningNumbers, int bonusNumber) {
//
//        int howManyEqualsToWinningNumbers = lotto.howManyEqualsToWinningNumbers(winningNumbers);
//        boolean doesContainBonusNumber = lotto.doesContainBonusNumber(bonusNumber);
//        if (howManyEqualsToWinningNumbers == 5) {
//            if (doesContainBonusNumber)
//                return FIVE_COMMON_NUMBERS_AND_BONUS;
//            return FIVE_COMMON_NUMBERS;
//        }
//        if (howManyEqualsToWinningNumbers == 6) {
//            return SIX_COMMON_NUMBERS;
//        }
//        return howManyEqualsToWinningNumbers;
//    }
    public static int getBonusNumbers(List<Integer> winningNumbers) {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        System.out.println();
        validateBonusNumber(input, winningNumbers);
        return Integer.parseInt(input);
    }
    public static void getWinningNubers() {
        System.out.println("당첨 번호를 입력해주세요.");
        String[] inputStringArr = (Console.readLine()).split(",");
        inputStringArr = Arrays.stream(inputStringArr).map(String::trim).toArray(String[]::new);
        System.out.println();
        for (String str : inputStringArr) {
            validateStringWinningNumber(str);
        }
        List<Integer> winningNumbers = Arrays.stream(inputStringArr).map(Integer::parseInt).collect(Collectors.toList());
        validateWinningNumbersDuplicate(winningNumbers);
        Lotto.setWinningNumbers(winningNumbers);
    }
    public static List<Lotto> getLottos(int buyMoney) {
        int numOfLottos = buyMoney / LOTTO_PRICE;
        List<Lotto> lottos = new ArrayList<>(numOfLottos);
        for (int i=0; i<numOfLottos; i++) {
            lottos.add(new Lotto(getLottoNum()));
        }
        System.out.println();
        return lottos;
    }
    public static List<Integer> getLottoNum() {
        List<Integer> tmp = Randoms.pickUniqueNumbersInRange(1, 45, 6);
//        List<Integer> LottoNum = lottoNumSet.stream().sorted().collect(Collectors.toList());
        List<Integer> LottoNum = new ArrayList<>(tmp);
        Collections.sort(LottoNum);
        System.out.println(LottoNum);
        return LottoNum;
    }
    public static int getBuyMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        System.out.println();
        validateInputMoney(input);
        int buyMoney = Integer.parseInt(input);
        System.out.printf("%d개를 구매했습니다.\n",buyMoney/LOTTO_PRICE);
        return buyMoney;
    }
    public static void validateWinningNumbersDuplicate(List<Integer> winningNumbers) {
        Set<Integer> winningNumbersSet = new HashSet<>(winningNumbers);
        if (winningNumbersSet.size() != 6) {
            System.out.println("[ERROR] 당첨 번호에 중복이 있습니다.");
            throw new IllegalArgumentException("[ERROR] 당첨 번호에 중복이 있습니다.");
        }
    }
    public static void validateBonusNumber(String s, List<Integer> winningNumbers) {
        validateStringWinningNumber(s);
        if (winningNumbers.contains(Integer.parseInt(s))) {
            System.out.println("[ERROR] 보너스 숫자가 이미 당첨 숫자에 포함되어 있습니다.");
            throw new IllegalArgumentException("[ERROR] 보너스 숫자가 이미 당첨 숫자에 포함되어 있습니다.");
        }
    }
    public static void validateStringWinningNumber(String s) {
        validateStringIsNotEmptyOrNull(s);
        validateStringIsNumber(s);
        int LottoNumber = Integer.parseInt(s);
        if (LottoNumber < 1 || LottoNumber > 45) {
            System.out.println("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }
    public static void validateInputMoney(String s) {
        validateStringIsNotEmptyOrNull(s);
        validateStringIsNumber(s);
        validateDivisibleBy1000(s);
    }
    public static void validateStringIsNotEmptyOrNull(String s) {
        if (s == null || s.isEmpty()) {
            System.out.println("[ERROR] 입력 값이 없습니다.");
            throw new IllegalArgumentException("[ERROR] 입력 값이 없습니다.");
        }
    }
    public static void validateStringIsNumber(String s) {
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) {
                System.out.println("[ERROR] 입력 값이 자연수가 아닙니다.");
                throw new IllegalArgumentException("[ERROR] 입력 값이 자연수가 아닙니다.");
//                throw new NoSuchElementException("[ERROR] 입력 값이 자연수가 아닙니다.");
            }
        }
    }
    public static void validateDivisibleBy1000(String s) {
        int num = Integer.parseInt(s);
        if (num%1000 != 0) {
            System.out.println("[ERROR] 입력 값이 1000으로 나누어 떨어지지 않습니다.");
            throw new IllegalArgumentException("[ERROR] 입력 값이 1000으로 나누어 떨어지지 않습니다.");
        }
    }
}
