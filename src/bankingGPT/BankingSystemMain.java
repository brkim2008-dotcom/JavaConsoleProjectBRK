package bankingGPT;

import java.util.InputMismatchException; 
import java.util.Scanner;

public class BankingSystemMain {
	
	public static void main(String[] args) {
		
	    AccountManager manager = new AccountManager();
	    Scanner scanner = new Scanner(System.in);
	        
	    System.out.println("☆★☆ Banking 계좌관리 시스템 시작 ☆★☆");

        while (true) {
            try {
                showMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                // MenuSelectException을 사용하여 메뉴 범위 유효성 검사
                if (choice < ICustomDefine.MENU_MAKE || 
                		         choice > ICustomDefine.MENU_EXIT) {
                    // 사용자 정의 예외 발생 (1~5 범위 밖의 숫자 입력)
                    throw new MenuSelectException(choice); 
                }
                // choice 입력값에 따라 기능을 호출하는 switch문 
                switch (choice) {
                    case ICustomDefine.MENU_MAKE: // 1. 계좌개설
                        manager.makeAccount();
                        break;
                    case ICustomDefine.MENU_DEPOSIT: // 2. 입금
                        manager.deposit();
                        break;
                    case ICustomDefine.MENU_WITHDRAW: // 3. 출금
                        manager.withdraw();
                        break;
                    case ICustomDefine.MENU_INQUIRE: // 4. 전체계좌정보출력
                        manager.showAllAccountInfo();
                        break;
                    case ICustomDefine.MENU_EXIT: // 5. 프로그램종료
                        System.out.println("프로그램을 종료합니다. "
                        		+ "이용해 주셔서 감사합니다.");
                        scanner.close(); // 스캐너 종료
                        return; // main 메서드 종료
                }
            } catch (MenuSelectException e) {
                System.out.println("\n[예외 발생] " + e.getMessage());
                System.out.println(">> 메뉴는 " + ICustomDefine.MENU_MAKE 
                		+ "부터 " + ICustomDefine.MENU_EXIT + "까지의 정수만 입력할 수 있습니다.");
            } catch (Exception e) {
                System.out.println("\n[오류 발생] 잘못된 입력 형식입니다. 메뉴를 다시 선택해 주세요.");
                scanner.nextLine(); // 입력 버퍼 정리 (오류 발생 시 필수)
            }
        }
    }
    public static void showMenu() {
        System.out.println("\n----Menu----");
        System.out.println("1. 계좌개설");
        System.out.println("2. 입  금");
        System.out.println("3. 출  금");
        System.out.println("4. 계좌정보출력");
        System.out.println("5. 프로그램종료");
        System.out.print("선택: ");
    }
}
