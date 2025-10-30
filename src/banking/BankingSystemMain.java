
	package banking;

	import java.util.Scanner;
	import java.util.InputMismatchException;

	public class BankingSystemMain {

	    public static void showMenu() {                                             // showMenu() 메서드는 main 메서드 밖에 위치해야 합니다.
	        System.out.println("\n****Menu****");
	        System.out.println("1. 계좌 개설");
	        System.out.println("2. 입 금");
	        System.out.println("3. 출 금");
	        System.out.println("4. 계좌 정보 출력");
	        System.out.println("5. 프로그램 종료");
	        System.out.print("선택: ");
	    }

	    public static void main(String[] args) {

	        AccountManager manager = new AccountManager();
	        Scanner mainScanner = new Scanner(System.in);                           // 🚨 수정 1: Scanner를 while 루프 밖에서 한 번만 선언
	                                                                                // 이렇게 해야 루프마다 객체가 생성되는 비효율을 막고, mainScanner.close()를 해도
	        System.out.println("****Banking 계좌관리 시스템을 시작합니다****");           // 다른 Scanner(manager 내부의)가 System.in을 사용할 때 문제가 발생하지 않습니다.

	        while(true) {
	            try {
	                showMenu();
	                
	                int choice = mainScanner.nextInt();                         // 🚨 수정 2: while 루프 밖에서 선언된 mainScanner를 사용합니다.
	                mainScanner.nextLine(); 

	                if (choice < ICustomDefine.MENU_MAKE || 
	                		choice > ICustomDefine.MENU_EXIT) {
	                    throw new MenuSelectException(choice);
	                }
	                
	                switch(choice) {
	                
	                    case ICustomDefine.MENU_MAKE : manager.makeAccount();
	                        break;
	                    case ICustomDefine.MENU_DEPOSIT : manager.deposit();
	                        break;
	                    case ICustomDefine.MENU_WITHDRAW : manager.withdraw();
	                        break;
	                    case ICustomDefine.MENU_INQUIRE : manager.inquire();
	                        break;
	                    case ICustomDefine.MENU_EXIT : 
	                        System.out.println("프로그램을 종료합니다.");
	                        manager.closeScanner();                                              // AccountManager의 Scanner 닫기
	                        mainScanner.close();                                                  // Main의 Scanner 닫기
	                        return;    
	                } 
	            } catch (MenuSelectException e) { 
	                System.out.println("\n[예외 발생] " + e.getMessage());
	                System.out.println(">> 메뉴는 " + ICustomDefine.MENU_MAKE + "부터 "
	                    + ICustomDefine.MENU_EXIT + "까지의 정수만 입력할 수 있습니다.");
	                
	            } catch (InputMismatchException e) {                                              // 정수 외 문자 입력 처리
	                System.out.println("\n[오류 발생] 잘못된 입력 형식입니다. "
	                    + "메뉴를 다시 선택해 주세요.");
	                mainScanner.nextLine();                                                       // mainScanner.nextInt()에서 오류가 발생했으므로 버퍼에 남아있는 잘못된 입력(문자열)을 제거해야 다음 루프에서 정상적으로 작동합니다.
	                
	            } catch (Exception e) {
	                System.out.println("알 수 없는 오류가 발생했습니다: " + e.getMessage());
	                
	            }                                                                                   // 🚨 수정 5: try-catch 블록이 여기서 닫히고, 그 뒤에 while 루프가 닫혀야 합니다.
	        }                                                                                       // while(true) 루프의 끝
	    }                                                                                           // main 메서드의 끝
	}                                                                                               // class BankingSystemMain의 끝		