package com.spring.bom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bom.model.iron.Board;
import com.spring.bom.model.iron.Follow;
import com.spring.bom.model.iron.HashTag;
import com.spring.bom.model.iron.Like_Bookmark;
import com.spring.bom.model.iron.User_Info;
import com.spring.bom.service.iron.BoardService;
import com.spring.bom.service.iron.FollowService;
import com.spring.bom.service.iron.HashTagService;
import com.spring.bom.service.iron.Like_BookmarkService;
import com.spring.bom.service.iron.User_InfoService;

//o jungchurl Controller
//- active navi support
//- view timeline
//- view sub_contents
//o profile support

@Controller
public class JungChurlController {
	@Autowired
	private User_InfoService us;

	@Autowired
	private BoardService bs;

	@Autowired
	private FollowService fs;

	@Autowired
	private HashTagService hs;

	@Autowired
	private Like_BookmarkService lbs;

	@GetMapping(value = "iron/timeline")
	public String goTimeline(HttpServletRequest request, Model model) {
		// System.out.println("[JungChurlController]");
		System.out.println("[JungChurlController] goTimeline start...");
		HttpSession session = request.getSession();
		System.out.println("[JungChurlController] Do -> us.getLoginUserInfo(21)");

		// 임시 로그인 세팅
		User_Info user = us.getLoginUserInfo(21); // DB에서 회원코드 21인 회원 정보 가져오기. 필요한것만!
		session.setAttribute("user", user); // Session에 user 데이터 저장
		System.out.println("[JungChurlController] Result : " + user + " -> us.getLoginUserInfo(21)");
		
		/*
		 * //업로드 파일경로 세션처리 String resourcePath =
		 * request.getSession().getServletContext().getRealPath("");
		 * System.out.println("resourcePath -> "+resourcePath);
		 * model.addAttribute("resourcePath",resourcePath);
		 */
		// 로그인유저가 볼 수 있는 글데이터 가져오기
		System.out.println("[JungChurlController] Do -> bs.getTimelineBoard(user.getUcode())");
		List<Board> bdlist = bs.getTimelineBoard(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + bdlist.size()
				+ " -> bs.getTimelineBoard(user.getUcode()).size()");

		//// 미디어파일 세부지정
		System.out.println("[JungChurlController] Setting Battach Type and Source -> start...");
		for (int i = 0; i < bdlist.size(); i++) {
			if (bdlist.get(i).getBattach() != null) {
				System.out.println("[" + i + "]" + " battach Data : " + bdlist.get(i).getBattach());
				bdlist.get(i).setBattachType(bdlist.get(i).getBattach().substring(0, 5));
				bdlist.get(i).setBattachSrc(bdlist.get(i).getBattach().substring(6));
				System.out.println("battach Type : " + bdlist.get(i).getBattachType() + " / battach Source : "
						+ bdlist.get(i).getBattachSrc());
			}
		}
		
		System.out.println("[JungChurlController] Setting Battach Type and Src -> done!!!");

		model.addAttribute("tl_list_size", bdlist.size());
		model.addAttribute("tl_list", bdlist);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = fs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = fs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		return "iron/timeline";
	}

	@RequestMapping(value = "iron/AjaxLikeAction", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Like_Bookmark ajaxLikeAction(@RequestParam int bcode, HttpSession session) {
		System.out.println("[JungChurlController] AjaxLikeAction start...");
		User_Info user = (User_Info) session.getAttribute("user");
		System.out.println("[JunbChurlController] login ucode -> " + user.getUcode());
		System.out.println("[JunbChurlController] bcode -> " + bcode);
		Like_Bookmark lb = new Like_Bookmark();
		lb.setUcode(user.getUcode());
		lb.setBcode(bcode);
		// 게시글의 북마크 여부 체크
		// step == 0 -> 북마크 x
		// 좋아요 여부 체크
		// 좋아요 x => step == 1 > insert
		// 좋아요 o => step == 2 > delete

		// step == 1 -> 북마크 o
		// 좋아요 여부 체크
		// 좋아요 x => step == 3 > update
		// 좋아요 o => step == 4 > update
		int step = lbs.checkBoardLike(lb); // update 또는 insert 두 케이스가 있다.
		System.out.println();
		lb.setLikeLogicStep(step);
		if (step == 1 || step == 3) {
			lb.setLtype("1");
		} else {
			lb.setLtype("0");
		}
		System.out.println("LikeLogicStep -> " + lb.getLikeLogicStep());
		int result = lbs.doBoardLike(lb);
		System.out.println("result -> " + result);
		lb = lbs.getBoardLikeCount(lb);
		System.out.println("LikeCount -> " + lb.getLikeCount());

		return lb;
	}

	// 게시글 북마크 추가/삭제 버튼 구현
	@RequestMapping(value = "iron/AjaxViewBoardOptions", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Like_Bookmark AjaxViewBoardOptions(@RequestParam int bcode, HttpSession session) {
		System.out.println("[JungChurlController] AjaxViewBoardOptions start...");
		User_Info user = (User_Info) session.getAttribute("user");
		System.out.println("[JunbChurlController] login ucode -> " + user.getUcode());
		System.out.println("[JunbChurlController] bcode -> " + bcode);
		Like_Bookmark lb = new Like_Bookmark();
		lb.setUcode(user.getUcode());
		lb.setBcode(bcode);

		lb = lbs.checkBoardBookmark(lb);
		System.out.println("lb.getBbtype -> " + lb.getBbtype());

		return lb;
	}

	//단일 게시물 출력
	@RequestMapping(value = "iron/singleBoard")
	public String goSingleBoard(@RequestParam String bcode, HttpSession session, Model model) {
		System.out.println("[JungChurlController] goSingleBoard start...");

		// 로그인 유저 정보 확인
		User_Info user = (User_Info) session.getAttribute("user");
		System.out.println("ucode -> " + user.getUcode());
		model.addAttribute("user", user);

		// 단일 게시글 정보 가져오기
		System.out.println("bcode -> " + bcode);
		Board board = new Board();
		board.setLoginUcode(user.getUcode());
		board.setBcode(Integer.parseInt(bcode));
		board = bs.getSingleBoard(board);
		System.out.println("board -> bucode" + board.getUcode());
		System.out.println("board -> bcontent" + board.getBcontent());

		//// 단일게시글 미디어파일 세부지정
		System.out.println("[JungChurlController] Setting Battach Type and Source -> start...");
		if (board.getBattach() != null) {
			System.out.println("battach Data : " + board.getBattach());
			board.setBattachType(board.getBattach().substring(0, 5));
			board.setBattachSrc(board.getBattach().substring(6));
			System.out.println("battach Type : " + board.getBattachType() + " / battach Source : "
					+ board.getBattachSrc());
		}
		System.out.println("[JungChurlController] Setting Battach Type and Src -> done!!!");
		model.addAttribute("board", board);
		
		//해당 댓글 가져오기
		List<Board> replylist = bs.getReplyList(Integer.parseInt(bcode));
		System.out.println("[JungChurlController] Result : listSize is " + replylist.size()
				+ " -> bs.getTimelineBoard(user.getUcode()).size()");

		//// 댓글들 미디어파일 세부지정
		System.out.println("[JungChurlController] Setting Battach Type and Source -> start...");
		for (int i = 0; i < replylist.size(); i++) {
			if (replylist.get(i).getBattach() != null) {
				System.out.println("[" + i + "]" + " battach Data : " + replylist.get(i).getBattach());
				replylist.get(i).setBattachType(replylist.get(i).getBattach().substring(0, 5));
				replylist.get(i).setBattachSrc(replylist.get(i).getBattach().substring(6));
				System.out.println("battach Type : " + replylist.get(i).getBattachType() + " / battach Source : "
						+ replylist.get(i).getBattachSrc());
			}
		}
		System.out.println("[JungChurlController] Setting Battach Type and Src -> done!!!");
		model.addAttribute("replylist_size", replylist.size());
		model.addAttribute("replylist", replylist);
		
		
		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = fs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		
		model.addAttribute("tag_list", hashtagList);

		return "iron/singleboard";
	}

	@GetMapping(value = "iron/profile")
	public String goProfile(@RequestParam String uatid, Model model, HttpSession session) {
		System.out.println("[JungChurlController] goProfile start...");
		//user - 세션에 존재하는 로그인 유저의 정보
		User_Info user = (User_Info)session.getAttribute("user");
		//someone - param uatid에 대칭되는 유저
		User_Info someone = new User_Info();
		someone.setUatid(uatid);
		//someone 에 uatid 유저정보 저장 -> 저장 정보 : 
		someone = us.getUserInfoUatid(someone);
		
		//uatid가 만약 존재하지 않다면 profile.jsp에서 존재하지 않는다고 출력한 뒤 login 중인 유저의 프로필 정보를 보여주자
		
		
		model.addAttribute("user", user);
		model.addAttribute("someone",someone);
		
		
		return "iron/profile";
	}
}