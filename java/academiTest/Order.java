package academiTest;

public class Order {
	
	private Food orderFood;
	private int orderCount;
	
	public Order(Food orderFood, int orderCount) {
		this.orderFood = orderFood;
		this.orderCount = orderCount;
	}

	public void setOrderFood(Food orderFood) {
		this.orderFood = orderFood;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public Food getOrderFood() {
		return orderFood;
	}

	public int getOrderCount() {
		return orderCount;
	}
	
	
	
}
