package khc.springboot.project7.total.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherDto {

	private String dataType;
	private String baseDate;
	private String baseTime;
	private String category;
	private String nx;
	private String ny;

	private String fcstDate;
	private String fcstTime;
	private String fcstValue;

	private String obsrValue;

	public void codeToName(String code, String value) {
		String name = code;
		switch (code) {
		case "T1H":
			name = "기온";
			value = value + " ℃";
			break;
		case "RN1":
			name = "1시간 강수량";
			if(!value.equals("강수없음")) {
			value = value + " mm";
			}
			break;
		case "SKY":
			name = "하늘상태";
			switch (value) {
			case "1" : value = "맑음"; break;
			case "3" : value = "구름많음"; break;
			case "4" : value = "흐림"; break;
			}
			break;
		case "UUU":
			name = "동서바람성분";
			value = value + " m/s";
			break;
		case "VVV":
			name = "남북바람성분";
			value = value + " m/s";
			break;
		case "REH":
			name = "습도";
			value = value + " %";
			break;
		case "PTY":
			name = "강수형태";
			switch (value) {
			case "0" : value = "없음"; break;
			case "1" : value = "비"; break;
			case "2" : value = "비/눈"; break;
			case "3" : value = "눈"; break;
			case "5" : value = "빗방울"; break;
			case "6" : value = "빗방울눈날림"; break;
			case "7" : value = "눈날림"; break;
			}
			break;
		case "LGT":
			name = "낙뢰";
			value = value + " kA(킬로암페어)";
			break;
		case "VEC":
			name = "풍향";
			value = value + " deg";
			break;
		case "WSD":
			name = "풍속";
			value = value + " m/s";
			break;
		}
		this.fcstValue = value;
		this.category = name;
	}

}
