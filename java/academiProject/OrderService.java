package academiProject;

import java.util.ArrayList;

public class OrderService {
	
	ProductDao pdao = new ProductDao();
	MemberDao mdao = new MemberDao();
	OrdersDao odao = new OrdersDao();
	
	public void orderProduct() {
		
		System.out.println("[상품출력]");
		
		ArrayList<Product> productList = pdao.selectProduct();
		
	}
	
}
