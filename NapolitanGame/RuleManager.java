import java.util.*;

// 상품 구매 로직을 담당하는 클래스
public class RuleManager {
    private final List<Item> allItems; // 전체 아이템 목록
    private final SafeCostPool safeCostPool; // 안전 부위 풀
    private final Random random = new Random(); // 랜덤 객체
    private static final String[] FATAL_COSTS = {"심장 반 개", "전두엽", "오른쪽 폐", "대장 15cm"};
    private static final String[] SYMBOLS = {"@", "#", "$", "%", "!", "&", "*"};

    public RuleManager(List<Item> allItems, SafeCostPool safeCostPool) {
        this.allItems = allItems;
        this.safeCostPool = safeCostPool;
    }

    // 랜덤으로 아이템을 5개 추출하여 사용자에게 보여줄 준비
    public List<Item> getRandomItems(int count) {
        Collections.shuffle(allItems);
        List<Item> selected = new ArrayList<>(allItems.subList(0, count));
        for (int i = 0; i < selected.size(); i++) {
            selected.get(i).setDisplayName((i + 1) + ". " + randomSymbols(5));
            selected.get(i).setCost(assignRandomCost());
        }
        return selected;
    }

    // 랜덤 가격 부여 (SafeCostPool + 치명적 부위)
    private String assignRandomCost() {
        List<String> combined = new ArrayList<>(safeCostPool.getRemaining());
        combined.addAll(Arrays.asList(FATAL_COSTS));
        return combined.get(random.nextInt(combined.size()));
    }

    // 랜덤 기호 문자열 생성
    private String randomSymbols(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(SYMBOLS[random.nextInt(SYMBOLS.length)]);
        }
        return sb.toString();
    }

    // 가격이 치명적인 부위인지 판별
    public boolean isFatal(String cost) {
        return Arrays.asList(FATAL_COSTS).contains(cost);
    }

    // 상품 구매 처리
    public void handlePurchase(Scanner scanner) {
        List<Item> selection = getRandomItems(5);
        GameDisplay.showItemList(selection);

        System.out.print("어떤 물건을 구매하시겠습니까? (1~5): ");
        String input = scanner.nextLine().trim();
        int choice;
        try {
            choice = Integer.parseInt(input) - 1;
            if (choice < 0 || choice >= selection.size()) {
                throw new InvalidMenuChoiceException("올바른 번호를 선택하세요.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[에러] 숫자를 입력해야 합니다.");
            return;
        } catch (InvalidMenuChoiceException e) {
            System.out.println("[에러] " + e.getMessage());
            return;
        }

        Item chosen = selection.get(choice);

        // 미리 보기권이 있을 경우 가격 먼저 보여주기
        if (GameState.previewTickets > 0) {
            System.out.println("해당 상품의 가격은 '" + chosen.cost + "'입니다.");
            GameDisplay.delayedPrint("정말 구매하시겠습니까? (y/n): ", 200);
            if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.println("구매를 취소하였습니다.");
                GameState.previewTickets--;
                return;
            }
            GameState.previewTickets--;
        }

        // 최종 구매 결과
        if (isFatal(chosen.cost)) {
            GameDisplay.printFatalMessage(chosen.cost);
            System.exit(0);
        } else {
            GameDisplay.delayedPrint("[생존] 해당 상품의 가격은 '" + chosen.cost + "'", 1000);
            GameDisplay.delayedPrint("고통스러우나, 당신은 살아서 마트를 탈출합니다.", 500);
            System.exit(0);
        }
    }
}