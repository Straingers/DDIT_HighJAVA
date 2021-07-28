package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil3;

public class MemberDaoImpl implements IMemberDao{

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public int insertMemeber(Connection conn, MemberVO mv) throws SQLException {
		int cnt = 0;
	
		try {
			String sql = "INSERT INTO MYMEMBER(MEM_ID, MEM_NAME, MEM_TEL, MEM_ADDR)"
					+ " VALUES(?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			JDBCUtil3.disConnect(null, null, pstmt, null);
		}
		
		return cnt;
	}

	@Override
	public boolean checkMember(Connection conn, String memId) throws SQLException {
		boolean chk = false;
		
		try  {
			String sql = "SELECT COUNT(*) CNT"
					+ " FROM MYMEMBER"
					+ " WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			while(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				chk = true;
			}
		} finally {
			JDBCUtil3.disConnect(null, null, pstmt, rs);
		}
		return chk;
	}

	@Override
	public List<MemberVO> getAllMemberList(Connection conn) throws SQLException {
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		try {
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery("SELECT * FROM MYMEMBER");
			
			while (rs.next()) {
				MemberVO mv = new MemberVO();
				
				String memId = rs.getString("MEM_ID");
				String memName = rs.getString("MEM_NAME");
				String memTel = rs.getString("MEM_TEL");
				String memAddr = rs.getString("MEM_ADDR");
				
				mv.setMemId(memId);
				mv.setMemName(memName);
				mv.setMemTel(memTel);
				mv.setMemAddr(memAddr);
				
				memList.add(mv);
			}
		} finally {
			JDBCUtil3.disConnect(null, stmt, null, rs);
		}
		
		return memList;
	}

	@Override
	public int updateMember(Connection conn, MemberVO mv) throws SQLException {
		int cnt = 0;
		
		try {
			String sql = "UPDATE MYMEMBER"
					+ " SET MEM_NAME = ?"
					+ " , MEM_TEL = ?"
					+ " , MEM_ADDR = ?"
					+ " WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			cnt = pstmt.executeUpdate();
		} finally {
			JDBCUtil3.disConnect(null, null, pstmt, null);
		}
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {
		int cnt = 0;
		
		try {
			String sql = "DELETE FROM MYMEMBER"
					+ " WHERE MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
		} finally {
			JDBCUtil3.disConnect(null, null, pstmt, null);
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(Connection conn, MemberVO mv) throws SQLException {

		List<MemberVO> memList = new ArrayList<>();
		
		try {
			String sql = "SELECT * "
					+ " FROM MYMEMBER"
					+ " WHERE 1=1";
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				sql += " AND MEM_ID = ? ";
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				sql += " AND MEM_NAME = ? ";
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				sql += " AND MEM_TEL = ? ";
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				sql += " AND MEM_ADDR LIKE '%' || ? || '%'";
			}
			pstmt = conn.prepareStatement(sql);
			
			System.out.println(sql);
			
			int index = 1;
			
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				pstmt.setString(index++, mv.getMemId());
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				pstmt.setString(index++, mv.getMemName());
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				pstmt.setString(index++, mv.getMemTel());
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				pstmt.setString(index++, mv.getMemAddr());
			}
			 
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mv2 = new MemberVO();
				mv2.setMemId(rs.getString("MEM_ID"));
				mv2.setMemName(rs.getString("MEM_NAME"));
				mv2.setMemTel(rs.getString("MEM_TEL"));
				mv2.setMemAddr(rs.getString("MEM_ADDR"));

				memList.add(mv2);
			}
		} finally {
			JDBCUtil3.disConnect(null, null, pstmt, rs);
		}
		return memList;
	}
}
