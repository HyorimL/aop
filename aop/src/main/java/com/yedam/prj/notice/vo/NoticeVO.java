package com.yedam.prj.notice.vo;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//아래 모두 lombok에서 제공하는 어노테이션
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class NoticeVO {
	private int id;
	private String title;
	private String content;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="Asia/Seoul")	
	private Date wdate;
	private int hit;
	@JsonIgnore private String fileName;
	@JsonIgnore private String uuidFile;
}
