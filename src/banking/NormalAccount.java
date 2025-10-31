package banking;

public class NormalAccount extends Account {                // NormalAccount는 Account를 확장하고, 일반적인 이자율을 적용합니다.
	
	private final double interestRate; // 캡슐화를 위해 private final 사용
	
    public NormalAccount (String accountNumber, 
 		String name, int balance, double interestRate) {
 	    super(accountNumber, name, balance);
 	    this.interestRate = interestRate;
    }
 
    public double getInterestRate() {
        return interestRate;
    }
 
    @Override
    public void deposit(int amount) {                 // 입금 시 이자 계산 및 적용 (재정의)
 	
 	    int interest = (int)(super.getBalance() * this.interestRate);
 	    super.deposit(interest);
 	    super.deposit(amount);
    }
}