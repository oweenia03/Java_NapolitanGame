import java.util.*;

// 안전한 신체 부위 목록을 관리하는 클래스
public class SafeCostPool {
    private List<String> safeCosts; // 안전 부위 리스트
    private final Random random = new Random();

    public SafeCostPool(List<String> initialCosts) {
        this.safeCosts = new ArrayList<>(initialCosts);
    }

    // 남은 부위가 비었는지 확인
    public boolean isEmpty() {
        return safeCosts.isEmpty();
    }

    // 랜덤으로 부위 하나를 뽑아서 제거, 없으면 예외 발생
    public String getRandomAndRemove() throws EmptyPoolException {
        if (safeCosts.isEmpty()) {
            throw new EmptyPoolException("당신은 과다출혈로 사망했습니다.");
        }
        return safeCosts.remove(random.nextInt(safeCosts.size()));
    }

    // 현재 남아 있는 안전 부위 리스트 반환
    public List<String> getRemaining() {
        return new ArrayList<>(safeCosts);
    }
}
