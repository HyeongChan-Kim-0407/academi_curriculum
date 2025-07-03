package character;

public class Archer extends Player implements Skill{
	
	int critical;
	
	@Override
	public void att() {
		System.out.println("활로 공격(" + ( attack + ( critical * attack ) ) + ") 합니다.");
	}
	
	@Override
	public void def() {
		System.out.println("방어(" + ( 10 ) + ") 합니다.");
	}
	
}
