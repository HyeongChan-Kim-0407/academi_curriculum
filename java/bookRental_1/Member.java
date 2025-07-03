package bookRental_1;

public class Member {
	private String mid;
	private String mpw;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public Member(String mid, String mpw) {
		this.mid = mid;
		this.mpw = mpw;
	}

}
