package day15_1;

public class Archer extends Player{
	public Archer(int hp, int attack, int defence) {
		super(hp, attack, defence);
		this.critical = 2;
	}
	int critical;
	@Override
	int attack() {
		int damage = attack * ( critical );
		System.out.println("활 공격("+damage+")을 합니다.");
		return damage;
	}
	@Override
	void defence() {
		System.out.println("피해를 "+defence+"만큼 감소");
	}
}
