package banking;

/* 메뉴선택 및 이자율 지정을 위한 인터페이스 상수 정의
   - 메뉴 상수 : MANU_MAKE = 1 ~ MENU_XEIT = 5
   - 고객의 신용등급별 이자율 : A, B, C _ 7%, 4%, 2%   */

public interface ICustomDefine {
	
	int MENU_MAKE = 1;
	int MENU_DEPOSIT = 2;
	int MENU_WITHDRAW = 3;
	int MENU_INQUIRE = 4;
	int MENU_EXIT = 5;
	
	double GRADE_A = 0.07;
	double GRADE_B = 0.04;
	double GRADE_C = 0.03;
	
}
