<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="com.javaex.vo.PersonVo"%>    

<% PersonVo upDateOne = (PersonVo)request.getAttribute("upDateOne"); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>전화번호 수정</h1>
<p>수정화면 입니다. 아래 항목을 수정하고 수정버튼을 눌러주세요</p>

<form action="/phonebook2/pbc" method="get">
이름: 	<input type="text" name="name" value="<%= upDateOne.getName() %>"> <br> 
핸드폰: <input type="text" name="hp" value="<%= upDateOne.getHp() %>"> <br> 
회사: 	<input type="text" name="company" value="<%= upDateOne.getCompany() %>"> <br> 
*히든(수정)		<input type="text" name="id" value="<%= upDateOne.getPersonId() %>"> <br>
		<input type="text" name="action" value="update">
<button type="submit">수정</button>
</form>


</body>
</html>