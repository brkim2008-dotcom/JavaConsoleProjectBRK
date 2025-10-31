package banking;

public class NormalAccount extends Account {                // 보통계좌의 특성 정의 , 부모클래스 Account 상속
	
	private final double interestRate;                      // 캡슐화를 위해 private final 사용
	
    public NormalAccount (String accountNumber, 
 		String name, int balance, double interestRate) {
    	
 	    super(accountNumber, name, balance);
 	    this.interestRate = interestRate;
    }
 
    public double getInterestRate() {
        return interestRate;
    }
 
    @Override
    public void deposit(int amount) {                       // 입금 시 이자 계산 및 적용 (재정의)
    	
 	    int interest = (int)(super.getBalance() * this.interestRate);
 	    super.deposit(interest);
 	    super.deposit(amount);
    }
   
    @Override                                               
    public void showAccountInfo() {                          // 계좌정보 출력 메서드 (재정의)
    	
        super.showAccountInfo();                             
        System.out.println("기본이자>" + interestRate + "%");
    }
}


