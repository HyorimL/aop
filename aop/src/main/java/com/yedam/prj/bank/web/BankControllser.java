package com.yedam.prj.bank.web;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankControllser {

	@GetMapping("/accountList")
	public List<AccountVO> accountList(BankVO vo) {
		return BankApi.getAccountList(vo);
	}

	@GetMapping("/balance")
	public long balance(BankVO vo) {
		return BankApi.getBalanceInfo(vo);
	}

	@GetMapping("/transList")
	public Map transList(BankVO vo) {
		return BankApi.getTransaction(vo);
	}

	

}
