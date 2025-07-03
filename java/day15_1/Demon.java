package day15_1;

public class Demon extends Monster implements DemonSkill{

	@Override
	void attack() {
		System.out.println(name+"의 공격("+attack+")");
	}

	@Override
	void takeDamege(int damage) {
		this.hp = hp - damage;
		System.out.println(damage + "피해를 받았습니다.");
		System.out.println("현재체력은 "+hp+"입니다.");
	}

}
