package day15_1;

public abstract class Monster {
	// 몬스터의 공통 정보 ( 레벨, 이름, 체력, 공격 )
	int level;
	String name;
	int hp;
	int attack;
	// 몬스터의 공통 기능 ( 공격기능 )
	public Monster() {
		this.level = 1;
		this.hp = 100;
		this.attack = 5;
	}
	abstract void attack();
	// demon, zombie
	// 몬스터 별로 수행하는 공격 기술을 정의
	// DemonSkill 인터페이스, ZombieSkill 인터페이스
	abstract void takeDamege(int damage);
}










