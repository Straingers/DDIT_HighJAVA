<%@page import="homework.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Integer boardNo = (Integer)request.getAttribute("boardNo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 삭제</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<script type="text/javascript">
	$(document).ready(function(){
		if(confirm("정말 삭제하시겠습니까?")){
			var fm = document.getElementById("fm");
			fm.action = "deleteBoard.do?boardNo=<%=boardNo %>";
			fm.method = "post";
			fm.submit();
		}
			return;
	})
	</script>
	<form id="fm">
	</form>
</body>
</html>