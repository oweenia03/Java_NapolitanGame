// SafeCostPool이 비었을 때 발생하는 사용자 정의 예외 클래스
public class EmptyPoolException extends Exception {

    // 생성자: 예외 메시지를 받아서 부모 Exception에 넘김
    public EmptyPoolException(String message) {
        super(message);
    }
}
