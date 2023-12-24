package tw.com.eeit.petforum.controller.page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.eeit.petforum.model.bean.Member;
import tw.com.eeit.petforum.service.MemberService;
import tw.com.eeit.petforum.util.PathConverter;

@WebServlet("/profile")
public class ToProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int memberID = -1;

		HttpSession session = request.getSession();
		Member loggedInMember = (Member) session.getAttribute("loggedInMember");

		if (loggedInMember != null) {
			memberID = loggedInMember.getmID();
		}

		String targetMemberID = request.getParameter("mID");

		if (targetMemberID != null) {
			memberID = Integer.valueOf(targetMemberID);
		}

		MemberService memberService = new MemberService();
		Member member = memberService.getMemberByID(memberID);

		if (member == null) {
			response.sendRedirect("index");
			return;
		}

		request.setAttribute("member", member);

		request.getRequestDispatcher(PathConverter.convertToWebInfPath(request.getServletPath())).forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
