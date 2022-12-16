package main.java.com.study.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.RequiredArgsConstructor;
import main.java.com.study.jdbc.entity.User;
import main.java.com.study.jdbc.util.DBConnectionMgr;

//@RequiredArgsConstructor
public class UserDao { /*데이터에 접근할 수 있는 객체*/
	
//	private final DBConnectionMgr pool;
	
//	public UserDao(DBConnectionMgr pool) {
//		this.pool = pool;
//	}  이것이 @RequiredArgsConstructor 
	
	private DBConnectionMgr pool;
	
	public UserDao() {
		pool = DBConnectionMgr.getInstance();
	}
	
	public int insertUser(User user) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int successCount = 0;
		
		try { /*오토 인크리트먼드로*/
			con = pool.getConnection();
			sql = "insert into user_mst values(0,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			successCount = pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys(); /*오토 인크리트먼드된 값을 가져다줌*/
			if(rs.next()) {
				user.setUser_Id(rs.getInt(1)); /*유저에 set 해준다.*/
			}		
		} catch (Exception e) {			
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,pstmt,null); /*커넥션을 끊어줌*/
		}
		return successCount;
	}	
	
}
