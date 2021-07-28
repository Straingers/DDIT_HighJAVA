<%@page import="homework.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardVO bv = (BoardVO) request.getAttribute("bv");
%>   
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	input {
		width: 300px;
	}
</style>
<title>게시글 수정</title>
</head>
<body>
	<form action="updateBoard.do?boardNo=<%=bv.getBoardNo() %>" method="post">
		<h2>게시글 수정</h2>
		<input type="hidden" name="boardWriter" value="<%=bv.getBoardWriter() %>">
		<table>
			<tr>
				<td>제   목 : </td>
				<td><input type="text" name="boardTitle" value="<%=bv.getBoardTitle() %>"></td>
			</tr>
			<tr>
				<td>내   용 : </td>
				<td><textarea rows="10" cols="45" name="boardContent"><%=bv.getBoardContent() %></textarea></td>
			</tr>
		</table>
		<br>
		<button type="submit">수정</button>
	</form>
</body>
</html>