package memberOrder;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderService {
	Scanner scan;
	private ArrayList<Member> memberList; // 가입된 회원 목록
	private Member loginMember; // 로그인된 회원정보를 저장할 객체
	private ArrayList<Product> productList = new ArrayList<>(); // 상품 목록
	private ArrayList<Order> orderList = new ArrayList<>(); // 전체 주문 내역

	public OrderService() {
		scan = new Scanner(System.in);
		memberList = new ArrayList<>();
		memberList.add(new Member("test1", "1111", "테스트1"));
		memberList.add(new Member("test2", "2222", "테스트2"));
		loginMember = null;
		genProducts();
//		genOrders();
	}

	private void genProducts() {
		productList.add(new Product("마우스", 20000));
		productList.add(new Product("배터리", 25000));
		productList.add(new Product("모니터", 200000));
		productList.add(new Product("키보드", 100000));
		productList.add(new Product("충전기", 15000));
		System.out.println("상품 " + productList.size() + "개가 등록되었습니다.");
	}
	
//	private void genOrders() {
//		orderList.add(new Order(memberList.get(0), productList.get(0), 1));
//		orderList.add(new Order(memberList.get(0), productList.get(1), 2));
//		orderList.add(new Order(memberList.get(0), productList.get(2), 3));
//		orderList.add(new Order(memberList.get(1), productList.get(0), 1)); 
//		orderList.add(new Order(memberList.get(1), productList.get(1), 2));
//		orderList.add(new Order(memberList.get(1), productList.get(2), 3));
//	}

	public Member getLoginMember() {
		return loginMember;
	}

	public void setLoginMember(Member loginMember) {
		this.loginMember = loginMember;
	}

	// 회원가입 기능
	public void joinMember() {
		// 중복 되지 않은 아이디만 가입 가능

		boolean Register = true;
		Member member = null;

		System.out.print("가입할 아이디를 입력해주십시오 : ");
		String inputId = scan.next();
		System.out.print("가입할 비밀번호를 입력해주십시오 : ");
		String inputPw = scan.next();
		System.out.print("가입할 이름을 입력해주십시오 : ");
		String inputName = scan.next();
		for (int idx = 0; idx < memberList.size(); idx++) {
			Member checkRegister = memberList.get(idx);
			if (checkRegister.getmId().equals(inputId)) {
				System.out.println("입력하신 아이디는 사용이 불가능합니다. 다시 입력해주십시오. ");
				Register = false;
				continue;
			}
		}
		if (Register) {
			memberList.add(member);
			System.out.println("회원가입이 완료되었습니다.");
			return;
		}

	}

	// 로그인 기능
	public void loginMember() {

		System.out.print("아이디 입력 : ");
		String inputId = scan.next();
		System.out.print("비밀번호 입력 : ");
		String inputPw = scan.next();

		for (int idx = 0; idx < memberList.size(); idx++) {
			Member checkMember = memberList.get(idx);
			if (inputId.equals(checkMember.getmId()) && inputPw.equals(checkMember.getmPw())) {
				loginMember = checkMember;
				break;
			}
		}

		if (loginMember != null) {
			System.out.println("로그인 되었습니다.");
		} else {
			System.out.println("아이디 또는 비밀번호가 일치하지 않습니다.");
		}

	}

	public void orderProduct() { // 상품 주문
		if (loginMember == null) {
			System.out.println("로그인을 선행해주십시오.");
			return;
		}
//		1. 상품 목록(productList) 출력
		System.out.println("====[ 상 품 목 록 ]====");
		for (int idx = 0; idx < productList.size(); idx++) {
			Product mem = productList.get(idx);
			System.out.println("[" + idx + "]" + mem.getpName());
		}
		System.out.println("[-2] 주문종료");
		boolean run = true;
		while (run) {
			try {
//				2. 사용자가 주문할 상품 선택 ( productList에서 selectMenu인덱스의 product객체)
				System.out.print("주문하실 상품 번호를 입력해주십시오 : ");
				int selectPd = scan.nextInt(); // 문자를 입력했을 경우 > 예외발생
				if (selectPd == -2) {
					System.out.println("주문 종료");
					run = false;
				}
				Product selectProduct = productList.get(selectPd); // 없는 인덱스가 지정되는 경우 > 예외발생
//				3. 선택한 상품의 이름과 가격 출력
				System.out.println("선택한 상품은 " + selectProduct.getpName() + " " + selectProduct.getPrice() + "원 입니다.");
//				4. 사용자로부터 주문할 수량 입력 (count)
				System.out.print("주문하실 수량을 입력해주십시오 : ");
				int count = scan.nextInt();
//				5. 주문목록(orderList)에 주문내용(Order 객체) 추가
//				5-1. 주문(Order)객체 생성
				Order order = new Order(loginMember, selectProduct, count);
//				5-2. 주문(Order)객체 Order를 orderList에 추가
				orderList.add(order);
//				6. 주문 완료 메시지 출력
				System.out.println(selectProduct.getpName() + " " + count + "개 주문이 완료되었습니다.");

			} catch (InputMismatchException misMatch) {
				System.out.println("숫자만 입력 가능합니다. 다시 입력해주십시오.");
				scan.nextLine();
				continue;
			} catch (IndexOutOfBoundsException outOfBounds) {
				System.out.println("없는 상품입니다. 다시 입력해주십시오.");
			} catch (Exception e) {
				System.out.println("예상치 못한 예외 발생! 주문을 종료합니다");
				run = false;
			}

		}

	}

	public void loginMemberOrderList() {
		System.out.println(loginMember.getmName() + " 님의 주문내역");
		// 로그인 되어있는 회원의 주문내역을 출력
		// 총 주문 수량 및 총 주문금액 출력
		ArrayList<Order> orderIndex = new ArrayList<>();
		int out = 0, totalPrice = 0, totalCount = 0, totalOrder = 0; // 주문내역, 총 가격, 총 갯수 0으로 초기화
		for (int idx = 0; idx < orderList.size(); idx++) {
			Order checkOrder = orderList.get(idx);
			if (loginMember.equals(checkOrder.getMember())) {
				Product loginPd = checkOrder.getProduct();
				int loginCount = checkOrder.getCount();
				orderIndex.add(checkOrder);
				totalPrice += (loginPd.getPrice() * loginCount);
				totalCount += loginCount;
				totalOrder++;
				System.out.println("["+out+"]"+loginPd.getpName() + ", " + loginPd.getPrice() + "원, " + loginCount + "개");
				out++;
				
			}
		}
		if(totalOrder == 0) {
			System.out.println("주문 내역이 없습니다.");
			return;
		}
		System.out.println("총 주문 수 : " + totalOrder + " 총 주문 수량 : " + totalCount + " 총 주문금액 : " + totalPrice);

		// 주문내역에서 선택한 주문을 취소(삭제) 가능
		System.out.print("취소하실 주문을 선택해주십시오. [-2] 메인으로 : ");
		int removeSelect = scan.nextInt();
		if (removeSelect == -2) {
			return;
		}else if(removeSelect <= orderIndex.size() && removeSelect >= 0) {
			System.out.println("정말 취소하시겠습니까? [1]예 [2]돌아가기");
			int confirm = scan.nextInt();
			if(confirm == 1) {
				Order delIndex = orderIndex.get(removeSelect);
				orderList.remove(delIndex); // 인덱스 번호 OR 객체 자체를 지정해서 삭제 가능
				System.out.println("[" + removeSelect + "] 번째 주문이 취소되었습니다.");
			}else {
				return;
			}
		}
	}

	public void logoutMember() {
		loginMember = null;
		System.out.println("로그아웃 되었습니다.");
	}

}
