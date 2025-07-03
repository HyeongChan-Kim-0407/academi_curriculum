package memberOrder;

//import java.util.ArrayList;

public class Member {
	
	private String mId;		//회원 아이디
	private String mPw;		//회원 비밀번호
	private String mName;	//회원 이름
	
	//private ArrayList<Order> orderList;
	
	
	
	public Member(String mId, String mPw, String mName) {
		super();
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
	}
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
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}

}
