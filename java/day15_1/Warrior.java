package day15_1;

public class Warrior extends Player{
	public Warrior(int hp, int attack, int defence) {
		super(hp, attack, defence);
		this.armor = 5;
	}
	int armor;
	@Override
	int attack() {
		int damage = attack;
		System.out.println("검 공격("+damage+")을 합니다.");
		return damage;
	}
	@Override
	void defence() {
		System.out.println("피해를 "+(defence+armor)+"만큼 감소");
	}
	
	
	
}
