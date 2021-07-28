package homework.board.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import homework.board.vo.BoardVO;
import homework.util.SqlMapClientUtil;

public class BoardDaoImpl implements IBoardDao{
	
	private static IBoardDao boardDao;
	
	private BoardDaoImpl() {
		SqlMapClientUtil.getInstance();
	}
	
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	
	@Override
	public int insertBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		int cnt = 0;
		
		Object obj = smc.insert("board.insertBoard", bv);
		
		if(obj == null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int updateBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		int cnt = smc.update("board.updateBoard", bv);
		
		return cnt;
	}

	@Override
	public int deleteBoard(SqlMapClient smc, int boardNo) throws SQLException {
		int cnt = smc.delete("board.deleteBoard", boardNo);
		
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard(SqlMapClient smc) throws SQLException {
		List<BoardVO> boardList = smc.queryForList("board.getAllBoard");
		
		return boardList;
	}

	@Override
	public List<BoardVO> searchTitle(SqlMapClient smc, String title) throws SQLException {
		List<BoardVO> boardList = smc.queryForList("board.searchTitle", title);
		
		return boardList;
	}

	@Override
	public List<BoardVO> searchWriter(SqlMapClient smc, String writer) throws SQLException {
		List<BoardVO> boardList = smc.queryForList("board.searchWriter", writer);
		
		return boardList;
	}

	@Override
	public List<BoardVO> readBoard(SqlMapClient smc, int boardNo) throws SQLException {
		List<BoardVO> boardList = smc.queryForList("board.readBoard", boardNo);
		
		return boardList;
	}
	
	@Override
	public boolean checkWrite(SqlMapClient smc, int boardNo) throws SQLException {
		boolean chk = false;
		
		int cnt = (int) smc.queryForObject("board.checkBoard", boardNo);
		
		if (cnt > 0) {
			chk = true;
		}
		return chk;
	}

	@Override
	public List<BoardVO> searchBoard(SqlMapClient smc) throws SQLException {
		return null;
	}

}
