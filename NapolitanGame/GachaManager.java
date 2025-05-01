package NapolitanGame;
import java.util.*;

// 경품 뽑기 기능을 담당하는 클래스
public class GachaManager {
    private final SafeCostPool safeCostPool;  // 안전 부위 풀 참조
    private final Random random = new Random(); // 랜덤 객체

    // 생성자: SafeCostPool 객체를 받아 초기화
    public GachaManager(SafeCostPool pool) {
        this.safeCostPool = pool;
    }

    // 경품 뽑기를 시작하는 메소드
    public void start(Scanner scanner) {
        System.out.println("전자제품 코너에서 경품 룰렛 이벤트를 진행하고 있습니다.");

        // 사용자가 뽑기를 원하는 동안 루프
        while (true) {
            System.out.print("참가하시겠습니까? (y/n): ");
            if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
                System.out.println("경품 뽑기를 종료합니다.");
                break;
            }

            int roll = random.nextInt(100); // 0~99 난수 생성

            if (roll < 30) {
                // 30% 확률로 미리 보기권 지급
                GameState.previewTickets++;
                System.out.println("🎁 [경품] 미리 보기권 +1 (보유: " + GameState.previewTickets + ")");
            } else if (roll < 45) {
                // 15% 확률로 즉사 이벤트 (공포 연출)
                GameDisplay.GachaFatalMessage();
            } else {
                // 나머지 55% 확률로 안전 부위 손실
                try {
                    // SafeCostPool에서 랜덤 부위 제거
                    String part = safeCostPool.getRandomAndRemove();
                    System.out.println("🩸 [꽝] 당신은 " + part + "을(를) 잃었습니다. 하지만 살아남았습니다.");
                } catch (EmptyPoolException e) {
                    // 부위가 모두 소진됐을 때 예외 처리
                    System.out.println(e.getMessage());
                    System.exit(0); // 사망 처리
                }
            }
        }
    }
}
