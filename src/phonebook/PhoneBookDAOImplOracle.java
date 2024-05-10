package phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PhoneBookDAOImplOracle implements PhoneBookDAO {

	private Connection getConnection () throws SQLException {
Connection conn = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			
			conn = DriverManager.getConnection(dburl, DatabaseConfig.DB_USER, DatabaseConfig.DB_PASS);
			
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver를 찾을 수 없습니다..ㅠㅠ");
		}
		
		return conn;
	}
	
	@Override
	public List<PhoneBookVO> getList() {
		List <PhoneBookVO> list = new ArrayList <PhoneBookVO>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "select * from Phone_book order by id";
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				
				PhoneBookVO vo = new PhoneBookVO(id, name, hp, tel);
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				
			}
		}
		
		return list;
	}

	@Override
	public boolean insert(PhoneBookVO phoneBookVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int insertCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "insert into phone_book (id, name, hp, tel) values (seq_phone_book.nextval, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, phoneBookVo.getName());
			pstmt.setString(2, phoneBookVo.getHp());
			pstmt.setString(3, phoneBookVo.getTel());
			
			insertCount = pstmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return insertCount == 1;
	}

	@Override
	public boolean delete(Integer id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deleteCount = 0;
		
		try {
			conn = getConnection();
			
			String sql = "DELETE from phone_book where id = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			
			deleteCount = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}catch (Exception e) {
				
			}
		}
		
		return deleteCount == 1;
	}

	@Override
	public PhoneBookVO get(String inStr) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		PhoneBookVO vo = null;
		
		try {
			conn = getConnection();
			
			//	실행 계획 수립			
			String sql = "select id, name, hp, tel from Phone_book where name like ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%" + inStr + "%");
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Integer id = rs.getInt(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				
				vo = new PhoneBookVO (id, name, hp, tel);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				
			}
		}
		
		return vo;
	}

	
	
}
