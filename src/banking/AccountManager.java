package banking;

import java.util.Scanner;
import java.util.InputMismatchException;    

/* 계좌객체들을 저장하고 관리하며
   계좌 생성, 입금, 출금, 전체 정보 출력 등 핵심 비즈니스 로직을 처리하는 역할 */

public class AccountManager {                                             
		
	private Account[] accountList;                                         // Account 객체들을 저장할 배열변수 accountList
	private int numOfAccount;                                              // 현재 배열에 저장된 계좌 객체의 수
	private Scanner scanner;       
	
	public AccountManager() {
		this.accountList = new Account[50];                                                
		this.numOfAccount = 0;                                                          
		this.scanner = new Scanner(System.in);                             // scanner 객체 생성하여, 표준입력 스트림에 연결
	}
	private Account findAccount(String accountNumber) {                    // 계좌번호를 매개변수로 해당 Account 객체를 찾아 반환
		for (int i = 0; i < numOfAccount; i++) {
			if (accountList[i].getAccountNumber().equals(accountNumber)) { // 배열의 i번째 계좌번호가 찾는 계좌번호와 일치하는지 확인
				return accountList[i];                                                    
			}
		}
		return null;                                                                        
	}
	public void makeAccount() {                                                           
		int select = -1; 
		String accountNumber = null, name = null; 
		int balance = -1;
		
		System.out.println("\n***신규계좌개설***");
		
		System.out.println("---계좌선택---");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.println("선택: ");
		
		try {                                                               // try-catch와 while 루프를 결합, 입력 유효성 검사 수행
			select = scanner.nextInt();
			scanner.nextLine();
					
			if (select != 1 && select !=2) {
				System.out.println("잘못된 선택입니다. 계좌개설 취소");
				return;
			}
		}	
	    catch (InputMismatchException e) {
			System.out.println("[오류] 잔고는 숫자로 입력해야 합니다. 다시 입력.");
			scanner.nextLine(); 
		}
			
		System.out.print("계좌번호: ");
		accountNumber = scanner.nextLine();
			
		System.out.print("고객이름: ");
		name = scanner.nextLine();
			
		while (balance < 0) {
			try {
				System.out.print("계좌잔고: ");
				balance = scanner.nextInt();
				scanner.nextLine(); 
					
				if (balance < 0) {
					System.out.println("[경고] 잔고는 음수가 될 수 없습니다. 다시 입력.");
				}
			} catch (InputMismatchException e) {
				System.out.println("[오류] 잔고는 숫자로 입력해야 합니다. 다시 입력.");
				scanner.nextLine(); 
			}
		}
		
		Account newAccount = new Account(accountNumber, name, balance);       // Account 객체 생성 및 배열에 저장
		accountList[numOfAccount++] = newAccount;                             // 후위증가 연산자, 배열에 저장 후 계좌 수 증가
			
		System.out.println("계좌 개설이 완료되었습니다.");
	}
	
	public void deposit() {                                                   // 4. 입금
		System.out.println("\n***입 금***");
		System.out.print("계좌번호: ");
		String accountNumber = scanner.nextLine();
			
		Account account = findAccount(accountNumber);
		if (account == null) {
			System.out.println("[오류] 계좌번호를 찾을 수 없습니다.");
			return;
		}
			
		int amount = -1;
		while (amount <= 0) {
			try {
				System.out.print("입금액 (양수): ");
				amount = scanner.nextInt();
				scanner.nextLine(); 
					
				if (amount <= 0) {
					System.out.println("[경고] 입금액은 양수여야 합니다. 다시 입력.");
				}
			} catch (InputMismatchException e) {
				System.out.println("[오류] 입금액은 숫자로 입력해야 합니다. 다시 입력.");
				scanner.nextLine(); 
			}
		}
			
		account.deposit(amount);                                               // Account 객체의 deposit 메서드 호출 , 입금액만큼 잔고 증가
		System.out.println("입금이 완료되었습니다. "
				+ "현재 잔액: "  + account.getBalance() + "원");
	}
		
	public void withdraw() {                                                   // 5. 출금
		System.out.println("\n***출 금***");
		System.out.print("계좌번호: ");
		String accountNumber = scanner.nextLine();
			
		Account account = findAccount(accountNumber);
		if (account == null) {
			System.out.println("[오류] 계좌번호를 찾을 수 없습니다.");
			return;
		}
			
		int amount = -1;
		int currentBalance = account.getBalance();                            // 출금 전에 현재 잔고를 미리 가져와 저장
			
		while (amount <= 0) {
			try {
				System.out.print("출금액 (양수): ");
				amount = scanner.nextInt();
				scanner.nextLine();
					
				if (amount <= 0) {
					System.out.println("[경고] 출금액은 양수여야 합니다. 다시 입력.");
				} else if (amount > currentBalance) {
					System.out.println("[경고] 잔액 부족 (현재 잔액: " 
				        + currentBalance + "원)");
					amount = -1;                                              // 다시 입력받기 위해 -1로 초기화
				}
			} catch (InputMismatchException e) {
				System.out.println("[오류] 출금액은 숫자로 입력해야 합니다. 다시 입력.");
				scanner.nextLine();
			}
		}
			
		if (account.withdraw(amount)) {                                        // 출금 성공
			System.out.println("출금이 완료되었습니다. 현재 잔액: " 
		            + account.getBalance() + "원");
		} else {
			System.out.println("[오류] 잔액 부족으로 출금 실패.");                     
		}
	}
		
	public void inquire() {                                                    // 전체 계좌 정보 출력
		System.out.println("\n***전체계좌정보 출력***");
		if (numOfAccount == 0) {
			System.out.println("등록된 계좌가 없습니다.");
			return;
		}
		for (int i = 0; i < numOfAccount; i++) {
			System.out.println("--------------------");
			accountList[i].showAccountInfo();
		}
		System.out.println("--------------------");
		System.out.println("전체계좌 정보출력이 완료되었습니다. "
				+ "(총 " + numOfAccount + "개)");
	}
		
	public void closeScanner() {                                                // 프로그램 종료 시 Scanner 닫기 (메인에서 호출할 용도)
		if (scanner != null) {
			scanner.close();
		}
	}
}	
	
