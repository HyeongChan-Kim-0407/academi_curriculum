package khc.springboot.project6.newsCrawllingProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import khc.springboot.project6.newsCrawllingProject.domain.ScrapeNews;

public interface NewsRepository extends JpaRepository<ScrapeNews, Long> {

	ScrapeNews findByLinkUrl(String linkUrl);

	List<ScrapeNews> findBySection(String sectionNumber);
	
	@Query("SELECT n.linkUrl FROM ScrapeNews n WHERE n.portal = 'naver'")
	List<String> findAllNaverLinkUrl();

	List<ScrapeNews> findBySectionAndPortal(String sectionNumber, String portal);
	
	@Query("SELECT n.linkUrl FROM ScrapeNews n WHERE n.portal = 'daum'")
	List<String> findAllDaumLinkUrl();
	
	
	
}
