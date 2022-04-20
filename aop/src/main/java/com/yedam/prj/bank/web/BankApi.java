package com.yedam.prj.bank.web;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BankApi {

	static String orgCode = "M202200736";
	static String oob_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJNMjAyMjAwNzM2Iiwic2NvcGUiOlsib29iIl0sImlzcyI6Imh0dHBzOi8vd3d3Lm9wZW5iYW5raW5nLm9yLmtyIiwiZXhwIjoxNjU4MTk0MjA5LCJqdGkiOiJjMGRjZjkwNy02ZTUyLTQ1YzQtOWYzYy04ZTQ5NGQ1ZjQ3ZTYifQ.ukkHwCiLEw5uRb7OZByZcS0z0EQlvOD3UxzXIuELqgQ";
	
	
	
	public static long getBalanceInfo(BankVO vo) {

		long balance = 0;

		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/balance/fin_num";

		String param = "bank_tran_id=" + orgCode + "U" + getSequence();
		param += "&tran_dtime=" + getDate();
		param += "&fintech_use_num=" + vo.getFintechUseNum();

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + vo.getAccessToken());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null,
				headers);

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Map> response = restTemplate.exchange(reqURL + "?" + param, HttpMethod.GET, request, Map.class);

		// System.out.println(response.getBody());

		Map map = response.getBody();
		balance = Long.valueOf((String) map.get("balance_amt"));

		// DecimalFormat coma = new DecimalFormat("#,###,###,###");

		return balance;
	}

	public static List<AccountVO> getAccountList(BankVO vo) {

		String reqURL = "https://testapi.openbanking.or.kr/v2.0/account/list";

		String param = "user_seq_no=" + vo.getUserSeqNo();
		param += "&include_cancel_yn=N";
		param += "&sort_order=D";
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + vo.getAccessToken());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(null,
				headers);

		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<AccountList> response = restTemplate.exchange(reqURL + "?" + param, HttpMethod.GET, request, AccountList.class);

		ResponseEntity<String> response = restTemplate.exchange(reqURL + "?" + param, HttpMethod.GET, request,
				String.class);

		// str -> jsonNode(readTree)
		ObjectMapper om = new ObjectMapper();

		JsonNode reslist = null;
		try {
			reslist = om.readTree(response.getBody());

		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonNode res = reslist.get("res_list");

		List<AccountVO> returnVal = new ArrayList<AccountVO>();
		System.out.println(res);
		// "res_list" 필드 -> jsonString -> AccountVO[] -> List<VO>
		for (JsonNode i : res) {
			AccountVO n = new AccountVO();
			n.setBank_name(i.get("bank_name").asText());
			n.setAccount_holder_name(i.get("account_holder_name").asText());
			n.setAccount_num_masked(i.get("account_num_masked").asText());
			n.setFintech_use_num(i.get("fintech_use_num").asText());
			n.setAccount_alias(i.get("account_alias").asText());
			returnVal.add(n);
		}

		return returnVal;
	}

	public static String getRealName() {
		
		String reqUrl = "https://testapi.openbanking.or.kr/v2.0/inquiry/real_name";
		
		Map<String, String> param = new HashMap<>();
		param.put("bank_tran_id", "M202200736U" + getSequence());
		param.put("bank_code_std", "090");
		param.put("account_num", "1234567890123456");
		param.put("account_holder_info_type", " ");
		param.put("account_holder_info", "880101");
		param.put("tran_dtime", getDate());
		
		String jsonValue = "";
		ObjectMapper om = new ObjectMapper();
		try {
			jsonValue = om.writeValueAsString(param);
			System.out.println(jsonValue);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json; charset=UTF-8");
		headers.set("Authorization", "Bearer " + oob_token);

		//보낼 데이터의 타입
		HttpEntity<String> request = new HttpEntity<String>(jsonValue, headers);
		
		//맵 객체로 변환시켜서 담아 보냄
		RestTemplate restTemplate = new RestTemplate();
		Map reponse = restTemplate.postForObject(reqUrl, request, Map.class);
		System.out.println(reponse);
		
		String realName = String.valueOf(reponse.get("account_holder_name")); //이름출력
		System.out.println(realName);
		return realName;
	}
	
	public static String getToken() {
		
		String tkUrl =  "https://testapi.openbanking.or.kr/oauth/2.0/token";
		String redirect_uri = "http://localhost/bank/callback";
		String client_id = "56623ec9-ff89-4e8d-b59b-3ab59a9f0b97";
		
		Map<String, String> param = new HashMap<>();
		param.put("code", "");
		param.put("client_id", client_id);
		param.put("client_secret", );
		param.put("redirect_uri", redirect_uri);
		param.put("grant_type", "authorization_code");
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		headers.set("Authorization", "Bearer " + oob_token);
		
		return "";
	}
	
	public static Map getTransaction(BankVO vo) {
		
		String reqUrl = "https://testapi.openbanking.or.kr/v2.0/account/transaction_list/fin_num";
		
		//parameter setting
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("bank_tran_id", "M202200736U" + getSequence());
		map.add("fintech_use_num", vo.getFintechUseNum());
		map.add("inquiry_type", "A");
		map.add("inquiry_base", "D");
		map.add("from_date", "20190101");
		map.add("to_date", "20190101");
		map.add("sort_order", "D");
		map.add("tran_dtime", "20190101010101");
		
		//MultiValueMap => queryString
		URI uri = UriComponentsBuilder.fromUriString(reqUrl)
		        .queryParams(map)
		        .build().encode()
		        .toUri();
		
		//header setting
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer" + vo.getAccessToken());
		
		//request 생성
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		
		//RestTemplate을 이용하여 request를 보내고 response 받고 json 결과를 객체로 파싱
		RestTemplate rt = new RestTemplate();
		ResponseEntity<Map> res = rt.exchange(uri, HttpMethod.GET, request, Map.class);
		System.out.println(res.getBody());

		return res.getBody();
	}
	
	private static String getDate() {
		String result;
		SimpleDateFormat dTime = new SimpleDateFormat("yyyyMMddhhmmss");
		Date nd = new Date();
		result = dTime.format(nd);
		return result;
	}

	private static String getSequence() {
		long curTime = System.currentTimeMillis();
		String num = String.valueOf(curTime).substring(4);
		return num;
	}

}
