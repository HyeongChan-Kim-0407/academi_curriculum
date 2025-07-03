package memberOrder;

import java.util.ArrayList;

public class OrderList {

	public static void main(String[] args) {

		ArrayList<Order> orderList = new ArrayList<>();

		Product pd1 = new Product("상품1", 1000);
		Member mem1 = new Member("Id01", "1111", "회원1");
		int count1 = 10;
		Order order1 = new Order(mem1, pd1, count1);
		orderList.add(order1);
		
		Product pd2 = new Product("상품2", 2000);
		Member mem2 = new Member("Id02", "2222", "회원2");
		int count2 = 20;
		Order order2 = new Order(mem2, pd2, count2);
		orderList.add(order2);
		
		for (int idx = 0; idx <orderList.size(); idx++) {
			
			Order od = orderList.get(idx);
			Member mem = od.getMember();
			Product pd = od.getProduct();
			if(mem1 == mem) {				
				System.out.println(mem.getmName() + " " + pd.getpName() + " " + od.getCount() + "개");
			}
			
		}
		System.out.println("[100]메인으로");
		System.out.print("취소할 주문 선택 : ");
		/*
		 * 스캔을 받고
		 * 해당 인덱스에 대한 정보 출력 << ** 중요) 그 정보가 orderList의 몇 번 인덱스에 있는지 확인 방법 필요 **
		 * 삭제 확인메시지 출력 ex) 1예 2돌아가기
		 * 1 입력시 해당 인덱스에 대한 정보 null
		 * 2 입력시 메인 or 주문내역 재출력
		 * 
		 * 
		 */
		
	}
	
	

}
