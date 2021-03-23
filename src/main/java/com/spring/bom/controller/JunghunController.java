package com.spring.bom.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bom.model.hoon.Junghun;
import com.spring.bom.model.iron.Follow;
import com.spring.bom.model.iron.HashTag;
import com.spring.bom.model.iron.User_Info;
import com.spring.bom.service.hoon.JunghunService;

@Controller
public class JunghunController {
	@Autowired
	private JunghunService js;

	@RequestMapping(value = "hoon/explore")
	public String explore(Model model, Junghun junghun, HttpSession session) throws Exception {
		System.out.println("hoon Controller explore Start");
		User_Info loginUser = (User_Info) session.getAttribute("user");
		System.out.println("explore -> " + loginUser.getUemail());

		// 로그인 ucode 전역 설정
		junghun.setLoginUcode(loginUser.getUcode());
		System.out.println("junghun.getLoginUcode() -> " + junghun.getLoginUcode());
		List<Junghun> listCount = js.listCount(junghun);
		List<Junghun> listHash = js.listHash(junghun);
		List<Junghun> searchkeyword = js.searchkeyword(junghun);

		// 실시간 해시태그 순위
		System.out.println("[junghun] Do -> hts.getHashTagRanking()");
		List<Junghun> hashtagList = js.listTrend();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[junghun] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = js.getSuggestFollowList1(loginUser.getUcode());
		System.out.println("[junghun] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist2_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[junghun] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = js.getSuggestFollowList2(loginUser.getUcode());
		System.out.println("[junghun] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		System.out.println("searchkeyword count::" + searchkeyword.size());
		System.out.println("listCount count:: " + listCount.size());
		System.out.println("listHash count:: " + listHash.size());

		model.addAttribute("searchkeyword", searchkeyword);
		model.addAttribute("listHash", listHash);
		model.addAttribute("listCount", listCount);
		return "hoon/explore";
	}

	@RequestMapping(value = "hoon/searchView")
	public String searchView(HttpServletRequest request, Model model, Junghun junghun, String search,
			HttpSession session) throws Exception {
		System.out.println("hoon Controller search Start");
		System.out.println("Controller ::" + search);
		User_Info loginUser = (User_Info) session.getAttribute("user");
		System.out.println("explore -> " + loginUser.getUemail());
		if (search.trim().equals("") || search.trim() == null) {
			return "hoon/explore";
		}

		// 로그인 ucode 전역 설정
		junghun.setLoginUcode(loginUser.getUcode());
		System.out.println("junghun.getLoginUcode() -> " + junghun.getLoginUcode());
		String searchkeyword1 = junghun.getSearch();
		if (searchkeyword1.charAt(0) == '@') {
			searchkeyword1 = String.format(searchkeyword1.substring(1), searchkeyword1);
			junghun.setSearch(searchkeyword1);
		}

		List<Junghun> listSearch = js.listSearch(junghun);

		List<Junghun> listUser = js.listUser(junghun);
		List<Junghun> listNew = js.listNew(junghun);
		List<Junghun> searchlistall = js.searchlistall(junghun);

		// input search text데이터 db에 저장값 설정
		String Hashtag = junghun.getSearch();
		if (Hashtag.charAt(0) == '#') {
			Hashtag = String.format("<a href=\"searchView?search=%%23%s\">%s</a>", Hashtag.substring(1), Hashtag);
			System.out.println("SearchData에 저장될 Hashtag 명::" + Hashtag);
			junghun.setSearch(Hashtag);
		}
		int searchData = js.searchData(junghun);

		List<Junghun> searchkeyword = js.searchkeyword(junghun);
		List<Junghun> searchblock = js.searchblock(junghun);

		// 사진.동영상 세부지정
		System.out.println("Junghun Controller 첨부파일 지정 Start");
		for (int i = 0; i < listSearch.size(); i++) {
			if (listSearch.get(i).getBattach() != null) {
				System.out.println("[" + i + "]" + " battach Data : " + listSearch.get(i).getBattach());
				listSearch.get(i).setBattachType(listSearch.get(i).getBattach().substring(0, 5));
				listSearch.get(i).setBattachSrc(listSearch.get(i).getBattach().substring(6));
				System.out.println("battach Type : " + listSearch.get(i).getBattachType() + " / battach Source : "
						+ listSearch.get(i).getBattachSrc());
			}
		}

		List<Junghun> searchbattach = js.searchbattach(junghun);

		for (int i = 0; i < searchbattach.size(); i++) {
			if (searchbattach.get(i).getBattach() != null) {
				System.out.println("[" + i + "]" + " battach Data : " + searchbattach.get(i).getBattach());
				searchbattach.get(i).setBattachType(searchbattach.get(i).getBattach().substring(0, 5));
				searchbattach.get(i).setBattachSrc(searchbattach.get(i).getBattach().substring(6));
				System.out.println("battach Type : " + searchbattach.get(i).getBattachType() + " / battach Source : "
						+ searchbattach.get(i).getBattachSrc());
			}
		}
		model.addAttribute("searchbattach", searchbattach);
		System.out.println(searchbattach.size() + "::  사진첨부파일 ");

		List<Junghun> searchbattachvideo = js.searchbattachvideo(junghun);
		for (int i = 0; i < searchbattachvideo.size(); i++) {
			if (searchbattachvideo.get(i).getBattach() != null) {
				System.out.println("[" + i + "]" + " battach Data : " + searchbattachvideo.get(i).getBattach());
				searchbattachvideo.get(i).setBattachType(searchbattachvideo.get(i).getBattach().substring(0, 5));
				searchbattachvideo.get(i).setBattachSrc(searchbattachvideo.get(i).getBattach().substring(6));
				System.out.println("battach Type : " + searchbattachvideo.get(i).getBattachType()
						+ " / battach Source : " + searchbattachvideo.get(i).getBattachSrc());
			}
		}
		model.addAttribute("searchbattachvideo", searchbattachvideo);
		System.out.println(searchbattachvideo.size() + "::  동영상첨부파일 ");
		
		List<Junghun> searchbattach2 = js.searchbattach2(junghun);

		for (int i = 0; i < searchbattach2.size(); i++) {
			if (searchbattach2.get(i).getBattach() != null) {
				System.out.println("[" + i + "]" + " battach Data : " + searchbattach2.get(i).getBattach());
				searchbattach2.get(i).setBattachType(searchbattach2.get(i).getBattach().substring(0, 5));
				searchbattach2.get(i).setBattachSrc(searchbattach2.get(i).getBattach().substring(6));
				System.out.println("battach Type : " + searchbattach2.get(i).getBattachType() + " / battach Source : "
						+ searchbattach2.get(i).getBattachSrc());
			}
		}
		model.addAttribute("searchbattach2", searchbattach2);
		System.out.println(searchbattach2.size() + "::  사진첨부파일2 ");

		List<Junghun> searchbattachvideo2 = js.searchbattachvideo2(junghun);
		for (int i = 0; i < searchbattachvideo2.size(); i++) {
			if (searchbattachvideo2.get(i).getBattach() != null) {
				System.out.println("[" + i + "]" + " battach Data : " + searchbattachvideo2.get(i).getBattach());
				searchbattachvideo2.get(i).setBattachType(searchbattachvideo2.get(i).getBattach().substring(0, 5));
				searchbattachvideo2.get(i).setBattachSrc(searchbattachvideo2.get(i).getBattach().substring(6));
				System.out.println("battach Type : " + searchbattachvideo2.get(i).getBattachType()
						+ " / battach Source : " + searchbattachvideo2.get(i).getBattachSrc());
			}
		}
		model.addAttribute("searchbattachvideo2", searchbattachvideo2);
		System.out.println(searchbattachvideo2.size() + "::  동영상첨부파일2 ");

		// 실시간 해시태그 순위
		System.out.println("[junghun] Do -> hts.getHashTagRanking()");
		List<Junghun> hashtagList = js.listTrend();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[j] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = js.getSuggestFollowList1(loginUser.getUcode());
		System.out.println("[j] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist2_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[j] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = js.getSuggestFollowList2(loginUser.getUcode());
		System.out.println("[j] Result : listSize is " + suggestFlist2.size());
		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		System.out.println("searchblock ::" + searchblock.size());
		System.out.println("searchkeyword count::" + searchkeyword.size());
		System.out.println("검색데이터 저장되면 1 ::" + searchData);
		System.out.println("content ::" + listSearch.size());
		System.out.println("user ::" + listUser.size());
		System.out.println("new Content ::" + listNew.size());

		model.addAttribute("searchlistall", searchlistall);
		model.addAttribute("searchblock", searchblock);
		model.addAttribute("searchkeyword", searchkeyword);
		model.addAttribute("search", search);
		model.addAttribute("listSearch_size", listSearch.size());
		model.addAttribute("listSearch", listSearch);
		model.addAttribute("listUser", listUser);
		model.addAttribute("listNew", listNew);
		return "hoon/searchView";
	}

	@RequestMapping(value = "searchlike")
	@ResponseBody
	public String searchlike(Model model, Junghun junghun, HttpSession session) {
		System.out.println("searchlike ::1");
		User_Info loginUser = (User_Info) session.getAttribute("user");
		System.out.println("explore -> " + loginUser.getUemail());
		System.out.println("searchlike ::2");
		int jh = js.searchlike(junghun);
		System.out.println("searchlike 실행::" + jh);
		System.out.println("searchlike ::3");
		return "redirect:";
	}

	@RequestMapping(value = "deleteRow")
	@ResponseBody
	public String deleteRow(Model model, HttpSession session) {
		System.out.println("del ::1");
		User_Info loginUser = (User_Info) session.getAttribute("user");
		System.out.println("Ajax DeleteRow check loginUser.getUcode() -> " + loginUser.getUcode());
		System.out.println("explore -> " + loginUser.getUemail());
		System.out.println("del ::2");
		int jh = js.deleterow(loginUser.getUcode());
		System.out.println("deleteRow del - 리턴되면:: " + jh);
		System.out.println("del ::3");
		return "redirect:hoon/explore";
	}
	/*
	 * 1. explore.jsp 또는 searchView.jsp 의 '검색창'의 드랍다운 요소 Ajax가 실행됬을 때 1 - 1. Ajax
	 * 함수실행 1 - 2. deleteRow 수행 1 - 3. 결과 반환 (1 - 3 - 1. explore 페이지가 나오지 않을 까...?)
	 * 2.
	 */
}
