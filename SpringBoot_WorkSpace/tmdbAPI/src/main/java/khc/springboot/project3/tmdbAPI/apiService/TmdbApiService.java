package khc.springboot.project3.tmdbAPI.apiService;

import java.io.IOException;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.springframework.stereotype.Component;

@Component
public class TmdbApiService {

	public String searchByMovieTitle(String title) {
		System.out.println("TmdbApiService-searchByMovieTitle() TMDB API 영화 검색 요청");
		// TMDB에 REQUEST > RESPONSE : httpClient 사용
		// 1. TMDB API URL 생성
		
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
			ClassicHttpRequest httpGet = ClassicRequestBuilder.get("https://api.themoviedb.org/3/search/movie")
					.addParameter("query", title)
					.addParameter("language", "ko-KR")
					.build();
			/* 인증 키 */
			httpGet.addHeader("accept", "application/json");
			// Bearer 토큰 방식 인증
			httpGet.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNTNiMGQzYmExNjY0ZjUzZTM0ODAxYjdmY2Q5ZTVhMyIsIm5iZiI6MTc1NDI2NTI2MS42MSwic3ViIjoiNjg4ZmY2YWRlODVlOTE0MzZlZjNlNGIzIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.mTr5bZSJ-0LOMu9xBnDYYIRTLX8vZfv9A0TGHxzm3HE");
			String responseData = httpclient.execute(httpGet, response -> {
				System.out.println(response.getCode() + " " + response.getReasonPhrase());
				final HttpEntity entity1 = response.getEntity();
				String data = EntityUtils.toString(entity1);
				EntityUtils.consume(entity1);
				return data;
			});
			return responseData;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	public String findUpcomingMovies() {
		
		try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
		    ClassicHttpRequest httpGet = ClassicRequestBuilder.get("https://api.themoviedb.org/3/movie/upcoming")
		    		.addParameter("language", "ko-KR")
		            .build();
		    httpGet.addHeader("accept", "application/json");
		    httpGet.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiNTNiMGQzYmExNjY0ZjUzZTM0ODAxYjdmY2Q5ZTVhMyIsIm5iZiI6MTc1NDI2NTI2MS42MSwic3ViIjoiNjg4ZmY2YWRlODVlOTE0MzZlZjNlNGIzIiwic2NvcGVzIjpbImFwaV9yZWFkIl0sInZlcnNpb24iOjF9.mTr5bZSJ-0LOMu9xBnDYYIRTLX8vZfv9A0TGHxzm3HE");
		    String responseData = httpclient.execute(httpGet, response -> {
		        System.out.println(response.getCode() + " " + response.getReasonPhrase());
		        final HttpEntity entity1 = response.getEntity();
		        String data = EntityUtils.toString(entity1);
		        EntityUtils.consume(entity1);
		        return data;
		    });
		    return responseData;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
