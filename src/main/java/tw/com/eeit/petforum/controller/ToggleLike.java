package tw.com.eeit.petforum.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.eeit.petforum.model.bean.Likes;
import tw.com.eeit.petforum.model.bean.Member;
import tw.com.eeit.petforum.service.MemberService;

@WebServlet("/ToggleLike.do")
public class ToggleLike extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Member loggedInMember = (Member) session.getAttribute("loggedInMember");
		int memerID = loggedInMember.getmID();

		Integer petID = Integer.valueOf(request.getParameter("pID"));

		Likes likes = new Likes(memerID, petID);

		MemberService memberService = new MemberService();

		String status = memberService.switchLikeStatus(likes);

		response.setContentType("text/plain;charset=UTF-8");
		response.getWriter().write(status);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
