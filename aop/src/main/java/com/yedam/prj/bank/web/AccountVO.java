package com.yedam.prj.bank.web;

import lombok.Data;

@Data
public class AccountVO {

	private String bank_name; //�����
	private String account_num_masked; //���¹�ȣ
	private String account_holder_name; //������
	private String fintech_use_num; //
	private String account_alias;
}
