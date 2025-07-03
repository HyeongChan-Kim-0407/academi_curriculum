package academiTest;

public class SideDish extends Food {
	
	private String recipe;
	
	public SideDish(String foodName, int count, int foodPrice, String recipe) {
		super(foodName, count, foodPrice);
		this.recipe = recipe;
	}

	@Override
	public String foodInfo() {
		return "[메뉴명]" + getFoodName() + " [조리방법]" + recipe +" [가격]"+ getFoodPrice() + " [재고수량]" + getCount();
	}

	
	
	
	
}
