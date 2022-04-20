package com.yedam.prj.bank.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

	
	// �ݹ��Լ��� �����ڵ尡 ����
		@RequestMapping("/callback")
		public String bankCallback(String code) {
			// code�� ��ū �߱�
			//TokenVO vo = BankApi.getToken(code);
			//
			return "";
		}

		// ����� ������û
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
