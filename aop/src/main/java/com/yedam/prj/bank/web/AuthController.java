package com.yedam.prj.bank.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

	
	// 콜백함수로 인증코드가 들어옴
		@RequestMapping("/callback")
		public String bankCallback(String code) {
			// code로 토큰 발급
			//TokenVO vo = BankApi.getToken(code);
			//
			return "";
		}

		// 사용자 인증요청
		@RequestMapping("/bankAuth")
		public String bankAuth() throws Exception {
			String redirect_uri = "http://localhost/prj/callback";
			String client_id = "56623ec9-ff89-4e8d-b59b-3ab59a9f0b97";

			String reqUrl = "https://testapi.openbanking.or.kr/oauth/2.0/authorize";
			String url = reqUrl + "?response_type=code" + "&client_id=" + client_id + "&redirect_uri=" + redirect_uri
					+ "&scope=inquiry transfer login" + "&state=12345678901234567890123456789012" + "&auth_type=0";

			return "redirect:" + url;
		}
		
}
