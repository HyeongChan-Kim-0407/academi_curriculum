package khc.springboot.project5.webSocket.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class KakaoUserDto {
	
	private String userId; // 카카오톡 사용자 ID
	private String nickname; // 카카오톡 사용자 닉네임
	private String profileImage; // 카카오톡 사용자 프로필 이미지 URL
	
}
