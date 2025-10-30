package banking;

/* 기본 계좌 클래스
 - Account 클래스 멤버 변수선언
 - 생성자 선언                 */


public class Account {
    private String accountNumber;
    private String name;
    private int balance;
    
    public Account(String accountNumber, String name, int balance) {       // 생성자
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }
    
    public String getAccountNumber() { return accountNumber; }              // Getter 메서드
    public String getName() { return name; }
    public int getBalance() { return balance; }
    
    public void deposit(int amount) {                                        // 입금 기능
        this.balance += amount;
    }
    
    public boolean withdraw(int amount) {                                    // 출금 기능
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;                                                         // 잔액 부족 시 false 반환
    }
    
    public void showAccountInfo() {                                           // 계좌정보 출력메서드
        System.out.println("계좌번호: " + accountNumber);
        System.out.println("고객이름: " + name);
        System.out.println("계좌잔고: " + balance + "원");
    }
}
  
 
  
  
