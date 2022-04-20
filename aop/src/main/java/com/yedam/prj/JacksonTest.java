package com.yedam.prj;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yedam.prj.notice.vo.NoticeVO;

public class JacksonTest {
	
	static ObjectMapper om;
	@BeforeClass
	public static void init() {
		om = new ObjectMapper();
	}
	
	//@Test
	public void readTreeTest2() throws MalformedURLException, IOException {
		String infoUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=f5eef3421c602c6cb7ea224104795888&movieCd=20212725";
		JsonNode json = om.readTree(new URL(infoUrl));
		String drNm = json.get("movieInfoResult").get("movieInfo").get("directors").get(0).get("peopleNm").asText();
		System.out.println("감독 : " + drNm);
		
	}
	
	@Test
	public void readTreeTest3() throws JsonProcessingException, IllegalArgumentException  {
		String boxUrl = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieInfo.json?key=f5eef3421c602c6cb7ea224104795888&movieCd=20212725";
		RestTemplate rt = new RestTemplate();
		JsonNode js = rt.getForObject(boxUrl, JsonNode.class);
		JsonNode mvInfo = js.get("movieInfoResult").get("movieInfo");
		System.out.println(mvInfo);
		
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MovieInfoVO info = om.treeToValue(mvInfo, MovieInfoVO.class);
		
		System.out.println(info.getDirectors().get(0).get("peopleNm"));
	
	}
	
	//@Test
	public void readTreeTest() throws MalformedURLException, IOException {
		String boxUrl = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20220418";
		RestTemplate rt = new RestTemplate();
		JsonNode json = rt.getForObject(boxUrl, JsonNode.class);
		JsonNode mvList = json.get("boxOfficeResult").get("dailyBoxOfficeList");
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		MovieVO[] arr = om.treeToValue(mvList, MovieVO[].class);
		List<MovieVO> list = Arrays.asList(arr);
		System.out.println("1위: " + list.get(0).getMovieNm());
		System.out.println("2위: " + list.get(1).getMovieNm());
		
		//JsonNode json = om.readTree(new URL(boxUrl));
		//String movieNm = json.get("boxOfficeResult").get("dailyBoxOfficeList").get(0).get("movieNm").asText();
		//System.out.println("제목 : " + movieNm);
		//System.out.println("코드 : " + json.get("boxOfficeResult").get("dailyBoxOfficeList").get(0).get("movieCd").asText());
	
	}

	//@Test
	public void readTest() throws StreamReadException, DatabindException, MalformedURLException, IOException {	
		
		String str = "{\"id\":0,\"title\":\"제목:장예빈\",\"content\":\"내용:천재\",\"wdate\":null,\"hit\":0}";
		NoticeVO vo = om.readValue(str, NoticeVO.class);
		assertEquals(vo.getTitle(), "제목:장예빈");
		//System.out.println(vo.getTitle());
	}
	
	//@Test
	public void writeTest() throws JsonProcessingException {
		
		NoticeVO vo = NoticeVO.builder().title("제목:천재는 누구인가")
										.content("내용:그거슨 바로 장예빈")
										.build();
		
		String str = om.writeValueAsString(vo);
		
		//System.out.println(str);
	}
	
	
}
