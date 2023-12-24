package tw.com.eeit.petforum.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.eeit.petforum.model.bean.Member;
import tw.com.eeit.petforum.service.MemberService;

@WebServlet("/Login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Member loggedInMember = new MemberService().login(email, password);
		HttpSession session = request.getSession();

		if (loggedInMember == null) {
			session.setAttribute("message", "登入失敗，帳號或密碼錯誤");

			response.sendRedirect("login");
		}

		if (loggedInMember != null) {
			session.setAttribute("loggedInMember", loggedInMember);
			response.sendRedirect("index");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
