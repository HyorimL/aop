package com.yedam.prj.bank.web;

import lombok.Data;

@Data
public class AccountVO {

	private String bank_name; //은행명
	private String account_num_masked; //계좌번호
	private String account_holder_name; //예금주
	private String fintech_use_num; //
	private String account_alias;
}
