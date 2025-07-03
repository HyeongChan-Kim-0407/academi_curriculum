package day08;

public class MenuInfo {
	
	// 메뉴의 이름
	private String menuName;
	// 메뉴의 가격
	private int menuPrice;
	// 메뉴의 재고
	private int menuLeft;
	
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public void setMenuPrice(int menuPrice) {
		if(menuPrice < 1500) {
			menuPrice = 1500;
		}else if(menuPrice > 5000) {
			menuPrice = 5000;
		} // menuPrice 변수의 조정
		this.menuPrice = menuPrice;
		
	}
	public void setMenuLeft(int menuLeft) {
		this.menuLeft = menuLeft;
	}
	
	public String getMenuName() {
		return menuName;
	}
	
	public int getMenuPrice() {
		return menuPrice;
	}
	
	public int getMenuLeft() {
		return menuLeft;
	}
	
}
