package academiProject;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserOrderService {

	private Scanner scan = new Scanner(System.in);
	private ProductDao pdao = new ProductDao();
	private MemberDao mdao = new MemberDao();
	private OrdersDao odao = new OrdersDao();
	private GradeDao gdao = new GradeDao();

	private Member loginMember;
	private ArrayList<Orders> cart = new ArrayList<>();
	
	public Member getloginMember() {
		return loginMember;
	}
	
	public void logoutMember() {
		loginMember = null;
	}

	// [ 상품 검색 및 장바구니 담기 ]
	public void searchProduct() { // 06/13 1~3사이의 숫자만 입력받을 수 있도록 수정, 양수만 입력받을 수 있게 수정
	    try {
	        System.out.println("======[상품 검색]======");
	        System.out.println("[1]전체 목록 [2]품목별 [3]이름검색");

	        int menu = scan.nextInt();
	        scan.nextLine(); // 숫자 입력 후 줄바꿈 제거

	        ArrayList<Product> list;

	        if (menu == 1) {
	            list = pdao.selectProduct();
	        } else if (menu == 2) {
	            System.out.print("검색할 품목 입력: ");
	            String type = scan.nextLine();
	            list = pdao.userSearchProductByType(type);
	        } else if (menu == 3) {
	            System.out.print("검색할 이름 키워드 입력: ");
	            String keyword = scan.nextLine();
	            list = pdao.searchByName(keyword);
	        } else {
	            System.out.println("메뉴 번호는 1~3 사이의 숫자를 입력해야 합니다.");
	            return;
	        }

	        if (list == null || list.isEmpty()) {
	            System.out.println("검색 결과가 없습니다.");
	            return;
	        }

	        System.out.println(" 상품번호, 상품명, 대분류, 소분류, 가격, 재고 ");
	        int index = 1;
	        for (Product pd : list) {
	            System.out.println("[" + index++ + "] " + pd.getPname() + ", " + pd.getPtype() + ", " +
	                    pd.getPcategory() + ", " + pd.getPprice() + ", " + pd.getPstock());
	        }

	        System.out.print("구매하실 상품을 선택해주십시오 : ");
	        int select = scan.nextInt();

	        if (select < 1 || select > list.size()) {
	            System.out.println("잘못된 상품 번호입니다.");
	            return;
	        }

	        Product selPd = list.get(select - 1);

	        if (selPd.getPstock() <= 0) {
	            System.out.println("선택하신 " + selPd.getPname() + " 상품은 재입고 준비 중입니다.");
	            return;
	        }

	        System.out.println("선택하신 상품은 " + selPd.getPname() + " 입니다.");
	        System.out.println("구매할 수량을 입력해주십시오 : 현재 재고 [" + selPd.getPstock() + "]");
	        int inputCount = scan.nextInt();

	        if (inputCount <= 0) {
	            System.out.println("구매 수량은 1개 이상이어야 합니다.");
	            return;
	        }

	        if (inputCount > selPd.getPstock()) {
	            System.out.println("현재 재고 이상으로는 주문하실 수 없습니다.");
	            return;
	        }

	        Orders od = new Orders(selPd, loginMember, inputCount);
	        int updateResult = pdao.orderProduct(od);

	        if (updateResult == 0) {
	            System.out.println("주문 과정에서 문제가 발생하였습니다. 다시 시도해주십시오.");
	            return;
	        }

	        cart.add(od);
	        System.out.println("선택하신 상품이 장바구니에 담겼습니다");
	        System.out.println("[1] 계속 쇼핑하기 [2] 장바구니로");

	        int selMenu = scan.nextInt();
	        if (selMenu == 1) {
	            searchProduct();
	        } else if (selMenu == 2) {
	            cart();
	        } else {
	            System.out.println("잘못된 입력입니다. 메인으로 돌아갑니다.");
	        }

	    } catch (InputMismatchException e) {
	        System.out.println("숫자만 입력해주십시오.");
	        scan.nextLine(); // 버퍼 클리어
	    } catch (IndexOutOfBoundsException e) {
	        System.out.println("선택하신 상품은 없는 상품입니다. 다시 주문해주십시오.");
	    }
	}


	public void cart() {
		
		try {
		
		System.out.println("======[장바구니]======");
		
		if(cart.isEmpty()) {
			System.out.println("장바구니에 내역이 없습니다.");
			return;
		}

		int index = 1;

		System.out.println(" 상품명, 대분류, 소분류, 가격, 주문수량, 주문금액");

		int totalPrice = 0;
		int gradeDiscount = loginMember.getMgrade().getDiscount();
		double discount = ((double)gradeDiscount / 100);
		
		for (Orders od : cart) {
			Product pd = od.getProduct();
			totalPrice = totalPrice + (pd.getPprice() * od.getOcount());
			System.out.println("[" + index++ + "]" + pd.getPname() + ", " + pd.getPtype() + ", "
					+ pd.getPcategory() + ", " + pd.getPprice() + ", " + od.getOcount() + ", "
					+ pd.getPprice() * od.getOcount()); //06/13 주문금액이 출력되지 않는 오류 수정
		}
		System.out.println("주문금액 : " + totalPrice + " 할인율 : " + loginMember.getMgrade().getDiscount() + "%");
		int purPrice = (int) (totalPrice - (totalPrice * discount) );
		System.out.println("결제금액 : " + purPrice + "원");

		System.out.println("주문을 확정하시겠습니까? [1]확정 [2]취소 [0]메인");
		int selMenu = scan.nextInt();
		int orderResult = 0;
		int ocode = odao.selectOcode();
		if (selMenu == 1) {

			System.out.println("주문방식을 선택해주십시오 [1]카드결제 [2]계좌이체 [3]무통장입금");
			int selPurWay = scan.nextInt();

			if (selPurWay == 1) {
				String PurWay = "실시간";

				for (Orders od : cart) {

					orderResult = odao.insertOrderByRealTime(ocode, od, loginMember, PurWay);

				}
				int priceResult = mdao.updateMtotalPrice(purPrice, loginMember);

				if (priceResult == 0) {
					System.out.println("결제 과정에서 문제가 발생했습니다 다시 시도해주십시오");
					return;
				}
			} else if (selPurWay == 2 || selPurWay == 3) {
				String PurWay = "비실시간";

				for (Orders od : cart) {

					orderResult = odao.insertOrderByNonRT(ocode, od, loginMember, PurWay);

				}

			}

		} else if (selMenu == 2) {

			boolean remove = true;
			while (remove) {
				
				if(cart.isEmpty()) {
					System.out.println("장바구니에 내역이 없습니다.");
					return;
				}
				
				System.out.println("취소할 주문을 선택해주십시오");

				int selDel = scan.nextInt();

				Orders delOd = cart.get(selDel - 1);
				
				int cancelResult = pdao.cancelOrder(delOd);
				
				if(cancelResult == 0) {
					System.out.println("주문 취소 과정에서 문제가 발생하였습니다 다시 시도해주십시오.");
					return;
				}
				
				cart.remove(delOd);
				
				System.out.print("더 취소할 주문이 있으십니까? [1]예 [2]아니오");

				int moreDel = scan.nextInt();

				if (moreDel == 1) {
					continue;
				} else if (moreDel == 2) {
					remove = false;
					System.out.println("장바구니를 재출력합니다.");
					cart();
					return;
				} else {
					System.out.println("잘못된 입력입니다. 메인으로 돌아갑니다.");
					return;
				}

			}

		} else {

			System.out.println("메인으로 돌아갑니다.");
			
			for (Orders od : cart) {
				pdao.cancelOrder(od);
			}
			
			return;
		}

		if (orderResult == 0) {
			System.out.println("주문과정에서 문제가 생겼습니다 다시 시도해주십시오.");
			for (Orders od : cart) {
				pdao.cancelOrder(od);
			}
			return;
		}

		System.out.println("주문이 완료되었습니다. 주문번호는 " + ocode + "번 입니다.");
		updateGrade();
		cart.clear();
		
		} catch (Exception e){
			
			for (Orders od : cart) {
				pdao.cancelOrder(od);
			}
			
		}
		
	}

	public Member loginGuest() {

		loginMember = mdao.loginGuest();

		return loginMember;
	}

	public void orderList() {

		System.out.println("======[주문내역]======");

		String grade = null;
		ArrayList<Orders> orderList = new ArrayList<>();

		if (loginMember.getMgrade().getGrade().equals("GUEST")) {
			grade = "GUEST";
			System.out.println("[1]주문번호 검색");
		} else {
			grade = "일반회원";
			System.out.print("[1]주문번호 검색 [2]전체 주문내역 출력 : ");
		}

		int selMenu = scan.nextInt();

		if (selMenu == 1) {
			System.out.println("주문번호를 입력해주십시오.");
			int inputOcode = scan.nextInt();
			orderList = odao.selectOrdersByOcode(inputOcode);
		} else {
			if (grade.equals("GUEST")) {
				System.out.println("잘못된 접근입니다 다시 시도해주십시오");
				return;
			}
			orderList = odao.selectOrderByOMid(loginMember);
		}
		
		if(orderList.isEmpty()) {
			System.out.println("주문내역이 존재하지 않습니다.");
			return;
		}
		
		System.out.println("주문내역을 출력합니다.");

		System.out.println("주문번호, 상품이름, 주문수량, 주문일, 결제일, 주문상태");
		for (Orders od : orderList) {

			System.out.println( od.getOcode() + ", " + od.getProduct().getPname() + ", "
					+ od.getOcount() + ", " + od.getOrderdate() + ", " + od.getPurdate() +  ", ["
					+ od.getOrderstate() + "]");
		}
		
		System.out.println("취소할 주문이 있으십니까? [1]예 [2]아니오");
		int selectMenu = scan.nextInt();
		
		if(selectMenu == 2) {
			System.out.println("메인으로 돌아갑니다.");
			return;
		}
		
		System.out.println("취소하실 주문번호를 입력해주십시오.");
		int selCancel = scan.nextInt();
		
		ArrayList<Orders> canOrderList = new ArrayList<>();
		
		for(Orders od : orderList) {
			
			if(od.getOcode() == selCancel) {
				
				if(od.getOrderstate().equals("주문확정")) {
					System.out.println("주문확정 처리된 주문의 취소는 판매자에게 문의하여주시기 바랍니다.");
					return;
				}else if(od.getOrderstate().equals("주문취소")) {
					System.out.println("이미 취소처리된 주문입니다.");
					return;
				}
				
				canOrderList.add(od);
			}
			
		}
		
		System.out.println("선택한 주문번호의 주문내역을 출력합니다");
		
		for(Orders od : canOrderList) {
			
			System.out.println( od.getOcode() + ", " + od.getProduct().getPname() + ", "
					+ od.getOcount() + ", " + od.getOrderdate() + ", " + od.getPurdate() + ", " + od.getPurway() + ", ["
					+ od.getOrderstate() + "]");
			
		}
		
		System.out.println("취소하시겠습니까? [1]예 [2]아니오");
		int canConfirm = scan.nextInt();

		if (canConfirm == 1) {

			int updateResult = odao.updateOrderStateAndUpdateProduct(canOrderList);

			if (updateResult > 0) {
				System.out.println("주문이 취소되었습니다.");
			} else {
				System.out.println("오류가 발생하였습니다 다시 시도해주십시오.");
			}

		} else if (selCancel == 2) {
			System.out.println("메인으로 돌아갑니다.");
			return;
		}
	
		
		
	}

	public void joinMember() {

		System.out.println("======[회원가입]======");

		System.out.print("아이디를 입력해주십시오 : ");
		String inputId = scan.next();
		 if (mdao.checkDuplicateId(inputId)) { // 중복확인
		        System.out.println("이미 존재하는 아이디입니다. 다른 아이디를 입력해주세요.");
		        return;
		    }
		System.out.print("비밀번호를 입력해주십시오 : ");
		String inputPw = scan.next();
		System.out.print("전화번호를 입력해주십시오 : ");
		String inputHP = scan.next();
		System.out.print("주소를 입력해주십시오 : ");
		String inputAD = scan.next();
		System.out.print("이름을 입력해주십시오 : ");
		String inputName = scan.next();

		Member joinMember = new Member(inputId, inputPw, inputHP, inputAD, inputName);

		int insertResult = mdao.insertMember(joinMember);

		if (insertResult == 0) {
			System.out.println("회원가입 과정에서 문제가 발생하였습니다 다시 시도해주십시오.");
			return;
		}

		System.out.println("회원가입이 완료되었습니다. 로그인해주십시오.");

	}

	public void loginMember() {

		System.out.println("=======[로그인]=======");

		System.out.print("아이디를 입력해주십시오 : ");
		String inputId = scan.next();
		System.out.print("비밀번호를 입력해주십시오 : ");
		String inputPw = scan.next();

		Member selectMember = mdao.selectMemberByMidAndMpw(inputId, inputPw);

		if (selectMember == null) {
			System.out.println("아이디 혹은 비밀번호를 확인해주십시오.");
			return;
		} else {
			loginMember = selectMember;
			System.out.println("로그인 성공 환영합니다 " + loginMember.getMname() + "님");
			return;
		}

	}

	public void memberInfo() {

		System.out.println("======[회원정보]======");

		System.out.println("비밀번호를 다시 한 번 입력해주십시오.");
		String checkPw = scan.next();

		if (!loginMember.getMpw().equals(checkPw)) {
			System.out.println("비밀번호를 확인해주십시오.");
			return;
		}

		System.out.println(loginMember.getMname() + "님의 회원정보");
		System.out.println("아이디 : " + loginMember.getMid() + "\n 연락처 : " + loginMember.getMphone() + "\n 회원등급 : "
				+ loginMember.getMgrade().getGrade() + "\n 누적 결제금액 : " + loginMember.getMtotalprice());

		System.out.println("[1]정보수정 [2]회원탈퇴 [0]메인으로");
		int inputNum = scan.nextInt();

		if (inputNum == 1) {

			System.out.println("수정하실 정보를 선택해주십시오. [1]연락처 [2]주소 [3]이름");
			int selMod = scan.nextInt();
			
			if(selMod == 1) {
				System.out.println("수정하실 연락처를 입력해주십시오. ex) 010-1111-1111");
				String changeHP = scan.next();
				loginMember.setMphone(changeHP);
			}else if(selMod == 2) {
				System.out.println("수정하실 주소를 입력하여주십시오. ex) 인천광역시 미추홀구 ~");
				String changeAD = scan.nextLine();
				loginMember.setMaddr(changeAD);
			}else if(selMod == 3) {
				System.out.println("수정하실 이름을 입력해주십시오. ex)김아무개");
				String changeName = scan.next();
				loginMember.setMname(changeName);
			}else {
				System.out.println("잘못된 입력입니다.");
				return;
			}
			
			int updateResult = mdao.updateMemberInfo(loginMember);
			
			if(updateResult == 0) {
				System.out.println("정보 수정과정에서 오류가 발생했습니다 다시 시도해주십시오");
				return;
			}
			
			System.out.println("정보 수정이 완료되었습니다 다시 로그인해주십시오");
			loginMember = loginGuest();
			return;
			
		} else if (inputNum == 2) {

			System.out.println("탈퇴하시겠습니까? [1]예 [2]아니오");
			int selDrop = scan.nextInt();
			if (selDrop == 1) {
				System.out.println("탈퇴하려는 계정의 아이디와 비밀번호를 다시 한 번 입력해주십시오.");
				System.out.print("아이디 : ");
				String dropId = scan.next();
				System.out.print("비밀번호 : ");
				String dropPw = scan.next();

				if (loginMember.getMid().equals(dropId) && loginMember.getMpw().equals(dropPw)) {
					int updateResult = mdao.updateMstateByLoginMember(loginMember);
					if (updateResult == 0) {
						System.out.println("탈퇴처리 과정에서 오류가 발생하였습니다 다시 시도해주십시오.");
						return;
					}
					System.out.println("탈퇴처리 되었습니다. 감사합니다.");
					logoutMember();
					return;
				}

			} else if (selDrop == 2) {
				System.out.println("메인으로 돌아갑니다");
				return;
			}

		} else if (inputNum == 0) {
			System.out.println("메인으로 돌아갑니다");
			return;
		}

	}

	public void updateGrade() {
		
		ArrayList<Grade> gradeList = gdao.getAllGrades();
		
		int mtotalPrice = mdao.selectMtotalPrice(loginMember);
		
		String newGrade = loginMember.getMgrade().getGrade();
		
		for(Grade gd : gradeList) {
			if(mtotalPrice >= gd.getPurprice()) {
				newGrade = gd.getGrade();
			}
		}
		
		if(!(newGrade.equals(loginMember.getMgrade().getGrade() ) ) ) {
			mdao.updateMgrade(loginMember, newGrade);
			System.out.println("회원님의 등급이 " + newGrade + "등급으로 변경되었습니다.");
			Grade updateGrade = gdao.selectGrade(newGrade);
			loginMember.setMgrade(updateGrade);
		}
		
		System.out.println("회원님의 현재 등급은 " + loginMember.getMgrade().getGrade() + "등급 입니다.");
		
	}

	public void updateProduct() {
		
		if(!cart.isEmpty()) {
			
			for(Orders od : cart) {
				pdao.cancelOrder(od);
			}
			
		}
		
	}
	
}
