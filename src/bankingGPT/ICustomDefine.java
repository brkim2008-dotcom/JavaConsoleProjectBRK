package bankingGPT;

public interface ICustomDefine {
    // 메뉴 상수 (BankingSystemMain에서 사용)
    int MENU_MAKE = 1;      
    int MENU_DEPOSIT = 2;   
    int MENU_WITHDRAW = 3;  
    int MENU_INQUIRE = 4;   
    int MENU_EXIT = 5;      
    
    // 신용 등급별 추가 이자율 (HighCreditAccount에서 사용)
    double GRADE_A = 0.07;
    double GRADE_B = 0.04;
    double GRADE_C = 0.02;
}