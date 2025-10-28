package bankingGPT;

//기본 계좌 클래스
public class Account {
  private String accountNumber;
  private String name;
  private int balance; 

  public Account(String accountNumber, String name, int balance) {
      this.accountNumber = accountNumber;
      this.name = name;
      this.balance = balance;
  }  
}