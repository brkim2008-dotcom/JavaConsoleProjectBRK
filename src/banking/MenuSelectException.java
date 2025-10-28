package banking;
/* MenuSelectException.java 정의
- 부모클래스 Exception 으로 부터 상속   */
public class MenuSelectException extends Exception {

    public MenuSelectException(int menuNum) {
        // 부모 클래스(Exception)의 생성자 호출 및 오류 메시지 설정
        super(menuNum + "은(는) 유효하지 않은 메뉴 선택입니다.");
    }
}