package banking;

/* 일반고객계좌 클래스
 - Account 부모클래스 로부터 상속
 - 클래스 멤버변수 선언 & super 클래스 변수 상속
 - overide : 일반고객 기본이자율 적용 및
             입금시 이자 계산 및 적용        */

public class NormalAccount extends Account {
    protected double interestRate; // 기본 이자율 (0.01 = 1%)

    public NormalAccount(String accountNumber, 
    		String name, int balance, double interestRate) {
 