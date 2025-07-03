package day15_1;

public abstract class Player {
	// 공통으로 사용되는 필드나 메소드를 정의
	// 직접 객체를 만들 수 없다.
	// 필드
	int hp;			
	int attack;
	int defence;
	// 일반 메소드
	void move(int x, int y) {
		System.out.println("캐릭터가 ("+x+","+y+") 좌표로 이동");
	}
	public Player(int hp, int attack, int defence) {
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
	}
	// 추상 메소드
	abstract int attack();
	abstract void defence();
}










