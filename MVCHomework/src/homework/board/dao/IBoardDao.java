package homework.board.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import homework.board.vo.BoardVO;

public interface IBoardDao {

	//등록
	public int insertBoard(Connection conn, BoardVO bv) throws SQLException;
	
	//수정
	public int updateBoard(Connection conn, BoardVO bv) throws SQLException;
	
	//삭제
	public int deleteBoard(Connection conn, int boardNo) throws SQLException;
	
	//목록확인
	public List<BoardVO> getAllBoard(Connection conn) throws SQLException;
	
	//검색
	public List<BoardVO> searchBoard(Connection conn) throws SQLException;
	
	//검색-제목검색
	public List<BoardVO> searchTitle(Connection conn, String title) throws SQLException;
	//검색-작성자검색
	public List<BoardVO> searchWriter(Connection conn, String writer) throws SQLException;
	
	//상세보기
	public List<BoardVO> readBoard(Connection conn, int boardNo) throws SQLException;
	
	//글확인
	public boolean checkWrite(Connection conn, int boardNo) throws SQLException;
	
	
	
	
	
	
}
