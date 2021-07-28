<%@page import="homework.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVO bv = (BoardVO)request.getAttribute("board");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시물</title>
</head>
<body>
	<div>
		번호 : <%=bv.getBoardNo() %><br><br>
		작성자 : <%=bv.getBoardWriter() %><br><br>
		작성일 : <%=bv.getBoardDate() %><br>
		 <h3 align="center">&lt; <%=bv.getBoardTitle() %> &gt;</h3> 
		<p style="border:1px solid; width: calc(100% - 40px); height: 500px; padding: 20px">
			<%=bv.getBoardContent() %>
		</p>
		<br>
	</div>
	<div align="right">
		<a href="updateBoard.do?boardNo=<%=bv.getBoardNo() %>">
			<input type="button" value="수정" style="margin-right: 10px;">
		</a>
		<a href="deleteBoard.do?boardNo=<%=bv.getBoardNo() %>">
			<input type="button" value="삭제" style="margin-right: 10px;">
		</a>
		<a href="boardList.do">
			<input type="button" value="목록" style="margin-right: 30px;">
		</a>
	</div>
</body>
</html>