package main.java.com.study.jdbc.main.dml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import main.java.com.study.jdbc.util.DBConnection;

/*board_mst 가 나오게끔 해라.*/
public class jdbcSelect2 {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("작성자 id: ");
		int writerId = scanner.nextInt();
		
		Connection con = DBConnection.getInstance().getConnection(); /*연결이 되어야*/
		
		String sql = "select * from board_mst where writer_id = ?"; /* 조건을 주고 싶을때 where*/ /*미완성된 조건*/
		PreparedStatement pstmt; /*쿼리문 받기*/
		try {			
			pstmt= con.prepareStatement(sql); /*쿼리문 줄게*/
			pstmt.setInt(1, writerId); /*writerId int 로 대체 하겠다*/
			ResultSet rs = pstmt.executeQuery();			
			
			while(rs.next()) { 
				System.out.println("id: " + rs.getInt(1) 
				+ "\t title: " + rs.getString(2)
				+ "\t content: " + rs.getString(3)
				+ "\t read_count: " + rs.getInt(4)
				+ "\t writer_id: " + rs.getInt(5));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
}


