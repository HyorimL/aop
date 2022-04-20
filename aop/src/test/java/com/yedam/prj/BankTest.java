package com.yedam.prj;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.yedam.prj.bank.web.AccountVO;
import com.yedam.prj.bank.web.BankApi;
import com.yedam.prj.bank.web.BankVO;

public class BankTest {

	@Test
	public void getTransfer() {
		BankVO vo = new BankVO();
		BankApi.getTransfer(vo);
	}
	
	//@Test
	public void getRealName() {
		String name = BankApi.getRealName();
		assertEquals(name, "이효림");
	}
	
	
	//@Test
	public void getBalance() {
		BankVO vo = new BankVO();
		long balance = BankApi.getBalanceInfo(vo);
        System.out.println("잔액 " + balance + "원");
        
	}
	
	//@Test
	public void getAccountList() throws JsonMappingException, JsonProcessingException {
		BankVO vo = new BankVO();
		List<AccountVO> list = BankApi.getAccountList(vo);
		System.out.println(list);
	}
}
