package tw.com.eeit.petforum.model.bean;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Base64;

public class Pet {

	private int pID;
	private String type;
	private String pName;
	private int pAge;
	private byte[] pPhoto;
	private Member member;
	private String pPhotoBase64;

	public Pet() {
	}

	public Pet(String pName, String type, int pAge, byte[] pPhoto) {
		this.pName = pName;
		this.type = type;
		this.pAge = pAge;
		this.pPhoto = pPhoto;
	}

	public Pet(String pName, String type, int pAge, byte[] pPhoto, Member member) {
		this.pName = pName;
		this.type = type;
		this.pAge = pAge;
		this.pPhoto = pPhoto;
		this.member = member;
	}

	public Pet(int pID) {
		super();
		this.pID = pID;
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getpAge() {
		return pAge;
	}

	public void setpAge(int pAge) {
		this.pAge = pAge;
	}

	public byte[] getpPhoto() {
		return pPhoto;
	}

	public void setpPhoto(byte[] pPhoto) {

		this.pPhoto = pPhoto;

		String base64String = Base64.getEncoder().encodeToString(pPhoto);

		try {
			String guessContentTypeFromStream = URLConnection
					.guessContentTypeFromStream(new ByteArrayInputStream(pPhoto));

			this.pPhotoBase64 = "data:%s;base64,".formatted(guessContentTypeFromStream) + base64String;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getpPhotoBase64() {
		return pPhotoBase64;
	}

	public void setpPhotoBase64(String pPhotoBase64) {
		this.pPhotoBase64 = pPhotoBase64;
	}

	@Override
	public String toString() {
		String m = member == null ? "null" : member.toString();

		return "Pet [pID=" + pID + ", type=" + type + ", pName=" + pName + ", pAge=" + pAge + ", member=" + m + "]";
	}

}
