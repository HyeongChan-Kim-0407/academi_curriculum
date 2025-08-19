package khc.springboot.project6.newsCrawllingProject.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ScrapeNewsDto {
	private String title;
	private String imgUrl;
	private String linkUrl;
}
