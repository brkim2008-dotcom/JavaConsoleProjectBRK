package banking;

public class Account {                                                // *** 계좌객체 설계도 클래스 - 공통속성.공통기능 정의하는 부모클래스 ***  

    private String accountNumber;                                     // private : 외부 접근으로부터 데이터 무결성 유지
    private String name;
    private int balance;
    
    public Account(String accountNumber, String name, int balance) {  // 인스턴스 초기화 설정  
    	
        this.accountNumber = accountNumber;                           // 현재 객체의 인스턴스 변수
        this.name = name;
        this.balance = balance;
    }
    
    public String getAccountNumber() { return accountNumber; }        // private 변수에 읽기전용 접근 -> 계좌번호 반환
    public String getName() { return name; }
    public int getBalance() { return balance; }
    
    public void deposit(int amount) {                                 // 입금기능 메서드 - 반환값은 없음
        this.balance += amount;                                       // 현재 계좌객체 잔고 -> 매개변수 amount(입금) -> 잔고 갱신 
    }
    
    public boolean withdraw(int amount) {                             // 출금기능 메서드
        if (this.balance >= amount) {                                 // 현재잔고 >= 출금액 확인 후 실행 프로세스 
            this.balance -= amount;                                   // 현재 계좌객체 잔고 -> 매개변수 amount(출금) -> 잔고 갱신 
            return true;
        }
        return false;                                                 // 잔액 부족 시 false 반환
    }
    
    public void showAccountInfo() {                                   // 계좌정보 출력메서드 선언
        System.out.println("계좌번호: " + accountNumber);
        System.out.println("고객이름: " + name);
        System.out.println("계좌잔고: " + balance + "원");
    }
}
  
 
  
  
