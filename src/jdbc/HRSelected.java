package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class HRSelected {

	public static void main(String[] args) {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String id = "hr";
			String pass = "1234";
			Connection con = DriverManager.getConnection(url,id,pass);
			if(con!=null) {
				System.out.println("Oracle 연결성공");
				//1. 쿼리문 작성
				/* 작성시 줄바꿈할 때 앞뒤로 스페이스를 하나씩 삽입하는 것이 좋다.
				   그렇지 않으면 
				   문장이 서로 이어지는 syntaxerror가 발생하게 된다. */
				String sql = "SELECT * FROM employees WHERE"
						+ " department_id=20"
						+ " ORDER BY employee_id DESC";
				//2. 쿼리문 전송을 위한 인스턴스 생성
				/* 
				statement 인터페이스는 주로 정적 쿼리문을 실행할 때 사용한다
				정적쿼리란 인파라미터가 없는 변경할 수 없는 쿼리문을 말한다. */
				Statement stmt = con.createStatement();	
				//3. 쿼리문을 오라클로 전송한 후 실행
				/*
				 오라클은 쿼리문을 전송받은 후 실행하고, 그 결과를 resultset
				 인스턴스를 통해 반환한다.      */
				ResultSet rs = stmt.executeQuery(sql);
				int cnt = 1;
				//4. 반환된 결과의 갯수만큼 반복 인출
				/*
				쿼리문 조건에 따라 반환되는 레코드의 갯수가 다를 수 있으므로
				while문을 사용하고, 
				next() 메서드는 더이상 인출할 레코드가 없을 때 flase 반환 */
				while(rs.next()) {
					/*
					getxx() 메서드를 통해 각 컬럼에 접근한다.
					오라클의 자료형은 문자, 숫자, 날짜 3가지의 형식이므로
					메서드도 이와같은 동일한 형태를 가질 수 잇다
					컬럼 접근시 인덱스 와 컬럼명 2가지 사용할 수 잇다.*/
					//인덱스 1은 employeee_id를 가리킨다.
					String emp_id = rs.getString(1);
					//컬럼명을 통해 접근
					String f_name = rs.getString("first_name");
					String l_name = rs.getString("last_name"); 
					//날짜형식으로 데이터 인출
					java.sql.Date h_date = rs.getDate("hire_date");
					//숫자형식으로 데이터 인출
					int sal = rs.getInt("salary");
					System.out.print((cnt++) +"=>");
					System.out.printf("%s %s %s %s %s\n",
							emp_id, f_name, l_name, h_date, sal);
				}
				/* 모든 작업이 완료되면
				   메모리 절약을 위해 연결했던 모든 자원을 반납한다. */
				rs.close();
				stmt.close();
				con.close();
			}
			else {
				System.out.println("Oracle 연결실패");
			}
		}
		catch(Exception e) {
			System.out.println("Oracle 연결시 예외발생");
			e.printStackTrace();
		}
	}
}
