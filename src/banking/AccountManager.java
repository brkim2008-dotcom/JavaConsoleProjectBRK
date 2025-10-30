package banking;

import java.util.Scanner;
import java.util.InputMismatchException;                                                  // BankingSystemMain에서 사용

public class AccountManager {
		
	private Account[] accountList;                                                         // 계좌 객체 배열
	private int numOfAccount;                                                              // 현재 저장된 계좌 객체의 수
	private Scanner scanner;                                                               // 사용자 입력용 Scanner
		
	public AccountManager() {
		this.accountList = new Account[50];                                                // 배열 크기 50으로 초기화
		this.numOfAccount = 0;                                                             // 계좌 수 초기화
		this.scanner = new Scanner(System.in);
	}
	private Account findAccount(String accNum) {                                           // 계좌 번호로 Account 객체를 찾아 반환하는 보조 메서드
		for (int i = 0; i < numOfAccount; i++) {
			if (accountList[i].getAccountNumber().equals(accNum)) {
				return accountList[i];                                                     // 찾으면 해당 객체 반환
			}
		}
		return null;                                                                        // 못 찾으면 null 반환
	}
		
	public void makeAccount() {                                                            // 3. 계좌 개설
		
		System.out.println("\n***신규계좌개설***");
			
		System.out.print("계좌번호: ");
		String accountNumber = scanner.nextLine();
			
		System.out.print("고객이름: ");
		String name = scanner.nextLine();
			
		int balance = -1;
		while (balance < 0) {
			try {
				System.out.print("계좌잔고(0원 이상): ");
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
		Account newAccount = new Account(accountNumber, name, balance);                       // Account 객체 생성 및 배열에 저장
		accountList[numOfAccount++] = newAccount;                                             // 배열에 저장 후 계좌 수 증가
			
		System.out.println("계좌 개설이 완료되었습니다.");
	}
	
	public void deposit() {                                                                    // 4. 입금
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
			
		account.deposit(amount);
		System.out.println("입금이 완료되었습니다. "
				+ "현재 잔액: "  + account.getBalance() + "원");
	}
		
	public void withdraw() {                                                                            // 5. 출금
		System.out.println("\n***출 금***");
		System.out.print("계좌번호: ");
		String accountNumber = scanner.nextLine();
			
		Account account = findAccount(accountNumber);
		if (account == null) {
			System.out.println("[오류] 계좌번호를 찾을 수 없습니다.");
			return;
		}
			
		int amount = -1;
		int currentBalance = account.getBalance();
			
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
					amount = -1;                                                                    // 다시 입력받기 위해 -1로 초기화
				}
			} catch (InputMismatchException e) {
				System.out.println("[오류] 출금액은 숫자로 입력해야 합니다. 다시 입력.");
				scanner.nextLine();
			}
		}
			
		if (account.withdraw(amount)) {                                                               // 출금 성공
			System.out.println("출금이 완료되었습니다. 현재 잔액: " 
		            + account.getBalance() + "원");
		} else {
			System.out.println("[오류] 잔액 부족으로 출금 실패.");                                    // 이 로직은 amount > currentBalance에서 이미 걸러지지만, 안전을 위해 남겨둠
		}
	}
		
	public void inquire() {                                                                            // 6. 전체 계좌 정보 출력
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
		
	public void closeScanner() {                                                                        // 7. 프로그램 종료 시 Scanner 닫기 (메인에서 호출할 용도)
		if (scanner != null) {
			scanner.close();
		}
	}
}	
	
