package jdbcBook;

public class Member {
	
	private String mid;
	private String mpw;
	private String memail;
	private String mphone;
	private String maddr;
	private String mjoindate;
	
	private int totalRental;
	private int rental;
	
	
	public Member(String mid, String mpw, String memail, String mphone, String maddr, String mjoindate, int totalRental,
			int rental) {
		super();
		this.mid = mid;
		this.mpw = mpw;
		this.memail = memail;
		this.mphone = mphone;
		this.maddr = maddr;
		this.mjoindate = mjoindate;
		this.totalRental = totalRental;
		this.rental = rental;
	}

	public Member(String mid, String mpw, String memail, String mphone, String maddr) {
		super();
		this.mid = mid;
		this.mpw = mpw;
		this.memail = memail;
		this.mphone = mphone;
		this.maddr = maddr;
	}
	
	public Member(String memid, String mempw, String mememail, String memphone, String memaddr, String memjoindate) {
		super();
		this.mid = memid;
		this.mpw = mempw;
		this.memail = mememail;
		this.mphone = memphone;
		this.maddr = memaddr;
		this.mjoindate = memjoindate;
	}
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
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getMaddr() {
		return maddr;
	}
	public void setMaddr(String maddr) {
		this.maddr = maddr;
	}
	public String getMjoindate() {
		return mjoindate;
	}
	public void setMjoindate(String mjoindate) {
		this.mjoindate = mjoindate;
	}
	public int getTotalRental() {
		return totalRental;
	}
	public void setTotalRental(int totalRental) {
		this.totalRental = totalRental;
	}
	public int getRental() {
		return rental;
	}
	public void setRental(int rental) {
		this.rental = rental;
	}
	
}
