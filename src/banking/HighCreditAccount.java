package banking;

/* VIP고객계좌 클래스
 - Account 부모클래스에서 상속
 - 생성자를 통해 이자율정보를 초기화 할 수 있도록 정의
   멤버변수 선언 및 생성자 선언
 - 고객 신용등급별 이자율 설정
 - overide : 
   1. 총 이자율 계산 (기본 이자율 + 추가 이자율)
   2. 현재 잔고에 대한 총 이자액 계산 (이자율 * 원금)
   3. 잔고 업데이트: 잔고 = 기존 잔고 + 총 이자액 + 입금액   */
// VIP고객계좌 클래스 (기본 이자율 + 신용 등급별 추가 이자율 적용)

public class HighCreditAccount 
       extends NormalAccount implements ICustomDefine {
  