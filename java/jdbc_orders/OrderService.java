package jdbc_orders;


import java.util.ArrayList;
import java.util.Scanner;



public class OrderService {
	Scanner scan = new Scanner(System.in);
	private MemberDao mdao = new MemberDao();
	private OrderDao odao = new OrderDao();
	private Member loginMember;
	
	public Member getloginMember() {
		return loginMember;
	}
	
	/* 회원 가입 */
	public void joinMember() {
		if (loginMember != null) {
			System.out.println("이미 로그인중입니다. 로그아웃 후 시도해주십시오");
			return;
		}
		// 1. 가입자 정보 입력
		System.out.print("가입할 아이디 : ");
		String inputId = scan.next();
		System.out.print("가입할 비밀번호 : ");
		String inputPw = scan.next();
		System.out.print("가입할 이메일 : ");
		String inputEmail = scan.next();
		System.out.print("가입할 전화번호 : ");
		String inputPhone = scan.next();
		System.out.print("가입할 주소 : ");
		String inputAddr = scan.next();
				
		// 2. Member 객체 생성
		Member newMember = new Member(inputId, inputPw, inputEmail, inputPhone, inputAddr);
		
		// 3. 아이디 중복확인 (DB-MEMBER에서 확인 < SELECT)
		Member member = mdao.selectMemberById(newMember.getMid());
		if (member != null) {
			System.out.println("이미 사용중인 아이디입니다.\n다시 가입해주십시오.");
			return;
		}
		// 4. 회원가입 처리 (DB-MEMBER에 입력 < INSERT)
		int insertResult = mdao.insertMember(newMember);
		if (insertResult > 0) {
			System.out.println("회원가입 완료");			
		}else {
			System.out.println("회원가입 실패");
		}
				
	}
	
	public void loginMember() {
		if (loginMember != null) {
			System.out.println("이미 로그인중입니다. 로그아웃 후 시도해주십시오");
			return;
		}
		// 1. 로그인 할 아이디, 비밀번호 입력
		System.out.print("로그인 할 아이디 : ");
		String inputId = scan.next();
		System.out.print("로그인 할 비밀번호 : ");
		String inputPw = scan.next();
		
		// 2. 아이디, 비밀번호로 회원정보 조회
		Member selectResult = mdao.selectMemberByIdAndPw(inputId, inputPw);
		
		// 3. 조회 결과에 따라서 로그인 처리
		if(selectResult == null) {
			System.out.println("로그인 실패");
			return;
		}else {
			System.out.println("로그인 성공");
			loginMember = selectResult;
		}
		
	}
	
	public void logoutMember() {
		if(loginMember == null) {
			System.out.println("잘못된 접근입니다.");
			return;
		}
		System.out.println("로그아웃");
		loginMember = null;
	}
	
	public void memberList() {
		System.out.println("\n[회원목록]");
		//1. 가입된 회원 정보 목록 조회 (DB - MEMBER : SELECT)
		ArrayList<Member> memberList = mdao.selectMemberList();
		//2. 조회된 회원 목록 출력
		for(Member member : memberList) {
			System.out.println("[ID]" + member.getMid() + ", [PW]" + member.getMpw());
		}
		
	}
	
	
	
	public void orderProduct() {
		
		if(loginMember == null) {
			System.out.println("잘못된 접근입니다.");
			return;
		}
		System.out.println("상품 목록");
		/* 상품 목록 출력 */
		// [1] 전체 상품 [2] 종류별 상품 [3] 인기상품 
		System.out.println("[1] 전체 상품 [2] 종류별 상품 [3] 인기 상품");
		System.out.print("선택 : ");
		int selectMenu = scan.nextInt();
		ArrayList<Product> productList = new ArrayList<>();
		if(selectMenu == 1) {
			productList = odao.selectProduct();
		}else if(selectMenu == 2) {
			System.out.println("조회하실 상품 종류를 입력해주십시오");
			String selType = scan.next();
			productList = odao.selectProductByType(selType);
		}else if(selectMenu == 3) {
			System.out.println("주문많은 순으로 출력합니다.");
			productList = odao.selectProductByOcount();
		}else {
			System.out.println("전체 상품을 출력합니다");
			productList = odao.selectProduct();
		}
		
		System.out.println("[상품번호], [상품이름], [가격], [제조사], [상품종류], [재고] \n");
		
		int index = 1;
		for(Product product : productList) {
			System.out.println( "[" + index++ + "] " + product.getPname() + ", " + product.getPprice() + ", "
					+ product.getPcompany() + ", " + product.getPtype() + ", " + product.getPstock());
		}
		/* 상품 주문 처리 */
		
		System.out.print("주문하실 상품의 상품번호를 입력해주십시오.");
		int selectCode = scan.nextInt();
		if(selectCode > productList.size()) {
			System.out.println("선택하신 상품은 없는 상품입니다");
			return;
		}
		Product selectPd = productList.get(selectCode - 1);
		System.out.println("선택하신 상품은 " + selectPd.getPname() + "입니다.");
		System.out.print("주문 수량을 입력해주십시오 [재고] : " + selectPd.getPstock() + " ");		
		int inputCount = scan.nextInt();
		if(inputCount > selectPd.getPstock()) {
			System.out.println("죄송합니다 현재 재고 이상으론 주문하실 수 없습니다");
			return;
		}
		
		Orders order = new Orders();
		order.setProduct(selectPd);
		order.setMember(loginMember);
		order.setOcount(inputCount);
		
		int orderResult = odao.insertOrdersByOrder(order);
		if(orderResult > 0) {
				System.out.println("주문 완료");
				return;
		}else  {
			System.out.println("주문 실패");
			return;
		}
		
		
		
	}

	public void orderList() {
		
		if(loginMember == null) {
			System.out.println("잘못된 접근입니다.");
			return;
		}
		
		System.out.println("=====[주문내역]=====");
		System.out.println("[1]전체목록 [2]월별목록");
		int selMenu = scan.nextInt();
		
		ArrayList<Orders> orderList = new ArrayList<>();
		
		if(selMenu == 1) {
			orderList = odao.selectOrdersByMid(loginMember);
		}else if(selMenu == 2) {
			System.out.println("조회하실 주문월을 입력해주십시오.");
			int selMonth = scan.nextInt();
			if(selMonth > 12 || selMonth < 1) {
				System.out.println("잘못된 입력입니다 1~12 사이의 값을 입력해주십시오.");
				return;
			}
			String month = String.valueOf(selMonth);
			if(month.length() == 1) {
				month = '0' + month;
			}
			orderList = odao.selectOrdersByMidAndMonth(loginMember, month);
		}else {
			System.out.println("잘못된 입력입니다.");
			return;
		}
		
		if(orderList.isEmpty()) {
			System.out.println("주문하신 내역이 없습니다");
			return;
		}
		
		System.out.println("[주문번호], [상품이름], [상품가격], [주문수량], [주문금액], [주문일] \n");
		
		int index = 1;
		for(Orders orders : orderList) {
			System.out.println( "[" + index++ + "]" + ", " + orders.getProduct().getPname() + ", " + orders.getProduct().getPprice() +
					", " + orders.getOcount() + ", " + ( orders.getProduct().getPprice() * orders.getOcount() ) + ", " + orders.getOdate());
		}
		
		
		
		/* 취소할 주문 선택 */
		
		System.out.println("\n취소할 주문의 주문번호를 입력해주십시오");
		
		int cancelCode = scan.nextInt();
		
		Orders cancelOrder = orderList.get(cancelCode - 1); 
		
		Product selectPd = cancelOrder.getProduct();
		System.out.println("선택하신 주문내용은 " + selectPd.getPname() + "입니다.");
		
		System.out.print("취소하시겠습니까? [1]예 [2]아니오 ");
		int cancelconfirm = scan.nextInt();
		
		if(cancelconfirm == 1) {
			int cancelResult = odao.updateProductByOCodeAndDelete(cancelOrder);
			if(cancelResult > 0) {
				System.out.println("주문이 취소되었습니다");
				return;
			}else {
				System.out.println("오류가 발생하였습니다 다시 시도해주십시오.");
				return;
			}
		}else {
			System.out.println("메인메뉴로 돌아갑니다.");
			return;
		}
		
		
		
		
	}
	
}
