<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	input {
		width: 300px;
	}
</style>
<title>게시글 등록</title>
</head>
<body>
	<form action="insertBoard.do" method="post">
		<h2>게시글 등록</h2>
		<table>
			<tr>
				<td>제   목 : </td>
				<td><input type="text" name="boardTitle" value=""></td>
			</tr>
			<tr>
				<td>작성자 : </td>
				<td><input type="text" name="boardWriter" value=""></td>
			</tr>
			<tr>
				<td>내   용 : </td>
				<td><textarea rows="10" cols="45" name="boardContent"></textarea></td>
			</tr>
		</table>
		<br>
		<button type="submit">등록</button>
	</form>
</body>
</html>