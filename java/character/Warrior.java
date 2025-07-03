package character;

public class Warrior extends Player implements Skill {
	
	int armor;
	
	@Override
	public void att() {
		System.out.println("칼로 공격(" + ( attack + 10 ) + ") 합니다.");
	}
	
	@Override
	public void def() {
		System.out.println("방어(" + ( armor + 10 ) + ") 합니다.");
	}
	
}
