package com.tjoeun.regiater;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserRegister() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("UserRegister 서블릿의 doGet() 메소드 실행");
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("UserRegister 서블릿의 doPost() 메소드 실행");
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// System.out.println("UserRegister 서블릿의 actionDo() 메소드 실행");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String userID = request.getParameter("userID").trim();
		String userPassword1 = request.getParameter("userPassword1").trim();
		String userPassword2 = request.getParameter("userPassword2").trim();
		String userName = request.getParameter("userName").trim();
		String userAge = request.getParameter("userAge").trim();
		String userGender = request.getParameter("userGender");
		String userEmail = request.getParameter("userEmail").trim();
		
		if (userID == null || userID.equals("") ||
			userPassword1 == null || userPassword1.equals("") ||
			userPassword2 == null || userPassword2.equals("") ||
			userName == null || userName.equals("") ||
			userAge == null || userAge.equals("") ||
			userGender == null || userGender.equals("") ||
			userEmail == null || userEmail.equals("")) {
			// request.getSession().setAttribute("messageType", "오류 메시지"); // ①
			// request.getSession().setAttribute("messageContent", "모든 내용을 입력하세요."); // ①
			// response.sendRedirect("index.jsp"); // ①
			
			// 입력 체크 성공 여부에 따른 메시지를 ajax로 넘겨준다.
			response.getWriter().write("1"); // ②
			return;
		}
		
		if (!userPassword1.equals(userPassword2)) {
			// request.getSession().setAttribute("messageType", "오류 메시지"); // ①
			// request.getSession().setAttribute("messageContent", "비밀번호가 일치하지 않습니다."); // ①
			// response.sendRedirect("index.jsp"); // ①
			
			// 비밀번호 체크 성공 여부에 따른 메시지를 ajax로 넘겨준다.
			response.getWriter().write("2"); // ②
			return;
		}
		
		RegisterVO vo = new RegisterVO(userID, userPassword2, userName, Integer.parseInt(userAge), 
				userGender, userEmail);
		// System.out.println(vo);
		int result = new RegisterDAO().register(vo);
		// System.out.println(result);
		
		if (result == 1) {
			// request.getSession().setAttribute("messageType", "성공 메시지"); // ①
			// request.getSession().setAttribute("messageContent", "회원가입에 성공했습니다."); // ①
			
			// insert sql 명령이 정상적으로 실행되었을 때 메시지를 ajax로 넘겨준다.
			response.getWriter().write("3"); // ②
		} else {
			// request.getSession().setAttribute("messageType", "오류 메시지"); // ①
			// request.getSession().setAttribute("messageContent", "이미 존재하는 아이디입니다."); // ①
			
			// insert sql 명령이 정상적으로 실행되지 않았을 때 메시지를 ajax로 넘겨준다.
			response.getWriter().write("4"); // ②
		}
		// response.sendRedirect("index.jsp"); // ①
	}
	
}















