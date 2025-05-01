package NapolitanGame;

// 잘못된 메뉴 입력 시 던질 사용자 정의 예외 클래스
public class InvalidMenuChoiceException extends Exception {
    // 생성자: 에러 메시지를 받아 부모 클래스(Exception)에 전달
    public InvalidMenuChoiceException(String message) {
        super(message);
    }
}
