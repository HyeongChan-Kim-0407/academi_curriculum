package academiTest;

public class MainDish extends Food {

	private String ingredient;

	public MainDish(String foodName, int count, int foodPrice, String ingredient) {
		super(foodName, count, foodPrice);
		this.ingredient = ingredient;
	}
	
	@Override
	public String foodInfo() {
		return " [메뉴명]" + getFoodName() + " [종류]" + ingredient +" [가격]"+ getFoodPrice() + " [재고수량]" + getCount();
	}
	
}
