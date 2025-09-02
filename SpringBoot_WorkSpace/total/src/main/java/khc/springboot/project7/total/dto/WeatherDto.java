package khc.springboot.project7.total.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class WeatherDto {
	
	private String dataType;
	private String baseDate;
	private String baseTime;
	private String category;
	private String fcstDate;
	private String fcstTime;
	private String fcstValue;
	private String nx;
	private String ny;
	private String obsrValue;
	
	
}
