package bankingGPT;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AccountManager {

    private List<Account> accountList;

    private Scanner scanner;

    public AccountManager() {
        this.accountList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    // --- 1. 계좌 개설 ---
    public void makeAccount() {
        int select;
        String accNum, name, grade;
        int balance, rate;

        System.out.println("***신규계좌개설***");

        // 계좌 종류 선택 메뉴 출력 및 입력
        System.out.println("---- 계좌 종류를 선택하세요 ----");
        System.out.println("1. 일반고객계좌");
        System.out.println("2. VIP고객계좌");
        System.out.print("선택: ");

        try {
            select = scanner.nextInt();
            scanner.nextLine(); 

            if (select != 1 && select != 2) {
                System.out.println("잘못된 선택입니다. 계좌개설을 취소합니다.");
                return;
            }
            // 공통 정보 입력
            System.out.print("계좌번호 : ");
            accNum = scanner.nextLine();
            System.out.print("고객이름 : ");
            name = scanner.nextLine();
            System.out.print("계좌잔고 : ");
            balance = scanner.nextInt();
            scanner.nextLine(); 

            if (balance < 0) {
                System.out.println("잔고는 음수일 수 없습니다. 계좌개설을 취소합니다.");
                return;
            }

            if (select == 1) { // 1. 일반고객계좌
                System.out.print("기본 이자율 (정수 형태 5% -> 5): ");
                rate = scanner.nextInt();
                scanner.nextLine(); 

                NormalAccount newAccount = new NormalAccount(accNum, name, balance, (double)rate / 100.0);
                accountList.add(newAccount);
                System.out.println("계좌 개설이 완료되었습니다.");

            } else { // 2. VIP고객계좌 (select == 2)
                System.out.print("기본 이자율 (정수 형태 5% -> 5): ");
                rate = scanner.nextInt();
                scanner.nextLine(); 
                System.out.print("신용등급 (A, B, C): ");
                grade = scanner.nextLine().toUpperCase();

                if (!grade.equals("A") && !grade.equals("B") && !grade.equals("C")) {
                    System.out.println("유효하지 않은 신용등급입니다. 계좌개설을 취소합니다.");
                    return;
                }

                HighCreditAccount newAccount =
                        new HighCreditAccount(accNum, name, balance, (double)rate / 100.0, grade);
                accountList.add(newAccount);
                System.out.println("계좌 개설이 완료되었습니다.");
            }

        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력 형식입니다. (숫자를 입력해야 하는 곳에 문자를 입력했습니다). 계좌개설을 취소합니다.");
            scanner.nextLine(); // 잘못된 입력을 버퍼에서 제거
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다. 계좌개설을 취소합니다.");
            scanner.nextLine();
        }
    }

    // 계좌 검색 헬퍼 메서드
    private Account findAccount(String accNum) {
        for (Account acc : accountList) {
            if (acc.getAccountNumber().equals(accNum)) {
                return acc;
            }
        }
        return null;
    }

    // --- 2. 입금 ---
    public void deposit() {
        System.out.println("***입 \t 금***");
        System.out.println("계좌번호와 입금할 금액을 입력하세요");

        System.out.print("계좌번호:");
        String accNum = scanner.nextLine();

        System.out.print("입금액:");

        int amount = 0;
        try {
            amount = scanner.nextInt();
            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("유효하지 않은 입금액입니다. (0원 이하)");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("잘못된 금액 입력입니다. 숫자를 입력해야 합니다.");
            scanner.nextLine();
            return;
        } catch (Exception e) {
            System.out.println("입금 중 오류가 발생했습니다.");
            scanner.nextLine();
            return;
        }
        Account account = findAccount(accNum);
        if (account != null) {
            account.deposit(amount);
            System.out.println("입금이 완료되었습니다.");
        } else {
            System.out.println("오류: 해당 계좌번호를 찾을 수 없습니다.");
        }
    }

    // --- 3. 출금 ---
    public void withdraw() {
        System.out.println("***출 \t 금***");
        System.out.println("계좌번호와 출금할 금액을 입력하세요");

        System.out.print("계좌번호:");
        String accNum = scanner.nextLine();

        System.out.print("출금액:");
        int amount = 0;
        try {
            amount = scanner.nextInt();
            scanner.nextLine();

            if (amount <= 0) {
                System.out.println("유효하지 않은 출금액입니다. (0원 이하)");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("잘못된 금액 입력입니다. 숫자를 입력해야 합니다.");
            scanner.nextLine();
            return;
        } catch (Exception e) {
            System.out.println("출금 중 오류가 발생했습니다.");
            scanner.nextLine();
            return;
        }

        Account account = findAccount(accNum);
        if (account != null) {
            if (account.withdraw(amount)) {
                System.out.println("출금이 완료되었습니다.");
            } else {
                System.out.println("오류: 잔고가 부족하거나 유효하지 않은 출금액입니다.");
            }
        } else {
            System.out.println("오류: 해당 계좌번호를 찾을 수 없습니다.");
        }
    }

    // --- 4. 전체 계좌 정보 출력 ---
    public void showAllAccountInfo() {
        System.out.println("***계좌정보출력***");
        if (accountList.isEmpty()) {
            System.out.println("등록된 계좌가 없습니다.");
            return;
        }
        for (Account acc : accountList) {
            acc.showAccountInfo();
        }
        System.out.println("전체계좌정보 출력이 완료되었습니다.");
    }
}  
