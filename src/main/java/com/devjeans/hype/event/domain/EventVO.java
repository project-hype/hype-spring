package com.devjeans.hype.event.domain;

import java.util.Date;
import java.util.List;

import com.devjeans.hype.member.domain.FavoriteVO;

import lombok.Data;

@Data
public class EventVO {

	private Long eventId;
	private Long branchId;
	private Long eventTypeId;
	private Long categoryId;
	private String title;
	private String content;
	private String imageUrl;
	private Date startDate;
	private Date endDate;
	private int viewCount;
	private String detailAddress;
	
	private CategoryVO category;
	private List<EventHashtagVO> eventHashtagList;
	private List<StarScoreVO> starScoreList;
	private List<FavoriteVO> favoriteList;
	private EventTypeVO eventType;
	private BranchVO branch;
}