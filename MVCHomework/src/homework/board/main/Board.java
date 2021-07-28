package homework.board.main;

import java.util.List;
import java.util.Scanner;

import homework.board.service.IBoardService;
import homework.board.service.IBoardServiceImpl;
import homework.board.vo.BoardVO;

/**
위의 테이블을 작성하고 게시판을 관리하는
다음 기능들을 구현하시오.

기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
 
------------------------------------------------------------

게시판 테이블 구조 및 시퀀스

create table jdbc_board(
    board_no number not null,  -- 번호(자동증가)
    board_title varchar2(100) not null, -- 제목
    board_writer varchar2(50) not null, -- 작성자
    board_date date not null,   -- 작성날짜
    board_content clob,     -- 내용
    constraint pk_jdbc_board primary key (board_no)
);
create sequence board_seq
    start with 1   -- 시작번호
    increment by 1; -- 증가값

----------------------------------------------------------

 시퀀스의 다음 값 구하기
 시퀀스이름.nextVal
 */
public class Board {
	
	private IBoardService boardService;
	
	public Board() {
		boardService = new IBoardServiceImpl();
	}
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴 출력 메서드
	 */
	public void menu() {
		while(true) {
			System.out.println();
			System.out.println("==========================");
			System.out.println("\t     게 시 판");
			System.out.println("--------------------------");
			System.out.println("[1]글 목록");
			System.out.println("[2]새 글 작성");
			System.out.println("[3]검색");
			System.out.println("[4]종료");
			System.out.println("--------------------------");
			System.out.print("입력 >> ");
			int input = scan.nextInt(); // 메뉴 번호 입력
			switch (input) {
				case 1: // 목록
					showBoard();
					break;
				case 2: // 새 글 입력
					insertBoard();
					break;
				case 3: // 검색
					searchBoard();
					break;
				case 4: // 종료
					System.out.println("이용해주셔서 감사합니다.");
					System.exit(0);
				default:
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("다시 입력해주세요.");
			}
		}
	}

	/**
	 * 목록을 보여주는 메서드
	 */
	private void showBoard() {
		while(true) {
			System.out.println();
			System.out.println("=========================================");
			System.out.println(" 번호\t제목\t작성자\t작성일");
			System.out.println("-----------------------------------------");
			
			List<BoardVO> boardList = boardService.getAllBoard();
			for(BoardVO bv : boardList) {
				System.out.println(" " + bv.getBoardNo() + "\t" + bv.getBoardTitle()
								+ "\t" + bv.getBoardWriter() + "\t" + bv.getBoardDate());
			}
			System.out.println("-----------------------------------------");
			System.out.println("[1]글 확인\t[0]돌아가기");
			System.out.print("입력 >> ");
			int input = scan.nextInt();
			switch (input) {
				case 1: // 글 확인
					readBoard();
					break;
				case 0:
					return;
				default:
					System.out.println("잘못 입력하셨습니다.");
					System.out.println("다시 입력해주세요.");
			}
		}
	}
	
	/**
	 * 새 글 작성하는 메서드
	 */
	private void insertBoard() {
		System.out.println();
		System.out.println("--------- 새 글 작성 ---------");
		System.out.print("제목 >> ");
		String boardTitle = scan.next();
		scan.nextLine();
		System.out.print("내용 >> ");
		String boardContent = scan.nextLine();
		System.out.print("작성자 >> ");
		String boardWriter = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardContent(boardContent);
		bv.setBoardWriter(boardWriter);		
		
		int cnt = boardService.insertBoard(bv);
		
		if (cnt > 0) {
			System.out.println();
			System.out.println("글 작성 성공");
		} else {
			System.out.println();
			System.out.println("글 작성 실패!");
		}
		return;
	}
	
	/**
	 * 글 검색하는 메서드
	 */
	private void searchBoard() {
		System.out.println();
		System.out.println("[1]'제목'으로 검색");
		System.out.println("[2]'작성자'로 검색");
		System.out.println("[0]돌아가기");
		System.out.print("입력 >> ");
		int input = scan.nextInt();
		switch (input) {
			case 1: // 제목으로 검색
				searchTitle();
				break;
			case 2: // 작성자로 검색
				searchWriter();
				break;
			case 0:
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				System.out.println("다시 입력해 주세요.");
				break;
		}
	}

	/**
	 * 글검색 -> 제목검색
	 */
	private void searchTitle() {
		
		boolean chk = false;
		
		System.out.println("제목으로 검색");
		System.out.print("입력 >> ");
		String title = scan.next();
		System.out.println();
		System.out.println("=========================================");
		System.out.println(" 번호\t제목\t작성자\t작성일");
		System.out.println("-----------------------------------------");
		
		List<BoardVO> boardList = boardService.searchTitle(title);
		for(BoardVO bv : boardList) {
			System.out.println(" " + bv.getBoardNo() + "\t" + bv.getBoardTitle()
							+ "\t" + bv.getBoardWriter() + "\t" + bv.getBoardDate());
			chk = boardService.checkWrite(bv.getBoardNo());
		}
		if(chk == false) {
			System.out.println("검색한 제목의 글이 없습니다.");
			System.out.println("-----------------------------------------");
			return;
		}
		System.out.println("-----------------------------------------");
		System.out.println("[1]글 확인\t[0]돌아가기");
		System.out.print("입력 >> ");
		int input = Integer.parseInt(scan.nextLine());
		switch (input) {
		case 1: // 글 확인
			readBoard();
			break;
		case 0:
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
			System.out.println("다시 입력해주세요.");
		}
		System.out.println("-----------------------------------------");
	}
	
	/**
	 * 글 검색 -> 작성자로 검색
	 */
	private void searchWriter() {
		boolean chk = false;
		scan.nextLine();
		System.out.println("작성자로 검색");
		System.out.print("입력 >> ");
		String writer = scan.nextLine();
		System.out.println("=========================================");
		System.out.println(" 번호\t제목\t작성자\t작성일");
		System.out.println("-----------------------------------------");
		
		List<BoardVO> boardList = boardService.searchWriter(writer);
		for(BoardVO bv : boardList) {
			System.out.println(" " + bv.getBoardNo() + "\t" + bv.getBoardTitle()
							+ "\t" + bv.getBoardWriter() + "\t" + bv.getBoardDate());
			chk = boardService.checkWrite(bv.getBoardNo());
		}
		if(chk == false) {
			System.out.println("검색한 제목의 글이 없습니다.");
			System.out.println("-----------------------------------------");
			return;
		}
		
		System.out.println("-----------------------------------------");
		System.out.println("[1]글 확인\t[0]돌아가기");
		System.out.print("입력 >> ");
		int input = scan.nextInt();
		switch (input) {
		case 1: // 글 확인
			readBoard();
			break;
		case 0:
			return;
		default:
			System.out.println("잘못 입력하셨습니다.");
			System.out.println("다시 입력해주세요.");
		}
		System.out.println("-----------------------------------------");
	}

	/**
	 * 선택한 게시글을 확인하는 메서드
	 */
	private void readBoard() {
		boolean chk = false;
		int input = 0;
		do {
			System.out.println("");
			System.out.println("확인할 글 번호를 입력해주세요.");
			System.out.print("입력 >> ");
			input = scan.nextInt();
		
			chk = boardService.checkWrite(input);
			
			if (chk == false) {
				System.out.println(input + "번 게시글이 존재하지 않습니다.");
				System.out.println("다시 입력해 주세요.");
			}
		} while (chk == false);
		System.out.println();
		
		System.out.println("--------------------------------------");
		List<BoardVO> board = boardService.readBoard(input);
		for(BoardVO bv : board) {
			System.out.println("번호\t: " + bv.getBoardNo());
			System.out.println("작성자\t: " + bv.getBoardWriter());
			System.out.println("작성일\t: " + bv.getBoardDate());
			System.out.println("제목\t: " + bv.getBoardTitle());
			System.out.println("내용\t: " + bv.getBoardContent());
			System.out.println("--------------------------------------");
		}
		System.out.println("[1]수정\t[2]삭제\t[0]돌아가기");
		System.out.print("입력 >> ");
		int input2 = scan.nextInt();
		switch (input2) {
			case 1: // 글 수정
				updateBoard(input);
				break;
			case 2: // 글 삭제
				deleteBoard(input);
				break;
			case 0:
				return;
			default:
				System.out.println("잘못 입력 하였습니다.");
				System.out.println("다시 입력해주세요.");
		}
	}

	/**
	 * 게시글을 삭제하는 메서드
	 */
	private void deleteBoard(int boardNo) {
		System.out.println("");
		System.out.println("정말 삭제하시겠습니까?");
		System.out.println("[1]네\t[2]아니오");
		System.out.println("입력 >> ");
		int input = scan.nextInt();
		
		switch (input) {
			case 1:
				int cnt = boardService.deleteBoard(boardNo);
				if (cnt > 0) {
					System.out.println(boardNo + "번 게시글 삭제 성공");
				} else {
					System.out.println(boardNo + "번 게시글 삭제 실패!");
				}
				return;
			case 2:
				return;
			default:
				System.out.println("잘못입력하였습니다.");
				System.out.println("다시 입력해 주세요.");
		}
		
	}

	/**
	 * 글 수정하는 메서드
	 */
	private void updateBoard(int boardNo) {
		
		scan.nextLine();
		System.out.println("수정할 제목을 입력하세요.");
		System.out.print("입력 >> ");
		String boardTitle = scan.nextLine();
		scan.nextLine();
		System.out.println("수정할 내용을 입력하세요.");
		System.out.println("내용 >> ");
		String boardContent = scan.nextLine();
		System.out.print("작성자 >> ");
		String boardWriter = scan.next();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardContent(boardContent);
		bv.setBoardWriter(boardWriter);
		bv.setBoardDate("SYSDATE");
		
		int cnt = boardService.updateBoard(bv);
		
		if (cnt > 0) {
			System.out.println(boardNo + "번 게시글 수정 성공");
		} else {
			System.out.println(boardNo + "번 게시글 수정 실패!");
		}
	}
	
	/**
	 * 메인 메서드
	 * @param args
	 */
	public static void main(String[] args) {
		new Board().menu();
	}
}
