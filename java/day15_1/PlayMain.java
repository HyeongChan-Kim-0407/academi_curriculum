package day15_1;

public class PlayMain {

	public static void main(String[] args) {
		// 캐릭터 생성
		System.out.println("캐릭터 생성 직업선택");
		System.out.println("[1]전사 [2]궁수");
		int selectJob = 2;
		Player player;
		switch(selectJob) {
		case 1:
			player = new Warrior(1, 10,10);
			break;
		case 2:
			player = new Archer(1, 10,10);
			break;
		default:
			player = new Warrior(1, 10,10);
		}
		System.out.println("사냥할 몬스터");
		System.out.println("[1]데몬 [2]좀비");
		Monster monster = new Demon();
		while(true) {
			int damage = player.attack();
			monster.takeDamege(damage);
			
			
			if(monster.hp <= 0) {
				System.out.println("몬스터를 사냥했습니다.");
				break;
			}
		}
		
	}
}












