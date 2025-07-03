 package academiProject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OrderMain {

    public static void main(String[] args) {

        AdminService adsvc = new AdminService();
        UserOrderService usvc = new UserOrderService();
        Scanner scan = new Scanner(System.in);
        // 관리자 객체 생성
        
        String grade = null;
        boolean run = true;

        while (run) {
        	
        	adsvc.checkOrderState();
        	
        	Member loginMember = usvc.getloginMember();
            if(loginMember == null) {
            	loginMember = usvc.loginGuest();
            }
            System.out.println("\n=====메뉴=====");
            
            if(loginMember.getMid().equals("ADMIN")) {
            	grade = "관리자";
            }else if(!loginMember.getMid().equals("ADMIN") && !loginMember.getMid().equals("GUEST")) {
            	grade = "일반회원";
            }else if(loginMember.getMid().equals("GUEST")) {
            	grade = "guest";
            }
            
            System.out.println(loginMember.getMname() + "님 로그인");
            
            if (grade == "guest") {
                System.out.println("[1] 상품주문 [2] 장바구니 [3] 주문내역 [4] 회원가입 [5] 로그인 [0] 종료");
                System.out.print("메뉴선택>> ");
                int selectMenu;
                try {
                    selectMenu = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("숫자를 입력해 주세요.");
                    scan.nextLine(); // 입력 버퍼 정리
                    continue; // 메뉴를 다시 보여줌
                }

                switch (selectMenu) {
                    case 1: // 상품주문
                        usvc.searchProduct();
                        break;
                    case 2: // 장바구니
                        usvc.cart();
                        break;
                    case 3: // 주문내역
                        usvc.orderList();
                        break;
                    case 4: // 회원가입
                        usvc.joinMember();
                        break;
                    case 5: // 로그인
                        usvc.loginMember();
                    	break;
                    case 0:
                        run = false;
                        usvc.updateProduct();
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                }

            } else if (grade == "관리자") {
                // 관리자 메뉴
                System.out.println("[1] 상품등록 [2] 정보수정 [3] 회원관리 [4] 주문관리 [0] 종료");
                System.out.print("메뉴선택>> ");
                int selectMenu;
                try {
                    selectMenu = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("숫자를 입력해 주세요.");
                    scan.nextLine(); // 입력 버퍼 정리
                    continue; // 메뉴를 다시 보여줌
                }

                switch (selectMenu) {
                    case 1:
                        adsvc.productInsert();
                        break;
                    case 2:
                        adsvc.infoManage();
                        break;
                    case 3:
                        adsvc.memberList();
                        break;
                    case 4:
                    	adsvc.manageOrders();
                    	break;
                    case 0: 
                    	run = false;
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                }

            } else if(grade == "일반회원"){
                // 일반 사용자 메뉴
                System.out.println("[1] 상품주문 [2] 장바구니 [3] 주문내역 [4] 회원정보 [5] 로그아웃 [0] 종료");
                System.out.print("메뉴선택>> ");
                int selectMenu;
                try {
                    selectMenu = scan.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("숫자를 입력해 주세요.");
                    scan.nextLine(); // 입력 버퍼 정리
                    continue; // 메뉴를 다시 보여줌
                }

                switch (selectMenu) {
                    case 1: // 상품주문
                    	usvc.searchProduct();
                        break;
                    case 2: // 장바구니
                    	usvc.cart();
                        break;
                    case 3: // 주문내역
                        usvc.orderList();
                        break;
                    case 4: // 회원정보
                        usvc.memberInfo();
                        break;
                    case 5: // 로그아웃
                        usvc.updateGrade();
                        usvc.logoutMember();
                        System.out.println("로그아웃 되었습니다.");
                        break;
                    case 0:
                        run = false;
                        usvc.updateGrade();
                        break;
                    default:
                        System.out.println("잘못된 입력입니다.");
                } 
            }
        }

        System.out.println("프로그램 종료");
        scan.close();
    }
}
