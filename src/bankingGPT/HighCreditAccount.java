package bankingGPT;

// VIP고객계좌 클래스 (기본 이자율 + 신용 등급별 추가 이자율 적용)
public class HighCreditAccount extends NormalAccount implements ICustomDefine {
    private String creditGrade; // 신용 등급 (A, B, C)
    private double additionalRate; // 신용 등급별 추가 이자율

    public HighCreditAccount(String accountNumber, String name, 
    		int balance, double interestRate, String creditGrade) {
        super(accountNumber, name, balance, interestRate);
        this.creditGrade = creditGrade;
        this.additionalRate = getAdditionalRate(creditGrade);
    }
    // 신용 등급에 따른 추가 이자율 설정
    private double getAdditionalRate(String grade) {
        switch (grade.toUpperCase()) {
            case "A": return ICustomDefine.GRADE_A; // 7%
            case "B": return ICustomDefine.GRADE_B; // 4%
            case "C": return ICustomDefine.GRADE_C; // 2%
            default: return 0.0; // 잘못된 등급
        }
    }
    // ★ 입금 시 이자 계산 및 적용 (재정의) ★
    @Override
    public void deposit(int amount) {
        // 1. 총 이자율 계산 (기본 이자율 + 추가 이자율)
        double totalInterestRate = super.interestRate + this.additionalRate;

        // 2. 현재 잔고에 대한 총 이자액 계산 (이자율 * 원금)
        // 이자 계산 시, 정수형(int)으로 캐스팅하여 소수점 이하를 버립니다.
        int totalInterest = (int)(super.getBalance() * totalInterestRate);
        
        // 3. 잔고 업데이트: 잔고 = 기존 잔고 + 총 이자액 + 입금액
        super.setBalance(super.getBalance() + totalInterest + amount);
    }
}