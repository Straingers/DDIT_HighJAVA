package homework.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import homework.board.dao.BoardDaoImpl;
import homework.board.dao.IBoardDao;
import homework.board.vo.BoardVO;
import homework.util.JDBCUtil3;

public class IBoardServiceImpl implements IBoardService{

	private IBoardDao boardDao;
	private Connection conn;
	
	public IBoardServiceImpl() {
		boardDao = new BoardDaoImpl();
	}
	
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			cnt = boardDao.insertBoard(conn, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		try {
			conn = JDBCUtil3.getConnection();
			cnt = boardDao.updateBoard(conn, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		
		try {
			conn = JDBCUtil3.getConnection();
			cnt = boardDao.deleteBoard(conn, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil3.getConnection();
			boardList = boardDao.getAllBoard(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}		
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoard() {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil3.getConnection();
			boardList = boardDao.searchBoard(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		return boardList;
	}

	@Override
	public List<BoardVO> searchTitle(String title) {

		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil3.getConnection();
			boardList = boardDao.searchTitle(conn, title);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		return boardList;
	}

	@Override
	public List<BoardVO> searchWriter(String writer) {

		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil3.getConnection();
			boardList = boardDao.searchWriter(conn, writer);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		return boardList;
	}

	@Override
	public List<BoardVO> readBoard(int boardNo) {
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil3.getConnection();
			boardList = boardDao.readBoard(conn, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		return boardList;
	}

	@Override
	public boolean checkWrite(int boardNo) {
		
		boolean chk = false;
		
		try {
			conn = JDBCUtil3.getConnection();
			chk = boardDao.checkWrite(conn, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil3.disConnect(conn, null, null, null);
		}
		return chk;
	}

}
