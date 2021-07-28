package homework.board.service;

import java.util.List;

import homework.board.vo.BoardVO;

public interface IBoardService {

	public int insertBoard(BoardVO bv);
	
	public int updateBoard(BoardVO bv);
	
	public int deleteBoard(int boardNo);
	
	public List<BoardVO> getAllBoard();
	
	public List<BoardVO> searchBoard();
	
	public List<BoardVO> searchTitle(String title);
	public List<BoardVO> searchWriter(String Writer);
	
	public List<BoardVO> readBoard(int boardNo);
	
	public boolean checkWrite(int boardNo);
	
}
