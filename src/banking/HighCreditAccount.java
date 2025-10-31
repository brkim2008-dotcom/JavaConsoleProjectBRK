package banking;

import banking.ICustomDefine;
import banking.NormalAccount;

public class HighCreditAccount 
       extends NormalAccount implements ICustomDefine {
    private String creditGrade;                                // 신용 등급 (A, B, C)
    private double additionalRate;                              // 신용 등급별 추가 이자율

    public HighCreditAccount(String accountNumber, String name, 
 		int balance, double interestRate, String creditGrade) {
        super(accountNumber, name, balance, interestRate);
        this.creditGrade = creditGrade;
        this.additionalRate = getAdditionalRate(creditGrade);
 }
    private double getAdditionalRate(String grade) {           // 신용 등급에 따른 추가 이자율 설정
        switch (grade.toUpperCase()) {
            case "A": return ICustomDefine.GRADE_A;            // 7%
            case "B": return ICustomDefine.GRADE_B;            // 4%
            case "C": return ICustomDefine.GRADE_C;            // 2%
            default: return 0.0; // 잘못된 등급
        }
    }
    @Override
    public void deposit(int amount) {                            // ★ 입금 시 이자 계산 및 적용 (재정의) ★
        double totalInterestRate = super.getInterestRate()       // 1. 총 이자율 계산 (기본 이자율 + 추가 이자율)        
        		+ this.additionalRate;                           // 💡 오류 수정: super.interestRate 대신 getInterestRate()를 사용하여 접근합니다.
        int totalInterest = (int)(super.getBalance() * totalInterestRate);// 2. 현재 잔고에 대한 총 이자액 계산
        super.deposit(totalInterest + amount);                   // 3. 잔고 업데이트: 잔고 = 기존 잔고 + 총 이자액 + 입금액
    }
}

