package academiTest;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Service {

	static Scanner scan = new Scanner(System.in);
	private ArrayList<Food> foodList;
	private ArrayList<Order> orderList;

	public Service() {
		this.foodList = new ArrayList<>();
		this.orderList = new ArrayList<>();
	}

	public void register() {
		boolean register = true;
		while (register) {
			try {
				System.out.println("[메뉴 등록]");
				System.out.println("[1]메인메뉴 [2]사이드메뉴 [3]음료 [4]종료");
				int inputMenu = scan.nextInt();

				switch (inputMenu) {
				case 1:
					System.out.println("등록하실 메뉴의 이름, 종류, 수량과 가격을 입력해주십시오.");
					System.out.print("메뉴 이름 : ");
					String mainName = scan.next();
					System.out.print("종류 : ");
					String ingredient = scan.next();
					System.out.print("수량 : ");
					int mainCount = scan.nextInt();
					System.out.print("가격 : ");
					int mainPrice = scan.nextInt();

					foodList.add(new MainDish(mainName, mainCount, mainPrice, ingredient));

					System.out.println(mainName + " 메뉴가 등록되었습니다.");

					break;
				case 2:
					System.out.println("등록하실 메뉴의 이름, 조리방법, 수량과 가격을 입력해주십시오.");
					System.out.print("메뉴 이름 : ");
					String sideName = scan.next();
					System.out.print("조리방법 : ");
					String recipe = scan.next();
					System.out.print("수량 : ");
					int sideCount = scan.nextInt();
					System.out.print("가격 : ");
					int sidePrice = scan.nextInt();

					foodList.add(new SideDish(sideName, sideCount, sidePrice, recipe));

					System.out.println(sideName + " 메뉴가 등록되었습니다.");

					break;
				case 3:

					System.out.println("등록하실 메뉴의 이름, 분류, 수량과 가격을 입력해주십시오.");
					System.out.print("메뉴 이름 : ");
					String drinkName = scan.next();
					System.out.print("분류 : ");
					String drinkType = scan.next();
					System.out.print("수량 : ");
					int drinkCount = scan.nextInt();
					System.out.print("가격 : ");
					int drinkPrice = scan.nextInt();

					foodList.add(new Drinks(drinkName, drinkCount, drinkPrice, drinkType));

					System.out.println(drinkName + " 메뉴가 등록되었습니다.");

					break;
				case 4:
					System.out.println("메뉴 등록을 종료합니다.");
					return;
				default:
					System.out.println("잘못된 입력입니다.");
				}

			} catch (InputMismatchException m) {
				System.out.println("숫자만 입력해주시기 바랍니다.");
				scan.nextLine();
			}
		}
	}

	// 메뉴 목록 출력 및 주문

	public void menuList() {
		try {
			if (foodList.size() <= 0) {
				System.out.println("메뉴가 등록되어있지 않습니다 메뉴 등록을 선행해주십시오.");
				return;
			}
			System.out.println("=====[메뉴 목록]=====");
			System.out.println("찾으시려는 종류를 입력해주십시오");
			System.out.println("[1]메인메뉴 [2]사이드메뉴 [3]음료 [4]종료");
			int inputNum = scan.nextInt();
			ArrayList<Food> filterList = new ArrayList<>();

			if (inputNum == 1) {
				for (int main = 0; main < foodList.size(); main++) {
					Food mainMenu = foodList.get(main);
					if (mainMenu instanceof MainDish) {
						filterList.add(mainMenu);
					}
				}
				if (filterList.size() <= 0) {
					System.out.println("죄송합니다 메인 메뉴가 준비 중입니다.");
					return;
				}
				System.out.println("=====[메인 메뉴]=====");
			} else if (inputNum == 2) {
				for (int side = 0; side < foodList.size(); side++) {
					Food sideMenu = foodList.get(side);
					if (sideMenu instanceof SideDish) {
						filterList.add(sideMenu);
					}
				}
				if (filterList.size() <= 0) {
					System.out.println("죄송합니다 사이드 메뉴가 준비 중입니다.");
					return;
				}
				System.out.println("=====[사이드 메뉴]=====");
			} else if (inputNum == 3) {
				for (int drink = 0; drink < foodList.size(); drink++) {
					Food drinkMenu = foodList.get(drink);
					if (drinkMenu instanceof Drinks) {
						filterList.add(drinkMenu);
					}
				}
				if (filterList.size() <= 0) {
					System.out.println("죄송합니다 음료가 준비 중입니다.");
					return;
				}
				System.out.println("=====[음료]=====");
			} else if (inputNum == 4) {
				System.out.println("주문 종료");
				return;
			} else {
				System.out.println("잘못된 입력입니다.");
				return;
			}

			for (int idx = 0; idx < filterList.size(); idx++) {
				Food filter = filterList.get(idx);
				if (filter.getCount() <= 0) {
					System.out.println("재고 소진으로 인한 주문 불가");
					continue;
				}
				System.out.println("[" + (idx + 1) + "]" + filter.foodInfo());
			}

			System.out.print("주문하실 메뉴를 선택해주십시오 메인으로 돌아가기[0] : ");
			int select = scan.nextInt();
			if (select == 0) {
				return;
			}

			Food selectMenu = filterList.get((select - 1));
			if (selectMenu.getCount() <= 0) {
				System.out.println("죄송합니다 선택하신 메뉴는 재고 소진으로 인해 주문이 불가합니다.");
				menuList();
			} else {
				System.out.println("선택하신 메뉴는 " + selectMenu.getFoodName() + "입니다.");
				System.out.print("주문하실 수량을 입력해주십시오 : ");
			}
			int inputCount = scan.nextInt();
			if (inputCount > selectMenu.getCount()) {
				System.out.println("죄송합니다 재고수량이 부족하여 주문이 불가합니다.");
				menuList();
			} else if (inputCount <= 0) {
				System.out.println("주문수량을 확인해주십시오.");
				menuList();
			} else {
				System.out.println(selectMenu.getFoodName() + " " + inputCount + "개가 주문 완료되었습니다.");
				orderList.add(new Order(selectMenu, inputCount));
				selectMenu.setCount(selectMenu.getCount() - inputCount);
			}

		} catch (InputMismatchException m) {
			System.out.println("숫자만 입력 가능합니다.");
			scan.nextLine();
		} catch (IndexOutOfBoundsException b) {
			System.out.println("죄송합니다 선택하신 메뉴는 없는 메뉴입니다.");
			menuList();
		}
	}

	// 주문 내역

	public void orderList() {
		try {
			if (orderList.size() <= 0) {
				System.out.println("주문하신 내역이 없습니다");
				return;
			}
			int orderMain = 0;
			int orderSide = 0;
			int orderDrink = 0;
			int totalOrder = 0;
			int totalPrice = 0;
			System.out.println("=====[주문 내역]=====");
			for (int idx = 0; idx < orderList.size(); idx++) {
				Order order = orderList.get(idx);
				Food orderFood = order.getOrderFood();
				if (orderFood instanceof MainDish) {
					orderMain += order.getOrderCount();
				} else if (orderFood instanceof SideDish) {
					orderSide += order.getOrderCount();
				} else {
					orderDrink += order.getOrderCount();
				}
				System.out.println("[" + (idx + 1) + "]" + orderFood.getFoodName() + " " + orderFood.getFoodPrice()
						+ "원 " + order.getOrderCount() + "개");
				totalOrder += order.getOrderCount();
				totalPrice += (order.getOrderCount() * orderFood.getFoodPrice());
			}
			System.out.println("메인 메뉴 " + orderMain + "개, 사이드 메뉴 " + orderSide + "개, 음료 " + orderDrink + "개");
			System.out.println("총 " + totalOrder + "개 주문하셨습니다.");
			System.out.println("총 주문 금액은 " + totalPrice + "원 입니다.");

			System.out.print("취소하고 싶은 주문이 있습니까? [1]예 [2]아니오 ");
			int confirm = scan.nextInt();
			if (confirm == 2) {
				System.out.println("주문이 확정되었습니다. 감사합니다");
				return;
			}

			System.out.print("취소하고 싶은 주문을 선택해주십시오 : ");
			int cancelindex = scan.nextInt();
			Order cancelOrder = orderList.get(cancelindex);
			Food cancelFood = cancelOrder.getOrderFood();
			System.out.print("선택하신 주문은 " + cancelFood.getFoodName() + "입니다. 취소하시겠습니까? [1]예 [2]아니오");
			int cancel = scan.nextInt();
			if (cancel == 1) {
				orderList.remove(cancelOrder);
			} else {
				System.out.println("메인으로 돌아갑니다");
				return;
			}

		} catch (InputMismatchException m) {
			System.out.println("죄송합니다 숫자만 입력 가능합니다.");
			scan.nextLine();
		}

	}

}
