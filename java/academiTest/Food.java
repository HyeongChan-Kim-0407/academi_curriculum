package academiTest;

public class Food {
	private String foodName;
	private int foodPrice;
	private int count;
	
	public Food(String foodName, int count, int foodPrice) {
		this.foodName = foodName;
		this.count = count;
		this.foodPrice = foodPrice;
	}
	public String getFoodName() {
		return foodName;
	}
	public int getFoodPrice() {
		return foodPrice;
	}
	public int getCount() {
		return count;
	}
	public String foodInfo() {
		return "[메뉴명]" + foodName + " [가격]"+ foodPrice + " [재고수량]" + count;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public void setFoodPrice(int foodPrice) {
		this.foodPrice = foodPrice;
	}
	public void setCount(int count) {
		this.count = count;
	}
}