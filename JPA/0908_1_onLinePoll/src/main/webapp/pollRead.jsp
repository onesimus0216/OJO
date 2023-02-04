<%@page import="java.util.ArrayList"%>
<%@page import="com.tjoeun.onLinePoll.PollRead"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>투표하기</title>
<!-- favicon: 인터넷 웹 브라우저의 주소창에 표시되는 웹 사이트를 대표하는 이미지이다. -->
<link rel="icon" href="./logo.png">
</head>
<body>

<!-- 투표 항목이 저장된 텍스트 파일의 데이터를 읽어서 웹 브라우저에 출력한다. -->
<%
//	이클립스는 프로젝트를 실행하면 프로젝트를 이클립가 내부적으로 사용하는 웹서버에 복사하고 실행된다.
//	D:\k_digital\web\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps => real path
//	application.getRealPath("/") 메소드를 실행하면 프로젝트가 실행되는 실제(real) 경로(path)를 얻어온다.
//	out.println(application.getRealPath("/") + "poll.txt"); // "/"는 web root(홈페이지의 최초 진입 경로)를 의미한다.
	String filepath = application.getRealPath("/") + "poll.txt";
//	투표 내용이 저장된 텍스트 파일의 데이터를 읽어오는 메소드를 실행한다.
	ArrayList<String> poll = PollRead.pollRead(filepath);
//	out.println(poll);
//	for (String str : poll) {
//		out.println(str + "<br>");
//	}
//	투표 항목의 개수
	int itemCount = (poll.size() - 1) / 2;
//	out.println(itemCount);
%>

<form action="pollWrite.jsp" method="post">

	<!--
		cellpadding: 셀 안여백, 셀을 구성하는 선과 셀 내부 문자와의 간격
		cellspacing: 셀과 셀 사이의 간격
	-->

	<table width="500" border="1" align="center" cellpadding="5" cellspacing="0"> <!-- 표 만들기 -->
		<tr> <!-- 줄 -->
			<th> <!-- 칸, 표의 첫줄을 구성하는 칸, 굵게 및 가운데 정렬되서 표시된다. -->
				<%=poll.get(0)%>
			</th>
		</tr>
		
	<%
	//	투표 항목의 개수만큼 반복하며 radio와 투표 항목을 출력한다.
		for (int i=1; i<=itemCount; i++) {
	%>
		<tr>
			<td> <!-- 칸, 표의 첫줄을 제외한 나머지 구성하는 칸 -->
				<input type="radio" name="poll" value="<%=i%>"><%=poll.get(i)%>
			</td>
		</tr>
	<%
		}
	%>
		<!-- 투표하기 버튼과 결과보기 버튼을 만든다. -->
		<tr>
			<td align="center">
				<input type="submit" value="투표하기">
				<input type="button" value="결과보기" onclick="location.href='pollResult.jsp'">
			</td>
		</tr>
	</table>
	
</form>

</body>
</html>


















