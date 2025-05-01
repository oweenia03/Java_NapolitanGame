//Main
// 괴이 마트에서 탈출하라! 선택지형 생존 게임 | 2413834 김민서
/*  이 프로그램은 '(나폴리탄) 1. 해피 에브리데이 마트 수색 지침서' 괴담을 기반으로 제작되었습니다.
현재 알려진 해피 에브리데이 마트 탈출 방법은 한 가지밖에 없으며, 이는 마감시간 내에 쇼핑을 끝마치는 것입니다.
해피 에브리데이 마트에 진열된 모든 상품의 대가는 신체 부위의 일부입니다. 상품을 구입한다면 이는 쇼핑으로 인정됩니다.
귀하의 무사 귀환을 기원합니다.
*/

package NapolitanGame;

import java.io.IOException;
import java.util.*;

// 게임의 메인 루프를 담당하는 클래스
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Item> allItems = null;

        // 아이템 파일 불러오기
        try {
            allItems = ItemLoader.loadItems();
        } catch (IOException e) {
            System.out.println("[에러] 아이템 파일을 불러오는 데 실패했습니다.");
            return; // 아이템 불러오기 실패 시 프로그램 종료
        }

        // 안전한 신체 부위 리스트 초기화
        List<String> safeCosts = List.of("손톱 다섯 개", "왼쪽 눈썹", "오른쪽 발가락", "왼손 약지", "귀끝", "오른쪽 발뒤꿈치", "눈알 두 개");
        SafeCostPool pool = new SafeCostPool(safeCosts); // SafeCostPool 생성

        // 주요 매니저 객체 생성
        RuleManager ruleManager = new RuleManager(allItems, pool);
        PenaltyManager penaltyManager = new PenaltyManager(pool);
        GachaManager gachaManager = new GachaManager(pool);

        // 게임 시작 전 수색 지침서 출력 여부 묻기
        System.out.println("해피 에브리데이 마트 수색에 앞서, 수색 지침서를 보시려면 y, 패스를 원하시면 아무 키나 입력하세요.");
        String sc = scanner.nextLine();
        if (sc.equalsIgnoreCase("y")) {
            GameDisplay.GameStart();
        }

        // 메인 게임 루프
        while (true) {
            GameDisplay.showMainMenu(); // 메뉴 출력
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    ruleManager.handlePurchase(scanner); // 상품 구매
                    break;
                case "2":
                    GameDisplay.showEmptyCartScenario(); // 없는 물건 말하기 시나리오 출력
                    boolean keepPlaying = penaltyManager.processInput(scanner.nextLine().trim(), allItems);
                    if (!keepPlaying) return; // 실패 3회 시 종료
                    break;
                case "3":
                    gachaManager.start(scanner); // 경품 뽑기
                    break;
                default:
                    System.out.println("올바른 번호를 입력하세요."); // 잘못된 입력 처리
            }
        }
    }
}
