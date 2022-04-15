package com.yedam.prj.notice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.prj.notice.service.NoticeService;
import com.yedam.prj.notice.vo.NoticeVO;


@Controller
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/noticeList.do")
	public String noticeList(@RequestParam(required = false, defaultValue = "1") int page, Model model) {
		model.addAttribute("notices", noticeService.noticeSelectList(page));
		return "notice/noticeList";
	}
	
	@PostMapping("/noticeSearch.do")
	@ResponseBody
	public List<NoticeVO> noticeSearch(@RequestParam String key, @RequestParam String val) {
		
//		List<NoticeVO> list = new ArrayList<NoticeVO>(); 
//		list = noticeDao.noticeSearchList(key,val);
		 
		return noticeService.noticeSearchList(key,val);
	}
}
