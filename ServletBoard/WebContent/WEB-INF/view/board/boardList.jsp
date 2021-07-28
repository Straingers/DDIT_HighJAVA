<%@page import="homework.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<BoardVO> boardList = (List<BoardVO>)request.getAttribute("boardList");

	String msg = request.getParameter("msg") == null ? "" : request.getParameter("msg"); 
	
	String result = request.getParameter("result");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	table {
		border-collapse: collapse;
		width: 100%;
	}
	
	th {
		background-color: lightgray;
	}
	
	th, td {
		border: 1px solid;
		padding : 5px;
	}
	
	#title {
		width: 60%;
	}
</style>
<title>게시판 목록</title>
</head>
<body>
	<h2 align="center">게 시 판</h2>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>	
	<%
		int boardSize = boardList.size();
	
		if(boardSize > 0){
			for(int i = 0; i < boardSize; i++) {
	%>
		<tr>
			<td><%=boardList.get(i).getBoardNo() %></td>
			<td>
				<a href="board.do?boardNo=<%=boardList.get(i).getBoardNo() %>">
					<%=boardList.get(i).getBoardTitle() %>
				</a>
			</td>
			<td><%=boardList.get(i).getBoardWriter() %></td>
			<td><%=boardList.get(i).getBoardDate() %></td>
		</tr>
	<%
			}
		} else {
	%>
		<tr>
			<td colspan="4">작성한 글이 없습니다.</td>
		</tr>
	<%		
		}
	%>
	</table>
	<br>
	<div align="right">
		<a href="insertBoard.do">
			<input type="button" value="새 글 작성" style="margin-right: 30px;">
		</a>
	</div>
	<%
		if(msg.equals("성공")) {
	%>
	<script type="text/javascript">
		alert('<%=result %>');
	</script>
	<% 
		}
	%>
</body>
</html>