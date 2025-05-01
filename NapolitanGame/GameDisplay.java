import java.util.*;

// 게임 내 모든 텍스트 출력 담당 클래스
public class GameDisplay {

    // 일정 시간 딜레이 후 메시지를 출력하는 메소드
    public static void delayedPrint(String message, long delayMillis) {
        try {
            Thread.sleep(delayMillis);
            System.out.println(message);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // 한 글자씩 천천히 출력하는 메소드 (공포 연출)
    public static void slowPrint(String message, long charDelayMillis) {
        try {
            for (char c : message.toCharArray()) {
                System.out.print(c);
                System.out.flush();
                Thread.sleep(charDelayMillis);
            }
            System.out.println();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public static void GameInfo(){
        System.out.println("이 프로그램은 '(나폴리탄) 1. 해피 에브리데이 마트 수색 지침서' 괴담을 기반으로 제작되었습니다.");
        delayedPrint("현재 알려진 해피 에브리데이 마트 탈출 방법은 한 가지밖에 없으며, 이는 마감시간 내에 쇼핑을 끝마치는 것입니다.", 600);
        delayedPrint("해피 에브리데이 마트에 진열된 모든 상품의 대가는 신체 부위의 일부입니다. 상품을 구입한다면 이는 쇼핑으로 인정됩니다.",600);
        delayedPrint("귀하의 무사 귀환을 기원합니다.\n",600);
    }
    public static void GameStart(){
        slowPrint("0. 해피 에브리데이 마트 수색 지침서", 100);
        slowPrint("해피 애브리데이 마트 진입에 앞서 반드시 아래의 대전제를 명심해 주십시오.", 70);
        slowPrint("해당 마트의 존재들은 손님에게 해를 끼치지 않습니다.", 70);
        slowPrint("또한 해당 마트의 존재들은 매장 내에서 손님 외의 어떠한 생명체도 용납하지 않습니다.", 70);
        slowPrint("따라서 마트 내에서는 언제나 일반적인 마트의 손님처럼 행동하기를 강력히 권고드리는 바입니다.", 70);
        slowPrint("마트 내에는 귀하 외에도 상당수의 점원과 손님이 돌아다니고 있을 것이나,\n어떤 상황에서도 그들의 얼굴을 주의 깊게 응시하거나 먼저 말을 걸지 마십시오.", 70);
        slowPrint("위 사항을 어길 경우 귀하의 가족에게는 장례비 및 상당액의 위로금이 지급됩니다.", 70);
    }


    // 메인 메뉴 출력
    public static void showMainMenu() {
        System.out.println("\n무엇을 하시겠습니까?");
        System.out.println("1. 상품 구매");
        System.out.println("2. 없는 물건 말하기");
        System.out.println("3. 경품 뽑기");
        System.out.print("> ");
    }

    // 상품 리스트 출력
    public static void showItemList(List<Item> items) {
        for (Item item : items) {
            System.out.println(item.displayName);
        }
    }

    // SafeCostPool 소진 시 호출
    public static void handleEmptyPool(String message) {
        System.out.println(message);
        System.exit(0);
    }

    // 경품 즉사 이벤트 연출
    public static void GachaFatalMessage() {
        System.out.println("🎁 [경품] 희박한 확률을 뚫고 TV에 당첨되었습니다!");
        slowPrint("\"운이 좋으세요. 이 제품, 2014년 신형이거든요.\"", 50);
        delayedPrint("점원이 TV 전원을 켭니다.", 1000);
        slowPrint("TV에서 토끼, 사자, 사슴 인형탈을 쓴 인물들이 등장하는 어린이 프로그램이 해피 에브리데이 테마송과 함께 송출되고 있습니다.",100);
        slowPrint("\"어떠세요? 화질이 참 좋죠?\"", 70);
        delayedPrint("당신은 손뼉을 치며 노래를 따라 부르고 싶은 충동에 휩싸입니다.", 500);
        slowPrint(". . . . . .", 200);
        delayedPrint("점차 의식이 옅어집니다...", 1000);
        System.exit(0);
    }
    // 상품 구매 시 사망 출력
    public static void printFatalMessage(String cause) {
        delayedPrint("점원이 곧바로 물건을 가져와 당신의 카트에 담습니다.", 1000);
        delayedPrint("바코드 찍는 중...", 800);
        delayedPrint("[사망] 해당 상품의 가격은 '" + cause + "'. 당신은 사망했습니다.", 2000);
    }

    // 탈출 성공 메시지
    public static void printEscapeMessage() {
        System.out.println("[성공] 점원이 사과하며 당신을 보내줍니다.\n탈출에 성공했습니다!");
    }

    // 없는 물건 틀렸을 때 출력
    public static void printWrongGuess(int count, String loss) {
        delayedPrint("점원이 곧바로 물건을 가져와 당신의 카트에 담습니다.", 500);
        delayedPrint("바코드 찍는 중...", 800);
        delayedPrint(loss + "을(를) 잃었습니다.", 2000);
    }

    // 없는 물건 입력 시 시나리오 출력
    public static void showEmptyCartScenario() {
        delayedPrint("빈 카트를 끌고 계산대를 지나가는 중...", 1000);
        delayedPrint("점원이 귀하를 불러세웁니다.", 1000);
        slowPrint("\"오늘은 아무것도 사지 않으시네요?\"", 100);
        delayedPrint("살아나가고 싶다면 친근한 태도로 \"아, 00을 사러 왔는데 없네요.\"라고 대답해야 합니다.", 700);
        delayedPrint("어떤 물건을 말씀하시겠습니까?", 200);
        System.out.print("> ");
    }
}