package com.yedam.prj.member.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.prj.member.service.MemberService;
import com.yedam.prj.member.service.MemberVO;
@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {

	@Autowired
	private MemberMapper map;
	
	@Override
	public MemberVO selectMember(MemberVO vo) {
		return map.selectMember(vo);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO vo = new MemberVO();
		vo.setId(username);
		MemberVO result = map.selectMember(vo);
		if(result == null) {
			throw new UsernameNotFoundException("no user");
		}
		return result;
	}

	@Override
	@Transactional
	public int insertMember(MemberVO vo) {
		map.insertMember(vo); 
		map.insertMember(vo); //error
		return 1;
	}

}
