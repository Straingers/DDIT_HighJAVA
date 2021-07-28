package kr.or.ddit.member.handler;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import kr.or.ddit.comm.handler.CommandHandler;
import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.AtchFileVO;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.FileUploadRequestWrapper;

public class UpdateMemberHandler implements CommandHandler {

	private static final String VIEW_PAGE = "/WEB-INF/view/member/updateForm.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) { // GET 방식인 경우...
			return false;
		} else { // POST 방식인 경우...
			return true;
		}
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getMethod().equals("GET")) { // GET 방식인 경우...
			
			String memId = req.getParameter("memId");
			
			// 1. 회원정보 조회
			IMemberService service = MemberServiceImpl.getInstance();
			
			MemberVO mv = service.getMember(memId);

			if(mv.getAtchFileId() > 0) { // 첨부파일이 존재하면...
				// 첨부파일 정보 조회
				AtchFileVO fileVO = new AtchFileVO();
				
				fileVO.setAtchFileId(mv.getAtchFileId());
				
				IAtchFileService fileService = AtchFileServiceImpl.getInstance();
				
				List<AtchFileVO> atchFileList = fileService.getAtchFileList(fileVO);
				
				req.setAttribute("atchFileList", atchFileList);
				
			}
			
			// 2. 모델정보 등록
			req.setAttribute("memVO", mv);
			
			return VIEW_PAGE;
			
		} else { // POST 방식인 경우...
			
			FileItem item = ((FileUploadRequestWrapper)req).getFileItem("atchFile");
			
			AtchFileVO atchFileVO = new AtchFileVO();
			
			// 기존의 첨부파일 아이디 정보 가져오기
			atchFileVO.setAtchFileId(req.getParameter("atchFile") == null ? 
						-1 : Long.parseLong(req.getParameter("atchFile")));
			
			if(item != null && !item.getName().equals("")) {
				
				IAtchFileService fileService = AtchFileServiceImpl.getInstance();
				
				atchFileVO = fileService.saveAtchFile(item); // 첨부파일 저장
			}
			
			// 1. 요청파리미터 정보 가져오기
			String memId = req.getParameter("memId");
			String memName = req.getParameter("memName");
			String memTel = req.getParameter("memTel");
			String memAddr = req.getParameter("memAddr");
			
			// 2. 서비스 객체 생성하기
			IMemberService memberService = MemberServiceImpl.getInstance();
			
			// 3. 회원정보 수정하기
			MemberVO mv = new MemberVO();
			mv.setMemId(memId);
			mv.setMemName(memName);
			mv.setMemTel(memTel);
			mv.setMemAddr(memAddr);
			mv.setAtchFileId(atchFileVO.getAtchFileId());
			
			int cnt = memberService.updateMember(mv);
			
			String msg = "";
			if(cnt > 0) {
				msg = "성공";
			} else {
				msg = "실패";
			}
			
			// 4. 수정 후 글로 이동
			String redirectUrl = req.getContextPath() + "/member/list.do?msg=" 
								+ URLEncoder.encode(msg, "UTF-8");
			
			return redirectUrl;
		}
	}
}
