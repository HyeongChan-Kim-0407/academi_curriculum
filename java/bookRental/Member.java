package bookRental;

public class Member {
	
	private String mId;
	private String mPw;
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmPw() {
		return mPw;
	}
	public void setmPw(String mPw) {
		this.mPw = mPw;
	}
	
	public Member(String mId, String mPw) {
		this.mId = mId;
		this.mPw = mPw;
	}

}
