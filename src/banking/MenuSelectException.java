package banking;

public class MenuSelectException extends Exception {      

    public MenuSelectException(int menuNum) {               // 메뉴 번호(menuNum)를 정수(int) 형태 매개변수로 받는 공개 생성자
        // 부모 클래스(Exception)의 생성자 호출 및 오류 메시지 설정
        super(menuNum + "은(는) 유효하지 않은 메뉴 선택입니다.");
    }
}