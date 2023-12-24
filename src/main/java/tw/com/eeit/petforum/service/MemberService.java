package tw.com.eeit.petforum.service;

import java.sql.Connection;
import java.util.List;

import tw.com.eeit.petforum.model.bean.Likes;
import tw.com.eeit.petforum.model.bean.Member;
import tw.com.eeit.petforum.model.bean.Pet;
import tw.com.eeit.petforum.model.dao.LikesDAO;
import tw.com.eeit.petforum.model.dao.MemberDAO;
import tw.com.eeit.petforum.model.dao.PetDAO;
import tw.com.eeit.petforum.util.ConnectionFactory;

public class MemberService {

	/**
	 * 呼叫此方法並傳入Likes物件(須包含日期、會員ID、寵物ID)以切換「按讚」狀態。<br>
	 * 1.若資料庫有按讚記錄則移除<br>
	 * 2.若資料庫無按讚記錄則增加<br>
	 * 
	 * @param likes 單筆按讚記錄，須包含日期、會員ID、寵物ID
	 * @return "like!" : 若是新增按讚記錄到資料庫 ； "unlike!" : 若是從資料庫刪除按讚記錄 ； "error!" : 未預期的錯誤
	 */
	public String switchLikeStatus(Likes likes) {
		try (Connection conn = ConnectionFactory.getConnection()) {

			LikesDAO likesDAO = new LikesDAO(conn);
			Likes likesFromDB = likesDAO.findLikeByMemberIDAndPetID(likes);

			if (likesFromDB == null) {
				likesDAO.addLike(likes);
				return "like!";
			}

			if (likesFromDB != null) {
				likesDAO.removeLike(likes);
				return "unlike!";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error!";
	}

	public List<Pet> getAllPets() {

		try (Connection conn = ConnectionFactory.getConnection()) {

			PetDAO petDAO = new PetDAO(conn);

			List<Pet> pets = petDAO.findAllPetWithMember();
			return pets;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Member login(String email, String password) {

		try (Connection conn = ConnectionFactory.getConnection()) {

			MemberDAO memberDAO = new MemberDAO(conn);

			Member loggedInMember = memberDAO.findMemberByEmailAndPassword(email, password);

			return loggedInMember;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Member getMemberByID(int memberID) {
		try (Connection conn = ConnectionFactory.getConnection()) {

			MemberDAO memberDAO = new MemberDAO(conn);

			Member member = memberDAO.findMemberWithPetByID(memberID);

			return member;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
