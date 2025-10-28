package bankingGPT;

// 일반고객계좌 클래스 (기본 이자율 적용)
public class NormalAccount extends Account {
    protected double interestRate; // 기본 이자율 (0.01 = 1%)

    public NormalAccount(String accountNumber, 
    		String name, int balance, double interestRate) {
        super(accountNumber, name, balance);
        this.interestRate = interestRate;
    }
    // ★ 입금 시 이자 계산 및 적용 (재정의) ★
    @Override
    public void deposit(int amount) {
        // (원금 + 이자) 계산
        int interest = (int)(super.getBalance() * interestRate);
        
        // 원금 + 이자 + 입금액 적용
        super.setBalance(super.getBalance() + interest + amount);
    }
}
