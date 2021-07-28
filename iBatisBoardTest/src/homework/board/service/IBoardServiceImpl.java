package homework.board.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import homework.board.dao.BoardDaoImpl;
import homework.board.dao.IBoardDao;
import homework.board.vo.BoardVO;
import homework.util.SqlMapClientUtil;

public class IBoardServiceImpl implements IBoardService{

	private IBoardDao boardDao;
	private SqlMapClient smc;
	
	private static IBoardService boradservice;
	
	private IBoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
		smc = SqlMapClientUtil.getInstance();
	}
	
	public static IBoardService getInstence() {
		if (boradservice == null) {
			boradservice = new IBoardServiceImpl();
		}
		return boradservice;
	}
	
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		try {
			cnt = boardDao.insertBoard(smc, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		try {
			cnt = boardDao.updateBoard(smc, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			cnt = boardDao.deleteBoard(smc, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			boardList = boardDao.getAllBoard(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoard() {
		List<BoardVO> boardList = new ArrayList<>();
		try {
			boardList = boardDao.searchBoard(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public List<BoardVO> searchTitle(String title) {
		List<BoardVO> boardList = new ArrayList<>();
		try {
			boardList = boardDao.searchTitle(smc, title);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return boardList;
	}

	@Override
	public List<BoardVO> searchWriter(String writer) {
		List<BoardVO> boardList = new ArrayList<>();
			try {
				boardList = boardDao.searchWriter(smc, writer);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return boardList;
	}

	@Override
	public List<BoardVO> readBoard(int boardNo) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			boardList = boardDao.readBoard(smc, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return boardList;
	}

	@Override
	public boolean checkWrite(int boardNo) {
		boolean chk = false;
		
		try {
			chk = boardDao.checkWrite(smc, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return chk;
	}

}
