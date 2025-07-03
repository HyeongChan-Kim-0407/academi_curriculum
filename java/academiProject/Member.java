package academiProject;

public class Member {
	
	private String mid;
	private String mpw;
	private String mphone;
	private Grade mgrade;
	private String maddr;
	private String mname;
	private String mstate;
	private int mtotalprice;
	

	public Member() {
    }

	public Member(String mid, String mpw, String mphone, String maddr, Grade mgrade, String mname, String mstate, int mtotalprice) {
	    this.mid = mid;
	    this.mpw = mpw;
	    this.mphone = mphone;
	    this.maddr = maddr;
	    this.mgrade = mgrade;
	    this.mname = mname;
	    this.mstate = mstate;
	    this.mtotalprice = mtotalprice;
	}

	public Member(String mid, String mpw, Grade mgrade) {
		super();
		this.mid = mid;
		this.mpw = mpw;
		this.mgrade = mgrade;
	}
	public Member(String inputId, String inputPw, String inputHP, String inputAD, String inputName) {
		mid = inputId;
		mpw = inputPw;
		mphone = inputHP;
		maddr = inputAD;
		mname = inputName;
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
	public Grade getMgrade() {
		return mgrade;
	}
	public void setMgrade(Grade mgrade) {
		this.mgrade = mgrade;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMstate() {
		return mstate;
	}
	public void setMstate(String mstate) {
		this.mstate = mstate;
	}
	
	public int getMtotalprice() {
		return mtotalprice;
	}
	public void setMtotalprice(int mtotalprice) {
		this.mtotalprice = mtotalprice;
	}
	
}
