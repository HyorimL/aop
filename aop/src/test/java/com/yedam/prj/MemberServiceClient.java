package com.yedam.prj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.prj.member.service.MemberService;
import com.yedam.prj.member.service.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/spring/*-context.xml")
public class MemberServiceClient {
	
	@Autowired MemberService memberService;
	@Test
	public void insertMember() {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
		
		MemberVO vo = MemberVO.builder()
				.id("test2")
				.name("test2")
				.password(encoder.encode("1111"))
				.author("USER")
				.build();
		
		memberService.insertMember(vo);
	}

}
