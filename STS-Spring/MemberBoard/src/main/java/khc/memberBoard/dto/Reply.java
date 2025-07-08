package khc.memberBoard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Reply {
	int rno;
	String rwriter;
	int rbno;
	String rcontents;
	String rdate;
	String rstate;
}
