package academiProject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminService {
	private Scanner scan = new Scanner(System.in);
	private ProductDao pdao = new ProductDao();
	private MemberDao mdao = new MemberDao();
	private OrdersDao odao = new OrdersDao();
	private GradeDao gdao = new GradeDao();

	public void productInsert() {
		System.out.println("\n[상품등록]");
		System.out.print("상품명>> ");
		String pname = scan.next();

		System.out.print("종류(상의,하의,아우터)>> ");
		String ptype = scan.next();

		System.out.print("카테고리(반팔,긴바지,가디건)>> ");
		String pcategory = scan.next();

		int pprice = 0;
		int pstock = 0;

		try {
			System.out.print("가격>> ");
			pprice = scan.nextInt();

			System.out.print("재고>> ");
			pstock = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("숫자만 입력해 주세요. 상품 등록을 취소합니다.");
			scan.nextLine(); 
			return;
		}

		Product product = new Product(pname, ptype, pcategory, pprice, pstock);
		int registResult = pdao.adminInsertProduct(product);

		if (registResult > 0) {
			System.out.println("새 상품이 등록 되었습니다.");
		} else {
			System.out.println("상품 등록에 실패하였습니다.");
		}
	}


	public void infoManage() {
		// 전체 상품 목록 가져오기
		ArrayList<Product> productList = pdao.selectProduct();

		if (productList.isEmpty()) {
			System.out.println("등록된 상품이 없습니다.");
			return;
		}

		// 상품 목록 출력
		System.out.println("\n[상품 목록]");
		for (int i = 0; i < productList.size(); i++) {
			Product product = productList.get(i);
			System.out.printf("ID: %d | 이름: %s | 종류: %s | 카테고리: %s | 가격: %d | 재고: %d\n", i + 1, //product.getPcode(),
					product.getPname(), 
					product.getPtype(), product.getPcategory(), product.getPprice(),
					product.getPstock());
		}

		// 인덱스로 선택
		int index;
		try {
			System.out.print("수정할 상품 ID를 선택하세요>> ");
			 index = scan.nextInt();
			scan.nextLine(); // 버퍼 클리어
			
		} catch (InputMismatchException e) {
			System.out.println("숫자만 입력해 주세요.");
			scan.nextLine(); 
			return;
		}

		if (index < 1 || index > productList.size()) {
			System.out.println("잘못된 번호입니다.");
			return;
		}

		Product existingProduct = productList.get(index - 1);

		// 수정 메뉴 출력
		System.out.println("\n[정보수정]");
		System.out.println("[1]상품명 [2]상품종류 [3]카테고리 [4]가격 [5]재고 [6]삭제");
		String fixMenu = scan.next();

		scan.nextLine(); // 버퍼 클리어

		switch (fixMenu) {
		case "1":
			System.out.print("새 상품명 (변경하지 않으려면 엔터)>> ");
			String newName = scan.nextLine();
			if (!newName.isEmpty())
				existingProduct.setPname(newName);
			break;
		case "2":
			System.out.print("새 종류 (변경하지 않으려면 엔터)>> ");
			String newType = scan.nextLine();
			if (!newType.isEmpty())
				existingProduct.setPtype(newType);
			break;
		case "3":
			System.out.print("새 카테고리 (변경하지 않으려면 엔터)>> ");
			String newCategory = scan.nextLine();
			if (!newCategory.isEmpty())
				existingProduct.setPcategory(newCategory);
			break;
		case "4":
			System.out.println("[1]가격변경 [2]할인적용");
			fixMenu = scan.next();
			if (fixMenu.equals("1")) {
				System.out.print("새 가격 입력 >> ");
				try {
					int newPrice = scan.nextInt();
					existingProduct.setPprice(newPrice);
				} catch (InputMismatchException e) {
					System.out.println("숫자만 입력하세요.");
					scan.nextLine(); // 버퍼 클리어
					return;
				}
			} else if (fixMenu.equals("2")) {
				System.out.print("할인률을 입력하세요 (%) >> ");
				try {
					int discountRate = scan.nextInt(); // 예: 15 입력 시 15% 할인
					if (discountRate < 0 || discountRate > 100) {
						System.out.println("할인률은 0~100 사이의 숫자여야 합니다.");
						return;
					}

					int originalPrice = existingProduct.getPprice();
					double discounted = originalPrice * (1 - (discountRate / 100.0)); // 할인 계산
					int finalPrice = ((int) discounted / 10) * 10; // 일의 자리 0으로 만들기

					System.out.println("할인된 가격: " + finalPrice + "원 (기존가격: " + originalPrice + "원)");
					existingProduct.setPprice(finalPrice);

				} catch (InputMismatchException e) {
					System.out.println("숫자만 입력하세요.");
					scan.nextLine(); // 버퍼 정리
					return;
				}
			}
			break;
		case "5":
			System.out.print("새 재고 입력 >> ");
			try {
				int newStock = scan.nextInt();
				existingProduct.setPstock(newStock);
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요.");
				scan.nextLine(); // 버퍼 클리어
				return;
			}
			break;
		case "6":
			System.out.print("정말 삭제하시겠습니까? (Y/N) >> ");
			String confirm = scan.nextLine();
			if (confirm.equalsIgnoreCase("Y")) {
				int deleteResult = pdao.deleteProduct(existingProduct.getPcode());
				if (deleteResult > 0) {
					System.out.println("상품이 삭제되었습니다.");
				} else {
					System.out.println("상품 삭제에 실패하였습니다.");
				}
			} else {
				System.out.println("삭제가 취소되었습니다.");
			}
			return; // 수정 로직 종료
		default:
			System.out.println("잘못된 메뉴 선택입니다.");
			return;
		}

		// 삭제가 아닌 경우만 DB 수정 실행
		int updateResult = pdao.adminUpdateProduct(existingProduct);
		if (updateResult > 0) {
			System.out.println("상품 정보가 수정되었습니다.");
		} else {
			System.out.println("상품 정보 수정에 실패하였습니다.");
		}
	}

	public void memberList() {
		System.out.println("< 회원정보 >");
		System.out.println("[1]전체회원정보 [2]등급별회원 [3]회원정보검색 [4]등급별혜택수정");
		String selMenu = scan.next();

		if (selMenu.equals("1")) {
			ArrayList<Member> memberList = mdao.adminSelectMember();
			if (memberList.isEmpty()) {
				System.out.println("등록된 회원이 없습니다.");
			} else {
				for (Member member : memberList) {
					System.out.printf("아이디: %s | 비밀번호: %s | 이름: %s | 전화번호: %s | 주소: %s | 등급: %s | 탈퇴여부: %s\n",
							member.getMid(), member.getMpw(), member.getMname(), member.getMphone(), member.getMaddr(),
							member.getMgrade().getGrade(), member.getMstate());
				}
			}
		} else if (selMenu.equals("2")) {

			System.out.print("확인할 등급 입력 : ");

			String selGrade = scan.next();

			ArrayList<Member> memberList = mdao.adminSelectMemberByGrade(selGrade);

			if (memberList.isEmpty()) {
				System.out.println("등록된 회원이 없습니다.");
			}

			System.out.println(selGrade + "등급 회원 목록: ");
			for (Member member : memberList) {
				System.out.printf("아이디: %s | 비밀번호: %s | 이름: %s | 전화번호: %s | 주소: %s | 등급: %s | 탈퇴여부: %s\n",
						member.getMid(), member.getMpw(), member.getMname(), member.getMphone(), member.getMaddr(),
						member.getMgrade(), member.getMstate());
			}
		} else if (selMenu.equals("3")) {
			System.out.print("검색할 키워드를 입력하세요 (아이디 또는 이름)>> ");
			String keyword = scan.next();
			Member member = mdao.adminSelectMemberByNameOrMid(keyword);
			if (member == null) {
				System.out.println("검색 결과가 없습니다.");
			} else {

				System.out.printf("아이디: %s | 비밀번호: %s | 이름: %s | 전화번호: %s | 주소: %s | 등급: %s | 탈퇴여부: %s\n",
						member.getMid(), member.getMpw(), member.getMname(), member.getMphone(), member.getMaddr(),
						member.getMgrade(), member.getMstate());

			}
		} else if (selMenu.equals("4")) {
			// 등급 목록 출력
			ArrayList<Grade> gradeList = gdao.getAllGrades();
			if (gradeList.isEmpty()) {
				System.out.println("등급 정보가 존재하지 않습니다.");
				return;
			}

			System.out.println("[등급 목록]");
			for (int i = 0; i < gradeList.size(); i++) {
				Grade g = gradeList.get(i);
				System.out.printf("[%d] 등급: %s | 구매기준: %,d원 | 할인율: %d%%\n",
						i + 1, g.getGrade(), g.getPurprice(), g.getDiscount());
			}

			System.out.print("조정할 등급 번호 선택 >> ");
			int selIndex = scan.nextInt();
			scan.nextLine(); // 버퍼 정리

			if (selIndex <= 0 || selIndex > gradeList.size()) {
				System.out.println("잘못된 등급 번호입니다.");
				return;
			}

			Grade selectedGrade = gradeList.get(selIndex - 1);
			String selGrade = selectedGrade.getGrade();

			// 누적 구매 기준 입력
			System.out.print("새 누적 구매 기준 금액 입력 (변경하지 않으려면 엔터) >> ");
			String purPriceInput = scan.nextLine();
			int purPrice = selectedGrade.getPurprice(); // 기본값
			if (!purPriceInput.isEmpty()) {
				try {
					purPrice = Integer.parseInt(purPriceInput);
				} catch (NumberFormatException e) {
					System.out.println("숫자만 입력하거나 엔터를 입력해 주세요.");
					return;
				}
			}

			// 할인율 입력
			System.out.print("새 할인율 0~100(%) 입력 (변경하지 않으려면 엔터) >> ");
			String discountInput = scan.nextLine();
			int discount = 0; // 기본값
			if (!discountInput.isEmpty()) {
				try {
					discount = Integer.parseInt(discountInput);
					if (discount < 0 || discount > 100) {
						System.out.println("할인율은 0~100 사이의 값이어야 합니다.");
						return;
					}
				} catch (NumberFormatException e) {
					System.out.println("숫자만 입력하거나 엔터를 입력해 주세요.");
					return;
				}
			}else {
				System.out.println("등급별 혜택 수정을 종료합니다.");
				return;
			}
			int success = gdao.updateGradeBenefit(selGrade, purPrice, discount);
			if (success == 1) {
				System.out.println("등급 혜택이 성공적으로 수정되었습니다.");
			} else {
				System.out.println("등급 혜택 수정에 실패했습니다.");
			}
		}
	}

	public void manageOrders() {
		System.out.println("======[주문관리]=======");
		System.out.println("수정하실 내용을 선택해주십시오 [1]미확정주문관리 [2]확정주문취소");

		int selMenu;
		try {
			selMenu = scan.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("숫자만 입력해 주세요.");
			scan.nextLine(); // 버퍼 비우기
			return;
		}

		if (selMenu == 1) {
			System.out.println("미확정 주문목록을 출력합니다.");
			ArrayList<Orders> orderList = odao.SelectOrdersByNotFixed();

			if (orderList.isEmpty()) {
				System.out.println("미확정 주문이 없습니다.");
				return;
			}

			System.out.println("주문번호, 상품명, 주문자, 주문수량, 주문일, 주문방식, 주문상태");
			for (Orders od : orderList) {
				System.out.println(od.getOcode() + ", " + od.getProduct().getPname() + ", " + od.getMember().getMname()
						+ ", " + od.getOcount() + ", " + od.getOrderdate() + ", " + od.getPurway() + ", ["
						+ od.getOrderstate() + "]");
			}

			System.out.print("수정하실 주문의 주문번호를 입력해주십시오: ");
			int selOcode;

			try {
				selOcode = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해 주세요.");
				scan.nextLine(); // 버퍼 비우기
				return;
			}

			ArrayList<Orders> selList = odao.selectOrdersByOcode(selOcode);

			if (selList == null || selList.isEmpty()) {
				System.out.println("해당 주문번호에 대한 주문이 존재하지 않습니다.");
				return;
			}

			Orders selOd = selList.get(0); // 주문번호는 고유하다고 가정

			System.out.println("선택하신 내역의 주문번호는 " + selOd.getOcode() + "번 입니다.");
			System.out.println("수정하실 내용을 선택해주십시오. [1]결제완료 [2]주문취소");

			int inputNum;
			try {
				inputNum = scan.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력해 주세요.");
				scan.nextLine();
				return;
			}

			if (inputNum == 1) {
				int updateResult = odao.updateOrderState(selList);
				if (updateResult > 0) {
					System.out.println("결제완료 처리되었습니다.");
				} else {
					System.out.println("오류가 발생하였습니다. 다시 시도해주십시오.");
				}
			} else if (inputNum == 2) {
				int updateResult = odao.updateOrderStateAndUpdateProduct(selList);
				if (updateResult > 0) {
					System.out.println("주문이 취소되었습니다.");
				} else {
					System.out.println("오류가 발생하였습니다. 다시 시도해주십시오.");
				}
			} else {
				System.out.println("잘못된 선택입니다.");
			}
		} else if (selMenu == 2) {

			System.out.println("주문확정된 목록을 출력합니다.");

			ArrayList<Orders> orderList = odao.SelectOrdersByFixed();

			System.out.println("주문번호, 상품명, 주문자, 주문수량, 주문일, 주문방식");

			for (Orders od : orderList) {

				System.out.println(od.getOcode() + ", " + od.getProduct().getPname() + ", " + od.getMember().getMname()
						+ ", " + od.getOcount() + ", " + od.getOrderdate() + ", " + od.getPurway());

			}

			System.out.println("수정하실 주문의 주문번호를 선택해주십시오.");
			int selIdx = scan.nextInt();

			ArrayList<Orders> selList = odao.selectOrdersByOcode(selIdx);

			Orders selOd = selList.get(0);

			System.out.println("선택하신 내역의 주문번호는 " + selOd.getOcode() + "번 입니다.");

			System.out.println("취소하시겠습니까? [1]예 [2]아니오");
			int inputNum = scan.nextInt();

			if (inputNum == 1) {
				int updateResult = odao.updateOrderStateAndUpdateProduct(selList);

				if (updateResult > 0) {
					System.out.println("주문이 취소되었습니다.");
				} else {
					System.out.println("오류가 발생하였습니다 다시 시도해주십시오.");
				}

			} else if (inputNum == 2) {
				System.out.println("메인으로 돌아갑니다.");
				return;
			}

		}

	}

}
