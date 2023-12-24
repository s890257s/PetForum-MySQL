package tw.com.eeit.petforum.controller.page;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.eeit.petforum.model.bean.Likes;
import tw.com.eeit.petforum.model.bean.Member;
import tw.com.eeit.petforum.model.bean.Pet;
import tw.com.eeit.petforum.service.MemberService;
import tw.com.eeit.petforum.util.PathConverter;

@WebServlet("/pets")
public class ToPets extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberService memberService = new MemberService();

		List<Pet> pets = memberService.getAllPets();

		HttpSession session = request.getSession();
		Member loggedInMember = (Member) session.getAttribute("loggedInMember");
		if (loggedInMember != null) {

			List<Likes> likesByMemberID = memberService.getAllLikesRecordByMemberID(loggedInMember.getmID());

			List<Integer> likedPetIDs = likesByMemberID.stream().map(like -> like.getPet().getpID()).toList();

			for (Pet p : pets) {
				if (likedPetIDs.contains(p.getpID())) {
					p.setLiked(true);
				}
			}

		}
		request.setAttribute("pets", pets);

		request.getRequestDispatcher(PathConverter.convertToWebInfPath(request.getServletPath())).forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
