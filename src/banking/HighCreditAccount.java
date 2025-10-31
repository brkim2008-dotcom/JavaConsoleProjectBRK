package banking;

public class HighCreditAccount 
    extends Account implements ICustomDefine {                    // 신용신뢰계좌 특성구현 및 부모클래스 상속  
	
	private final double interestRate;                            // 기본 이자율
    private final String creditGrade;                             // 신용 등급 (A, B, C)
	
public HighCreditAccount (String accountNumber, 
    String name, int balance, int interestRate, String creditGrade) {
	
	super(accountNumber, name, balance);                          // 부모 클래스 필드 초기화
	this.interestRate = interestRate;                             // 고유 필드 초기화
    this.creditGrade = creditGrade;                               // 신용 등급 초기화
}

private double getCreditBonusRate() {                             // 신용 등급에 따른 추가 이자율을 반환하는 메서드 (ICustomDefine 상수 활용)

    double bonusRate = 0;
    
    switch (creditGrade.toUpperCase()) {
        case "A":
            bonusRate = ICustomDefine.GRADE_A; 
            break;
        case "B":
            bonusRate = ICustomDefine.GRADE_B; 
            break;
        case "C":
            bonusRate = ICustomDefine.GRADE_C;
            break;
        default:
            bonusRate = 0; 
            break;
    }
    return (double)bonusRate;
}

@Override
public void deposit(int amount) {                                   // 입금 시 이자 계산 및 적용 (재정의)
	
    double baseRate = (double)this.interestRate;                    // 기본 이자율 계산 (int -> double)
    double bonusRate = getCreditBonusRate();                        // 신용 등급별 가산 이자율 
    double totalRate = baseRate + bonusRate;                        // 총 이자율 = 기본 이자율 + 신용 가산 이자율
    int interest = (int)(super.getBalance() * totalRate);           // 이자 금액 계산 (현재 잔고 기준)
      
    super.deposit(interest);                                        // 이자 입금 (부모의 deposit 메서드 재활용)
	super.deposit(amount);                                          // 6. 원금 입금
}

@Override                                                           // 계좌정보 출력 메서드 (재정의)
public void showAccountInfo() {
    super.showAccountInfo();                                        // 부모 클래스의 기본 정보 출력
    System.out.println("기본이자>" + interestRate + "%");
    System.out.println("신용등급>" + creditGrade);
  }
}

