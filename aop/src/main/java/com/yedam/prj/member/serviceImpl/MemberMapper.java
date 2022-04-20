package com.yedam.prj.member.serviceImpl;

import com.yedam.prj.member.service.MemberVO;

public interface MemberMapper {

	MemberVO selectMember(MemberVO vo);
	int insertMember(MemberVO vo);
}
