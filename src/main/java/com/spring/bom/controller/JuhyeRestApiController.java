package com.spring.bom.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.bom.model.god.JHUser_info;
import com.spring.bom.service.god.JHUserService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JuhyeRestApiController {
	
	private final JHUserService us;
	//검색한 유저 목록을 가져오는 REST API
	@RequestMapping(value = "god/getSearchUser", produces = "application/json;charset=UTF-8")
	public List<JHUser_info> getSearchUser(String search_value) {
		System.out.println("[GOD] start JuhyeRestApiController getSearchUser");
		List<JHUser_info> searchList = us.getSearchList(search_value);
		/*
		 * List<UserDto> userCollect=searchList.stream() .map(m->new
		 * UserDto(m.getUnickname(), m.getUatid(), m.getUcode(), m.getUimage()))
		 * .collect(Collectors.toList());
		 */
		//return new Result(userCollect);
		
		return searchList;
	}
	
	
	@Data
	@AllArgsConstructor
	class Result<T>{
		private T data;
	}
	
	@Data
	@AllArgsConstructor
	class UserDto{
		private String unickname;
		private String uatid;
		private int ucode;
		private String uimage;
	}
}
