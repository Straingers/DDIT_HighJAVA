package homework.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import homework.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao{

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private static IBoardDao boardDao;
	
	private BoardDaoImpl() {

	}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	
	@Override
	public int insertBoard(Connection conn, BoardVO bv) throws SQLException {

		String sql = "INSERT INTO JDBC_BOARD(BOARD_NO, BOARD_TITLE, BOARD_CONTENT"
				+ " , BOARD_WRITER, BOARD_DATE)"
				+ " VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bv.getBoardTitle());
		pstmt.setString(2, bv.getBoardContent());
		pstmt.setString(3, bv.getBoardWriter());
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public int updateBoard(Connection conn, BoardVO bv) throws SQLException {

		String sql = "UPDATE JDBC_BOARD"
				+ " SET BOARD_TITLE = ?"
				+ " , BOARD_CONTENT = ?"
				+ " , BOARD_WRITER = ?"
				+ " , BOARD_DATE = SYSDATE";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, bv.getBoardTitle());
		pstmt.setString(2, bv.getBoardContent());
		pstmt.setString(3, bv.getBoardDate());
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public int deleteBoard(Connection conn, int boardNo) throws SQLException {
		
		String sql = "DELETE FROM JDBC_BOARD"
				+ " WHERE BOARD_NO = ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		
		int cnt = pstmt.executeUpdate();
		
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard(Connection conn) throws SQLException {

		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		stmt = conn.createStatement();
		
		rs = stmt.executeQuery("SELECT BOARD_NO"
				+ " , BOARD_TITLE"
				+ " , BOARD_WRITER"
				+ " , BOARD_DATE"
				+ " FROM JDBC_BOARD"
				+ " ORDER BY 1 DESC");
		
		while (rs.next()) {
			BoardVO bv = new BoardVO();
			
			int boardNo = rs.getInt("BOARD_NO");
			String boardTitle = rs.getString("BOARD_TITLE");
			String boardWriter = rs.getString("BOARD_WRITER");
			String boardDate = rs.getString("BOARD_DATE");
			
			bv.setBoardNo(boardNo);
			bv.setBoardTitle(boardTitle);
			bv.setBoardWriter(boardWriter);
			bv.setBoardDate(boardDate);
			
			boardList.add(bv);
		}
		return boardList;
	}

	@Override
	public List<BoardVO> searchTitle(Connection conn, String title) throws SQLException {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		String sql = "SELECT BOARD_NO"
				+ " , BOARD_TITLE"
				+ " , BOARD_WRITER"
				+ " , BOARD_DATE"
				+ " FROM JDBC_BOARD"
				+ " WHERE BOARD_TITLE LIKE '%' || ?"
				+ " OR BOARD_TITLE LIKE ? || '%'"
				+ " OR BOARD_TITLE LIKE '%' || ? || '%'"
				+ " ORDER BY 1 DESC";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, title);
		pstmt.setString(3, title);
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			BoardVO bv2 = new BoardVO();
			
			int boardNo = rs.getInt("BOARD_NO");
			String boardTitle = rs.getString("BOARD_TITLE");
			String boardWriter = rs.getString("BOARD_WRITER");
			String boardDate = rs.getString("BOARD_DATE");
			System.out.println(" " + boardNo + "\t" + boardTitle + "\t" + boardWriter + "\t" + boardDate);
			
			bv2.setBoardNo(boardNo);
			bv2.setBoardTitle(boardTitle);
			bv2.setBoardWriter(boardWriter);
			bv2.setBoardDate(boardDate);
			
			boardList.add(bv2);
		}
		return boardList;
	}

	@Override
	public List<BoardVO> searchWriter(Connection conn, String writer) throws SQLException {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		String sql = "SELECT BOARD_NO"
				+ " , BOARD_TITLE"
				+ " , BOARD_WRITER"
				+ " , BOARD_DATE"
				+ " FROM JDBC_BOARD"
				+ " WHERE BOARD_WRITER LIKE '%' || ?"
				+ " OR BOARD_WRITER LIKE ? || '%'"
				+ " OR BOARD_WRITER LIKE '%' || ? || '%'"
				+ " ORDER BY 1 DESC";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, writer);
		pstmt.setString(2, writer);
		pstmt.setString(3, writer);
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			BoardVO bv2 = new BoardVO();
			
			int boardNo = rs.getInt("BOARD_NO");
			String boardTitle = rs.getString("BOARD_TITLE");
			String boardWriter = rs.getString("BOARD_WRITER");
			String boardDate = rs.getString("BOARD_DATE");
			
			bv2.setBoardNo(boardNo);
			bv2.setBoardTitle(boardTitle);
			bv2.setBoardWriter(boardWriter);
			bv2.setBoardDate(boardDate);
			
			boardList.add(bv2);
		}
		return boardList;
	}

	@Override
	public List<BoardVO> readBoard(Connection conn, int boardNo) throws SQLException {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		String sql = "SELECT BOARD_TITLE "
				+ " , BOARD_WRITER"
				+ " , BOARD_DATE"
				+ " , BOARD_CONTENT"
				+ " FROM JDBC_BOARD"
				+ " WHERE BOARD_NO = ?";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		
		rs = pstmt.executeQuery();
		
		while (rs.next()) {
			BoardVO bv2 = new BoardVO();
			
			String boardTitle = rs.getString("BOARD_TITLE");
			String boardWriter = rs.getString("BOARD_WRITER");
			String boardContent = rs.getString("BOARD_CONTENT");
			String boardDate = rs.getString("BOARD_DATE");
			
			bv2.setBoardNo(boardNo);
			bv2.setBoardTitle(boardTitle);
			bv2.setBoardWriter(boardWriter);
			bv2.setBoardContent(boardContent);
			bv2.setBoardDate(boardDate);
			
			boardList.add(bv2);
		}
		return boardList;
	}
	
	@Override
	public boolean checkWrite(Connection conn, int boardNo) throws SQLException {
		
		boolean chk = false;
		
		String sql = "SELECT COUNT(*) CNT"
				+ " FROM JDBC_BOARD"
				+ " WHERE BOARD_NO = ? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		
		rs = pstmt.executeQuery();
		
		int cnt = 0;
		while (rs.next()) {
			cnt = rs.getInt("cnt");
		}
		if (cnt > 0) {
			chk = true;
		}
		return chk;
	}

	@Override
	public List<BoardVO> searchBoard(Connection conn) throws SQLException {
		return null;
	}

}
