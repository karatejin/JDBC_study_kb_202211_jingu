package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import main.java.com.study.jdbc.util.DBConnection;

public class jdbcSelect1 {

	public static void main(String[] args) {
		Connection connection = DBConnection.getInstance().getConnection();
		
		String sql = "select * from score_mst ";
		
		try {
			PreparedStatement pstmt = connection.prepareStatement(sql); /*연결된 커넥션 객체에 sql을 prepareStatement(sql) 연결해달라. 반환자료형 pstmt*/
			ResultSet rs = pstmt.executeQuery(); /*해석한다.*/ /* executeQuery() 호출 되면 쿼리를 실행한다.*/ /*꺼내서 쓰면 비운다.*/
			
			System.out.println("id:\tname\t\tscore");
			
			while(rs.next()) { /*false 가 뜰때가지 반복한다.*/ /*next 있으면 true */
				System.out.println("id: " + rs.getInt(1) /*컬럼번호(1) 부터 시작한다.*/
				+ "\t name: " + rs.getString(2) 
				+ "\t score: " + rs.getInt(3));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
