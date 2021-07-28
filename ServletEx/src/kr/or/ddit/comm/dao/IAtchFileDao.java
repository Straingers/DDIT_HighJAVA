package kr.or.ddit.comm.dao;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.vo.AtchFileVO;


/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성하여
 * service에 전달하는 DAO의 interface
 *
 * @author sam
 *
 */
public interface IAtchFileDao {


	/**
	 * 첨부파일 저장
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public int insertAtchFile(AtchFileVO atchFileVO) throws SQLException;

	/**
	 * 첨부파일 세부정보 저장
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public int insertAtchFileDetail(AtchFileVO atchFileVO) throws SQLException;

	/**
	 * 첨부파일목록 조회하기
	 * @param fileVO
	 * @return
	 * @throws SQLException
	 */
	public List<AtchFileVO> getAtchFileList(AtchFileVO fileVO) throws SQLException;

	/**
	 * 첨부파일 세부정보 조회 메서드
	 *
	 * @param atchFileVO 검색할 첨부파일 정보(아이디 및 순번)
	 * @return AtchFileVO 해당 첨부파일로 검색한 첨부파일정보
	 * @throws SQLException
	 */
	public AtchFileVO getAtchFileDetail (AtchFileVO atchFileVO) throws SQLException;
}





