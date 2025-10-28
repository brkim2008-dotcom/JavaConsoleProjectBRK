package bankingGPT;

/* MenuSelectException.java 정의
 - 부모클래스 Exception 으로 부터 상속   */

public class MenuSelectException extends Exception {

    private int wrongChoice; 

    // 생성자: 잘못된 메뉴 번호를 받아 Exception 부모에게 메시지를 전달
    public MenuSelectException(int choice) {
        // 부모 클래스의 생성자 (String message)를 호출하여 예외 메시지 설정
        super("잘못된 메뉴 번호 " + choice + "가 입력되었습니다.");
        this.wrongChoice = choice;
    }
    
    // 선택한 잘못된 메뉴 번호를 반환하는 getter 메서드 (선택 사항)
    public int getWrongChoice() {
        return wrongChoice;
    }
}
