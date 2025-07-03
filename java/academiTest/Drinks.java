package academiTest;

public class Drinks extends Food {
	
	private String drinkType;
	
	public Drinks(String foodName, int count, int foodPrice, String drinkType) {
		super(foodName, count, foodPrice);
		this.drinkType = drinkType;
	}
	
	@Override
	public String foodInfo() {
		return "[메뉴명]" + getFoodName() + " [분류]" + drinkType +" [가격]"+ getFoodPrice() + " [재고수량]" + getCount();
	}
	
}
