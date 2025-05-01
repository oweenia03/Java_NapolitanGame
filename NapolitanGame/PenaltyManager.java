package NapolitanGame;

import java.util.*;

// 없는 물건 말하기(탈출 시도) 기능을 담당하는 클래스
public class PenaltyManager {
    private int failCount = 0; // 실패 횟수
    private final Random random = new Random();
    private final SafeCostPool safeCostPool; // 안전 부위 풀

    public PenaltyManager(SafeCostPool safeCostPool) {
        this.safeCostPool = safeCostPool;
    }

    // 사용자 입력을 받아 없는 물건인지 판별
    public boolean processInput(String input, List<Item> validItems) {
        String normalizedInput = normalize(input);

        for (Item item : validItems) {
            if (normalize(item.realName).equals(normalizedInput)) {
                // 입력이 기존 아이템과 일치하는 경우
                String randomCost = assignRandomCost(); // 손실 부위 결정

                System.out.println("[결과] '" + item.realName + "'은(는) 이미 존재하는 물건입니다.");

                if (isFatal(randomCost)) {
                    // 치명적인 부위인 경우 사망
                    GameDisplay.printFatalMessage(randomCost);
                    return false;
                } else {
                    // 신체 일부 손실
                    GameDisplay.printWrongGuess(failCount, randomCost);
                    return true;
                }
            }
        }

        // 없는 물건이면 탈출 성공
        GameDisplay.printEscapeMessage();
        return false;
    }

    // 문자열 정규화(공백 제거, 소문자 변환)
    private String normalize(String str) {
        return str.replaceAll("\\s+", "").toLowerCase();
    }

    // 랜덤으로 손실 부위를 선택
    private String assignRandomCost() {
        List<String> combinedCosts = new ArrayList<>(safeCostPool.getRemaining());
        combinedCosts.addAll(Arrays.asList("심장 반 개", "전두엽"));
        return combinedCosts.get(random.nextInt(combinedCosts.size()));
    }

    // 치명적인 부위 판별
    private boolean isFatal(String cost) {
        return cost.equals("전두엽") || cost.equals("심장 반 개");
    }
}
