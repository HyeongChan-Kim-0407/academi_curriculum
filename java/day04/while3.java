package day04;

import java.util.Scanner;

public class while3 {

	public static void main(String[] args) {
		// 메뉴 주문
		Scanner scan = new Scanner(System.in);
		
		
		int americano = 0; //판매한 아메리카노 수
		int caffeLatte = 0; //판매한 카페라떼 수
		boolean cafeOpen = true;
		while(cafeOpen) {
			System.out.println("[1]아메리카노 [2]카페라떼");
			System.out.println("메뉴주문 : ");
			int selectMenu = scan.nextInt();
			
			//if
			/*if(selectMenu == 1) {
				americano++;
			} else if(selectMenu == 2) {
				caffeLatte++;
			} 
			}
			*/
			
			//switch
			
			switch(selectMenu) {
			case 1: //[1] 아메리카노를 선택한 경우
				americano++;
				System.out.println("아메리카노 선택\n");
				break;
			case 2: //[2] 카페라떼를 선택한 경우
				caffeLatte++;
				System.out.println("카페라떼 선택\n");
				break;
			default:
				System.out.println("없는 메뉴입니다.");
			}
			if(americano + caffeLatte >= 10) {
				cafeOpen = false;
			}
		}
		System.out.println("아메리카노 " + americano + "잔 판매");
		System.out.println("카페라떼 " + caffeLatte + "잔 판매");
		System.out.println("총 " + (caffeLatte + americano) + "잔 판매");
	
	}
}
		
		
		
		// 잔 수 제한 및 자동 영업종료
		
		/*
		int ame = 30; //판매할 아메리카노
		int cfl = 30; //판매할 카페라떼
		boolean cafeOpen1 = true;
		while(cafeOpen1) {
			System.out.println("[1]아메리카노 [2]카페라떼");
			System.out.print("주문 : ");
			int select = scan.nextInt();
			
			if(select == 1) {
				if(ame != 0) {
				System.out.println("몇 잔 주문하시겠습니까? : ");
				int amePick = scan.nextInt();
				if(amePick > ame) {
						System.out.println("최대 " + ame +"잔까지만 주문 가능합니다. 다시 주문해주십시오.");
						amePick = scan.nextInt();
						if(amePick <= ame) {
							ame = ame-amePick;
						}
				}else if(amePick <= ame) {
					ame = ame-amePick;
				}
			} else if(ame == 0) {
				System.out.println("아메리카노가 매진되었습니다.");
			}
				
			}else if(select == 2) {
				if(cfl != 0) {
				System.out.println("몇 잔 주문하시겠습니까? : ");
				int cflPick = scan.nextInt();
				if(cflPick > cfl) {
					System.out.println("최대 " + cfl + "잔까지만 주문 가능합니다. 다시 주문해주십시오.");
					cflPick = scan.nextInt();
					if(cflPick <= cfl) {
						cfl = cfl-cflPick;
					}
				}else if(cflPick <= cfl) {
					cfl = cfl-cflPick;
				}
				
			} else if(cfl == 0) {
				System.out.println("카페라떼가 매진되었습니다.");
			}
			
		}
			if(ame <= 0 && cfl <=0) {
				cafeOpen1 = false;
			}
		}//while문 종료
		System.out.println("오늘 영업이 종료되었습니다.");
		
	}
	
}
*/
		
		

	


