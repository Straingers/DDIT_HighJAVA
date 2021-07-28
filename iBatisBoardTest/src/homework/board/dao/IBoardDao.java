package homework.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import homework.board.vo.BoardVO;

public interface IBoardDao {

	//등록
	public int insertBoard(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	//수정
	public int updateBoard(SqlMapClient smc, BoardVO bv) throws SQLException;
	
	//삭제
	public int deleteBoard(SqlMapClient smc, int boardNo) throws SQLException;
	
	//목록확인
	public List<BoardVO> getAllBoard(SqlMapClient smc) throws SQLException;
	
	//검색
	public List<BoardVO> searchBoard(SqlMapClient smc) throws SQLException;
	
	//검색-제목검색
	public List<BoardVO> searchTitle(SqlMapClient smc, String title) throws SQLException;
	//검색-작성자검색
	public List<BoardVO> searchWriter(SqlMapClient smc, String writer) throws SQLException;
	
	//상세보기
	public List<BoardVO> readBoard(SqlMapClient smc, int boardNo) throws SQLException;
	
	//글확인
	public boolean checkWrite(SqlMapClient smc, int boardNo) throws SQLException;
	
	
	
	
	
	
}
