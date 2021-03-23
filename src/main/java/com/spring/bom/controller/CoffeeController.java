package com.spring.bom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.bom.model.bro.User_info;
import com.spring.bom.model.coffee.BoardUser_info;
import com.spring.bom.model.coffee.ReportUser_infoBoard;
import com.spring.bom.model.iron.HashTag;
import com.spring.bom.model.iron.User_Info;
import com.spring.bom.model.coffee.CoffeeUser_info;
import com.spring.bom.service.coffee.CoffeeBoardService;
import com.spring.bom.service.coffee.ReportService;
import com.spring.bom.service.coffee.User_infoService;
import com.spring.bom.service.iron.HashTagService;
import com.spring.bom.service.iron.User_InfoService;



@Controller
public class CoffeeController {
	@Autowired
	private User_infoService uis;
	
	@Autowired
	private CoffeeBoardService bs;
	
	@Autowired
	private ReportService rs;
	
	@Autowired
	private HashTagService hs;
	
	@Autowired
	private User_InfoService us;
	
	
//	@GetMapping(value = "/coffee/example")
//	public String example() {
//		System.out.println("CoffeeController example start..");
//		return "coffee/example";
//	}
	
	@GetMapping(value = "/coffee/logout")
	public String logout(HttpServletRequest req) {
		String url = "redirect:/bro/index";
		System.out.println("CoffeeController logout start..");
		HttpSession session = req.getSession();
		User_info ui = null;
		try {
			ui = (User_info)session.getAttribute("login");
			uis.logout(ui);
			session.invalidate();
		}catch (Exception e) {
			System.out.println("CoffeeController logout ->"+e.getMessage());
		}
		
		return url;
	}
	
	@PostMapping(value = "/coffee/deleteBom_Profile")
	public String deleteBom_Profile(HttpServletRequest req, Model model) {
		String url = "redirect:/iron/profile";
		System.out.println("CoffeeController deleteBom_Profile start..");
//		int bcode = Integer.parseInt(req.getParameter("coffeeBoardBcode"));
//		System.out.println("CoffeeController deleteBom bcode->"+bcode);
		
//		return url+"?uatid=iron";
		return url+"?uatid="+deleteBom(req);
	}
	
	public String deleteBom(HttpServletRequest req) {
		HttpSession session = req.getSession();
		int bcode = -1;
		User_Info ui = new User_Info();
		String delBoardUatid = "";
		int bbcode = 0;
		try {
			ui = (User_Info)session.getAttribute("user");
			bcode = Integer.parseInt(req.getParameter("coffeeBoardBcode"));
			delBoardUatid = req.getParameter("coffeeBoardUatid");
			System.out.println("CoffeeController deleteBom bcode->"+bcode);
			System.out.println("CoffeeController deleteBom login ucode->"+ui.getUcode());
			if(delBoardUatid.equals(ui.getUatid())) {	//지울 보드의 uatid가 로그인한 회원의 uatid와 동일하면 지운다.
				bs.deleteBom(bcode);
			}
			bbcode = Integer.parseInt(bs.selectBbcodeUpdate(bcode));
			System.out.println("CoffeeController deleteBom_singleboard bbcode->"+bbcode);
		}catch (Exception e) {
			System.out.println("CoffeeController deleteBom ->"+e.getMessage());
		}
		return ui.getUatid();
	}
	
	@PostMapping(value = "/coffee/deleteBom_singleBoard")
	public String deleteBom_singleBoard(HttpServletRequest req) {
		String url = "redirect:/iron/singleBoard";
		System.out.println("CoffeeController deleteBom_singleBoard start..");
		HttpSession session = req.getSession();
		int bcode = 0;
		int bbcode = 0;
		User_Info ui = new User_Info();
		String delBoardUatid = "";
		int originValue = 0;
		try {
			ui = (User_Info)session.getAttribute("user");
			bcode = Integer.parseInt(req.getParameter("coffeeBoardBcode"));
//			System.out.println("CoffeeController deleteBom_singleBoard 1");
			delBoardUatid = req.getParameter("coffeeBoardUatid");
//			System.out.println("CoffeeController deleteBom_singleBoard 2");
			originValue = Integer.parseInt(req.getParameter("coffeeBoardOrigin"));	//singleBoard에서 최상위 글인 경우 1값을 가져온다.
			System.out.println("CoffeeController deleteBom_singleBoard bcode->"+bcode);
			System.out.println("CoffeeController deleteBom_singleBoard login ucode->"+ui.getUcode());
			if(delBoardUatid.equals(ui.getUatid())) {	//지울 보드의 uatid가 로그인한 회원의 uatid와 동일하면 지운다.
				bs.deleteBom(bcode);
				
			}
			bbcode = Integer.parseInt(bs.selectBbcodeUpdate(bcode));
			System.out.println("CoffeeController deleteBom_singleBoard bbcode->"+bbcode);
			if(originValue == 1) {
				url = "redirect:/iron/timeline";
			}else {
				url += "?bcode="+bbcode;
			}
		}catch (Exception e) {
			System.out.println("CoffeeController deleteBom_singleBoard Exception->"+e.getMessage());
		}
		return url;
	}
	
	
	
	
	@PostMapping(value = "/coffee/deleteBom_timeline")
	public String deleteBom_timeline(HttpServletRequest req, Model model) {
		String url = "redirect:/iron/timeline";
		System.out.println("CoffeeController deleteBom_timeline start..");
		deleteBom(req);
//		System.out.println(url+"?uatid="+ui.getUatid());
		return url;
	}
	
	// Interceptors
	// 회원 매니저
	// interCepter 진행 Test  --> 2번째 수행 
	@GetMapping(value = "/coffee/interceptor/censorMemberManagerPage")
	public String censorMemberManagerPageI(HttpServletRequest req, Model model) {
		String url = "/coffee/censorMemberManagerPage";
		System.out.println("CoffeeController "+url+" interceptor  start..");
		// 관리자 검증
		managerVerification(req, model, url);
		return url;
	}
	
	public void managerVerification(HttpServletRequest req, Model model, String url) {
		HttpSession session = req.getSession();
		User_info ui = null;
		int uCode = -1;
//		String search = req.getParameter("String");
//		System.out.println("CoffeeController managerVerification search->"+search);
		
		try {
			ui = (User_info)session.getAttribute("login");
			uCode = ui.getuCode();
		}catch (Exception e) {
			System.out.println("CoffeeController "+url+" Interceptor ->"+e.getMessage());
		}
		System.out.println("CoffeeController session uCode->"+uCode);
		int memCnt = uis.memConfirmManager(uCode);
		// model.addAttribute("id",id);
		model.addAttribute("memCnt",memCnt);
		model.addAttribute("url", url);
//		model.addAttribute("search", search);
		
		System.out.println("CoffeeController session memCnt->"+memCnt);
	}
	
	public void getHashtagRank(Model model) {
		// 실시간 해시태그 순위
		System.out.println("[CoffeeController] Do -> hs.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
	}
	
	public void getLoginUserInfo(HttpSession session, Model model, String returnPage){
		User_info ui = null;
		User_Info user = null;
		try {
			ui = (User_info)session.getAttribute("login");
//			System.out.println("CoffeeController " +returnPage+ " user.uNickname->"+user.getuNickname());
//			System.out.println("CoffeeController " +returnPage+ " user.uEmail->"+user.getuEmail());
			user = us.getLoginUserInfo(ui.getuCode());
			model.addAttribute("user", user);
		} catch (Exception e) {
			System.out.println("CoffeeController " +returnPage+ " ->"+e.getMessage());
		}
	}
	
	// Interceptors
	 // interCepter 진행 Test
	@GetMapping(value = "/coffee/censorMemberManagerPage")
	public String censorMemberManagerPage(HttpServletRequest req, Model model) {
		String returnPage = "/coffee/censorMemberManagerPage";
		System.out.println("CoffeeController " +returnPage+ " start..");
		// 관리자 검증
		HttpSession session = req.getSession();
		String manager = (String) session.getAttribute("manager");
		if (manager == null) manager = "9";
		System.out.println(returnPage+" manager->"+ manager );
		getLoginUserInfo(session, model, returnPage);
		// 관리자면 
		if (manager == "0") {
			List<CoffeeUser_info> list = uis.user_infoSensorList();
//			System.out.println("list.get(0).getUcode()->"+list.get(0).getUcode());
//			System.out.println("list.get(0).getUnickname()->"+list.get(0).getUnickname());
//			System.out.println("list.get(1).getUcode()->"+list.get(1).getUcode());
//			System.out.println("list.get(1).getUnickname()->"+list.get(1).getUnickname());
//			System.out.println("list.get(2).getUcode()->"+list.get(2).getUcode());
//			System.out.println("list.get(2).getUnickname()->"+list.get(2).getUnickname());
			model.addAttribute("user_infoList", list);
			// 실시간 해시태그 순위
			getHashtagRank(model);
			
		} else {
			// returnPage = "/coffee/kkk";
			returnPage = "redirect:/bro/index";
			
		}

		return returnPage;
	}
	
	@GetMapping(value = "/coffee/censorMemberManagerSearch")
	public String censorMemberManagerSearch(HttpServletRequest req, Model model) {
		String returnPage = "/coffee/censorMemberManagerSearch";
		System.out.println("CoffeeController " +returnPage+ " start..");
		// 관리자 검증
		HttpSession session = req.getSession();
		String manager = (String) session.getAttribute("manager");
		String search = req.getParameter("search");
		if (manager == null) manager = "9";
		System.out.println(returnPage+" manager->"+ manager );
		getLoginUserInfo(session, model, returnPage);

		// 관리자면 
		if (manager == "0") {
			List<CoffeeUser_info> list = uis.user_infoSensorList(search);
//			System.out.println("list.get(0).getUcode()->"+list.get(0).getUcode());
//			System.out.println("list.get(0).getUnickname()->"+list.get(0).getUnickname());
//			System.out.println("list.get(1).getUcode()->"+list.get(1).getUcode());
//			System.out.println("list.get(1).getUnickname()->"+list.get(1).getUnickname());
//			System.out.println("list.get(2).getUcode()->"+list.get(2).getUcode());
//			System.out.println("list.get(2).getUnickname()->"+list.get(2).getUnickname());
			model.addAttribute("user_infoList", list);
			model.addAttribute("search", search);
			// 실시간 해시태그 순위
			getHashtagRank(model);
		} else {
			// returnPage = "/coffee/kkk";
			returnPage = "redirect:/bro/index";
			
		}

		return returnPage;
	}
	
//	// Interceptors
//	 // interCepter 진행 Test
//	@GetMapping(value = "/coffee/kkk")
//	public String censorMemberKKKPage(HttpServletRequest req, Model model) {
//		System.out.println("CoffeeController censorMemberKKKPage start..");
//		String returnPage = "coffee/kkk";
//	
//		return returnPage;
//	}

	// Ajax  List Test String
	@GetMapping(value = "/coffee/coffeeUpdateUstate", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String coffeeUpdateUstate(int ucode, int updateValue) {
		System.out.println("Ajax  coffeeUpdateUstate  Start");
		System.out.println("Ajax  coffeeUpdateUstate updateValue->"+updateValue);
		System.out.println("Ajax  coffeeUpdateUstate ucode->"+ucode);
		
		// Business Logic 구현
		String result = "";
		result += uis.updateUstate(ucode, updateValue);
		return result;
		
	}

//		// Ajax  List Test JSON
//	@GetMapping(value = "/coffee/coffeeUpdateUstate3")
//	@ResponseBody
//	public User_info coffeeUpdateUstate3(int ucode, int updateValue) {
//		System.out.println("Ajax  coffeeUpdateUstate  Start");
//		System.out.println("Ajax  coffeeUpdateUstate updateValue->"+updateValue);
//		System.out.println("Ajax  coffeeUpdateUstate ucode->"+ucode);
//		
//		// Business Logic 구현
//		User_info ui = null;
//		// Return 받는 값이 객체라는 전제하에 
//		// result = uis.updateUstate(ucode, updateValue);
//		
//		
//		return ui;
//		
//	}
	
	// Interceptors
	// interCepter 진행 Test  --> 2번째 수행 
	@GetMapping(value = "/coffee/interceptor/restoreMemberManagerPage")
	public String restoreMemberManagerPageI(HttpServletRequest req, Model model) {
		String url = "/coffee/restoreMemberManagerPage";
		System.out.println("CoffeeController "+url+" interceptor  start..");
		// 관리자 검증
		managerVerification(req, model, url);
		return url;
	}
	
	@GetMapping(value = "/coffee/restoreMemberManagerPage")
	public String restoreMemberManagerPage(HttpServletRequest req, Model model) {
		String returnPage = "/coffee/restoreMemberManagerPage";
		System.out.println("CoffeeController " +returnPage+ " start..");
		// 관리자 검증
		HttpSession session = req.getSession();
		String manager = (String) session.getAttribute("manager");
		if (manager == null) manager = "9";
		System.out.println(returnPage+" manager->"+ manager );
		getLoginUserInfo(session, model, returnPage);

		if(manager == "0") {
			List<CoffeeUser_info> list = uis.user_infoRestoreList();
			model.addAttribute("user_infoList", list);
			// 실시간 해시태그 순위
			getHashtagRank(model);
		}else {
			returnPage = "redirect:/bro/index";
		}
		return returnPage;
	}
	
	@GetMapping(value = "/coffee/restoreMemberManagerSearch")
	public String restoreMemberManagerSearch(HttpServletRequest req, Model model) {
		String returnPage = "/coffee/restoreMemberManagerSearch";
		System.out.println("CoffeeController " +returnPage+ " start..");
		// 관리자 검증
		HttpSession session = req.getSession();
		String manager = (String) session.getAttribute("manager");
		String search = req.getParameter("search");
		if (manager == null) manager = "9";
		System.out.println(returnPage+" manager->"+ manager );
		getLoginUserInfo(session, model, returnPage);

		if(manager == "0") {
			List<CoffeeUser_info> list = uis.user_infoRestoreList(search);
			model.addAttribute("user_infoList", list);
			model.addAttribute("search", search);
			// 실시간 해시태그 순위
			getHashtagRank(model);
		}else {
			returnPage = "redirect:/bro/index";
		}
		return returnPage;
	}
	
	// Interceptors
	// interCepter 진행 Test  --> 2번째 수행 
	@GetMapping(value = "/coffee/interceptor/accusationMemberManagerPage")
	public String accusationMemberManagerPageI(HttpServletRequest req, Model model) {
		String url = "/coffee/accusationMemberManagerPage";
		System.out.println("CoffeeController "+url+" interceptor  start..");
		// 관리자 검증
		managerVerification(req, model, url);
		return url;
	}
	@GetMapping(value = "/coffee/accusationMemberManagerPage")
	public String accusationMemberManagerPage(HttpServletRequest req, Model model) {
		System.out.println("CoffeeController accusationMemberManagerPage start..");
		// 관리자 검증
		HttpSession session = req.getSession();
		String manager = (String) session.getAttribute("manager");
		String returnPage = "";
		if (manager == null) manager = "9";
		System.out.println(returnPage+" manager->"+ manager );
		User_info ui = (User_info)session.getAttribute("login");
		getLoginUserInfo(session, model, returnPage);

		if(manager == "0") {
			List<CoffeeUser_info> list = uis.user_infoAccusationList();
			model.addAttribute("user_infoList", list);
			returnPage = "/coffee/accusationMemberManagerPage";
			// 실시간 해시태그 순위
			getHashtagRank(model);
		}else {
			returnPage = "redirect:/bro/index";
		}
		return returnPage;
	}
	
	
	@GetMapping(value = "/coffee/accusationMemberManagerSearch")
	public String accusationMemberManagerSearch(HttpServletRequest req, Model model) {
		String returnPage = "/coffee/accusationMemberManagerSearch";
		System.out.println("CoffeeController " +returnPage+ " start..");
		// 관리자 검증
		HttpSession session = req.getSession();
		String manager = (String) session.getAttribute("manager");
		String search = req.getParameter("search");
		System.out.println(returnPage+" manager->"+ manager );
		getLoginUserInfo(session, model, returnPage);

		if (manager == null) manager = "9";
		System.out.println("/coffee/accusationMemberManagerSearch manager->"+ manager );
		if(manager == "0") {
			List<CoffeeUser_info> list = uis.user_infoAccusationList(search);
			model.addAttribute("user_infoList", list);
			model.addAttribute("search", search);
			// 실시간 해시태그 순위
			getHashtagRank(model);
		}else {
			returnPage = "redirect:/bro/index";
		}
		return returnPage;
	}
	
	// 봄 매니저
	
	// Interceptors
	// interCepter 진행 Test  --> 2번째 수행 
	@GetMapping(value = "/coffee/interceptor/censorBomManagerPage")
	public String censorBomManagerPageI(HttpServletRequest req, Model model) {
		String url = "/coffee/censorBomManagerPage";
		System.out.println("CoffeeController "+url+" interceptor  start..");
		// 관리자 검증
		managerVerification(req, model, url);
		return url;
	}
	@GetMapping(value = "/coffee/censorBomManagerPage")
	public String censorBomManagerPage(HttpServletRequest req, Model model) {
		String returnPage = "/coffee/censorBomManagerPage";
		System.out.println("CoffeeController " +returnPage+ " start..");
		// 관리자 검증
		HttpSession session = req.getSession();
		String manager = (String) session.getAttribute("manager");
		if (manager == null) manager = "9";
		System.out.println(returnPage+" manager->"+ manager );
		getLoginUserInfo(session, model, returnPage);

		if(manager == "0") {
			List<BoardUser_info> list = bs.sensorList();
			for (int i=0; i<list.size(); i++) {
				if(list.get(i).getBattach()!= null) {
					list.get(i).setBattachType(list.get(i).getBattach().substring(0, 5));
					list.get(i).setBattachSrc(list.get(i).getBattach().substring(6));
				}
			}
//			System.out.println("CoffeeController " +returnPage+ list.get(1).getBattach());
//			System.out.println("CoffeeController " +returnPage+ list.get(1).getBattachSrc());
			model.addAttribute("boardUser_infoList", list);
			// 실시간 해시태그 순위
			getHashtagRank(model);
			
		}else {
			returnPage = "redirect:/bro/index";
		}
		return returnPage;
	}
	@GetMapping(value = "/coffee/censorBomManagerSearch")
	public String censorBomManagerSearch(HttpServletRequest req, Model model) {
		String returnPage = "/coffee/censorBomManagerSearch";
		System.out.println("CoffeeController " +returnPage+ " start..");
		// 관리자 검증
		HttpSession session = req.getSession();
		String manager = (String) session.getAttribute("manager");
		String search = req.getParameter("search");
		if (manager == null) manager = "9";
		System.out.println(returnPage+" manager->"+ manager );
		getLoginUserInfo(session, model, returnPage);

		if(manager == "0") {
			List<BoardUser_info> list = bs.sensorList(search);
			for (int i=0; i<list.size(); i++) {
				if(list.get(i).getBattach()!= null) {
					list.get(i).setBattachType(list.get(i).getBattach().substring(0, 5));
					list.get(i).setBattachSrc(list.get(i).getBattach().substring(6));
				}
			}
//			System.out.println("CoffeeController " +returnPage+ list.get(1).getBattach());
//			System.out.println("CoffeeController " +returnPage+ list.get(1).getBattachSrc());
			model.addAttribute("boardUser_infoList", list);
			model.addAttribute("search", search);
			// 실시간 해시태그 순위
			getHashtagRank(model);

		}else {
			returnPage = "redirect:/bro/index";
		}
		return returnPage;
	}

		// Ajax  List Test String
	@GetMapping(value = "/coffee/coffeeUpdateBstate", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String coffeeUpdateBstate(int bcode, int updateValue) {
		System.out.println("Ajax  coffeeUpdateBstate  Start");
		System.out.println("Ajax  coffeeUpdateBstate updateValue->"+updateValue);
		System.out.println("Ajax  coffeeUpdateBstate bcode->"+bcode);
		
		// Business Logic 구현
		String result = "";
		result += bs.updateBstate(bcode, updateValue);
		return result;
		
	}
	
	// Interceptors
	// interCepter 진행 Test  --> 2번째 수행 
	@GetMapping(value = "/coffee/interceptor/restoreBomManagerPage")
	public String restoreBomManagerPageI(HttpServletRequest req, Model model) {
		String url = "/coffee/restoreBomManagerPage";
		System.out.println("CoffeeController "+url+" interceptor  start..");
		// 관리자 검증
		managerVerification(req, model, url);
		return url;
	}
	@GetMapping(value = "/coffee/restoreBomManagerPage")
	public String restoreBomManagerPage(HttpServletRequest req, Model model) {
		String returnPage = "/coffee/restoreBomManagerPage";
		System.out.println("CoffeeController " +returnPage+ " start..");
		// 관리자 검증
		HttpSession session = req.getSession();
		String manager = (String) session.getAttribute("manager");
		if (manager == null) manager = "9";
		System.out.println(returnPage+" manager->"+ manager );
		getLoginUserInfo(session, model, returnPage);

		if(manager == "0") {
			List<BoardUser_info> list = bs.restoreList();
			for (int i=0; i<list.size(); i++) {
				if(list.get(i).getBattach()!= null) {
					list.get(i).setBattachType(list.get(i).getBattach().substring(0, 5));
					list.get(i).setBattachSrc(list.get(i).getBattach().substring(6));
				}
			}
			model.addAttribute("boardUser_infoList", list);
			// 실시간 해시태그 순위
			getHashtagRank(model);
		}else {
			returnPage = "redirect:/bro/index";
		}
		return returnPage;
	}
	
	@GetMapping(value = "/coffee/restoreBomManagerSearch")
	public String restoreBomManagerSearch(HttpServletRequest req, Model model) {
		String returnPage = "/coffee/restoreBomManagerSearch";
		System.out.println("CoffeeController " +returnPage+ " start..");
		// 관리자 검증
		HttpSession session = req.getSession();
		String manager = (String) session.getAttribute("manager");
		String search = req.getParameter("search");
		if (manager == null) manager = "9";
		System.out.println(returnPage+" manager->"+ manager );
		getLoginUserInfo(session, model, returnPage);

		if(manager == "0") {
			List<BoardUser_info> list = bs.restoreList(search);
			for (int i=0; i<list.size(); i++) {
				if(list.get(i).getBattach()!= null) {
					list.get(i).setBattachType(list.get(i).getBattach().substring(0, 5));
					list.get(i).setBattachSrc(list.get(i).getBattach().substring(6));
				}
			}
			model.addAttribute("boardUser_infoList", list);
			model.addAttribute("search", search);
			// 실시간 해시태그 순위
			getHashtagRank(model);
		}else {
			returnPage = "redirect:/bro/index";
		}
		return returnPage;
	}
	
	
	// Interceptors
	// interCepter 진행 Test  --> 2번째 수행 
	@GetMapping(value = "/coffee/interceptor/accusationBomManagerPage")
	public String accusationBomManagerPageI(HttpServletRequest req, Model model) {
		String url = "/coffee/accusationBomManagerPage";
		System.out.println("CoffeeController "+url+" interceptor  start..");
		// 관리자 검증
		managerVerification(req, model, url);
		return url;
	}
	@GetMapping(value = "/coffee/accusationBomManagerPage")
	public String accusationBomManagerPage(HttpServletRequest req, Model model) {
		String returnPage = "/coffee/accusationBomManagerPage";
		System.out.println("CoffeeController " +returnPage+ " start..");
		// 관리자 검증
		HttpSession session = req.getSession();
		String manager = (String) session.getAttribute("manager");
		if (manager == null) manager = "9";
		System.out.println(returnPage+" manager->"+ manager );
		getLoginUserInfo(session, model, returnPage);

		if(manager == "0") {
			List<BoardUser_info> list = bs.accusationList();
			for (int i=0; i<list.size(); i++) {
				if(list.get(i).getBattach()!= null) {
					list.get(i).setBattachType(list.get(i).getBattach().substring(0, 5));
					list.get(i).setBattachSrc(list.get(i).getBattach().substring(6));
				}
			}
//			System.out.println(list.get(0).getBcode());
			model.addAttribute("boardUser_infoList", list);
			// 실시간 해시태그 순위
			getHashtagRank(model);
		}else {
			returnPage = "redirect:/bro/index";
		}
		return returnPage;
	}
	
	@GetMapping(value = "/coffee/accusationBomManagerSearch")
	public String accusationBomManagerSearch(HttpServletRequest req, Model model) {
		String returnPage = "/coffee/accusationBomManagerSearch";
		System.out.println("CoffeeController " +returnPage+ " start..");
		// 관리자 검증
		HttpSession session = req.getSession();
		String manager = (String) session.getAttribute("manager");
		String search = req.getParameter("search");
		if (manager == null) manager = "9";
		System.out.println(returnPage+" manager->"+ manager );
		getLoginUserInfo(session, model, returnPage);

		if(manager == "0") {
			List<BoardUser_info> list = bs.accusationList(search);
			for (int i=0; i<list.size(); i++) {
				if(list.get(i).getBattach()!= null) {
					list.get(i).setBattachType(list.get(i).getBattach().substring(0, 5));
					list.get(i).setBattachSrc(list.get(i).getBattach().substring(6));
				}
			}
//				System.out.println(list.get(0).getBcode());
			model.addAttribute("boardUser_infoList", list);
			model.addAttribute("search", search);
			// 실시간 해시태그 순위
			getHashtagRank(model);
		}else {
			returnPage = "redirect:/bro/index";
		}
		return returnPage;
	}
	
	
	//신고 매니저
	
	// Interceptors
	// interCepter 진행 Test  --> 2번째 수행 
//	@GetMapping(value = "/coffee/interceptor/censorAccusationManagerPage")
//	public String censorAccusationManagerPageI(HttpServletRequest req, Model model) {
//		String url = "/coffee/censorAccusationManagerPage";
//		System.out.println("CoffeeController "+url+" interceptor  start..");
//		// 관리자 검증
//		managerVerification(req, model, url);
//		return url;
//	}
//	
//	
//	@GetMapping(value = "/coffee/censorAccusationManagerPage")
//	public String censorAccusationManagerPage(HttpServletRequest req, Model model) {
//		String returnPage = "/coffee/censorAccusationManagerPage";
//		System.out.println("CoffeeController " +returnPage+ " start..");
//		// 관리자 검증
//		HttpSession session = req.getSession();
//		String manager = (String) session.getAttribute("manager");
//		if (manager == null) manager = "9";
//		System.out.println(returnPage+" manager->"+ manager );
//		getLoginUserInfo(session, model, returnPage);
//
//		if(manager == "0") {
//			List<ReportUser_infoBoard> list = rs.accusationList();
//			for (int i=0; i<list.size(); i++) {
//				if(list.get(i).getBattach()!= null) {
//					list.get(i).setBattachType(list.get(i).getBattach().substring(0, 5));
//					list.get(i).setBattachSrc(list.get(i).getBattach().substring(6));
//				}
//			}
////			System.out.println("list.get(0).getUnickname_1()->"+list.get(0).getUnickname_1());
////			System.out.println("list.get(0).getUnickname()->"+list.get(0).getUnickname());
//			model.addAttribute("ReportUser_infoBoardList", list);
//			// 실시간 해시태그 순위
//			getHashtagRank(model);
//			
//		}else {
//			returnPage = "redirect:/bro/index";
//		}
//		return returnPage;
//	}
//	
//		
//	@GetMapping(value = "/coffee/censorAccusationManagerSearch")
//	public String censorAccusationManagerSearch(HttpServletRequest req, Model model) {
//		String returnPage = "/coffee/censorAccusationManagerSearch";
//		System.out.println("CoffeeController " +returnPage+ " start..");
//		// 관리자 검증
//		HttpSession session = req.getSession();
//		String manager = (String) session.getAttribute("manager");
//		String search = req.getParameter("search");
//		if (manager == null) manager = "9";
//		System.out.println("CoffeeController " +returnPage+ "search-> "+search);
//		System.out.println(returnPage+" manager->"+ manager );
//		getLoginUserInfo(session, model, returnPage);
//
//		if(manager == "0") {
//			List<ReportUser_infoBoard> list = rs.accusationList(search);
//			for (int i=0; i<list.size(); i++) {
//				if(list.get(i).getBattach()!= null) {
//					list.get(i).setBattachType(list.get(i).getBattach().substring(0, 5));
//					list.get(i).setBattachSrc(list.get(i).getBattach().substring(6));
//				}
//			}
////				System.out.println("list.get(0).getUnickname_1()->"+list.get(0).getUnickname_1());
////				System.out.println("list.get(0).getUnickname()->"+list.get(0).getUnickname());
//			model.addAttribute("ReportUser_infoBoardList", list);
//			model.addAttribute("search", search);
//			// 실시간 해시태그 순위
//			getHashtagRank(model);
//		}else {
//			returnPage = "redirect:/bro/index";
//		}
//		return returnPage;
//	}
//	
//	
//	// Ajax  List Test String
//	@GetMapping(value = "/coffee/coffeeUpdateRaction", produces = "application/text;charset=UTF-8")
//	@ResponseBody
//	public String coffeeUpdateRaction(int rcode, int updateValue) {
//		System.out.println("Ajax  coffeeUpdateRaction  Start");
//		System.out.println("Ajax  coffeeUpdateRaction updateValue->"+updateValue);
//		System.out.println("Ajax  coffeeUpdateRaction rcode->"+rcode);
//		
//		// Business Logic 구현
//		String result = "";
//		result += rs.updateRaction(rcode, updateValue);
//		return result;
//		
//	}
//	// Interceptors
//	// interCepter 진행 Test  --> 2번째 수행 
//	@GetMapping(value = "/coffee/interceptor/uncensoredAccusationManagerPage")
//	public String uncensoredAccusationManagerPageI(HttpServletRequest req, Model model) {
//		String url = "/coffee/uncensoredAccusationManagerPage";
//		System.out.println("CoffeeController "+url+" interceptor  start..");
//		// 관리자 검증
//		managerVerification(req, model, url);
//		return url;
//	}
//	
//	@GetMapping(value = "/coffee/uncensoredAccusationManagerPage")
//	public String uncensoredAccusationManagerPage(HttpServletRequest req, Model model) {
//		String returnPage = "/coffee/uncensoredAccusationManagerPage";
//		System.out.println("CoffeeController " +returnPage+ " start..");
//		// 관리자 검증
//		HttpSession session = req.getSession();
//		String manager = (String) session.getAttribute("manager");
//		if (manager == null) manager = "9";
//		System.out.println(returnPage+" manager->"+ manager );
//		getLoginUserInfo(session, model, returnPage);
//
//		if(manager == "0") {
//			List<ReportUser_infoBoard> list = rs.uncensoredList();
//			for (int i=0; i<list.size(); i++) {
//				if(list.get(i).getBattach()!= null) {
//					list.get(i).setBattachType(list.get(i).getBattach().substring(0, 5));
//					list.get(i).setBattachSrc(list.get(i).getBattach().substring(6));
//				}
//			}
////			System.out.println("list.get(0).getUnickname_1()->"+list.get(0).getUnickname_1());
////			System.out.println("list.get(0).getUnickname()->"+list.get(0).getUnickname());
//			model.addAttribute("ReportUser_infoBoardList", list);
//			// 실시간 해시태그 순위
//			getHashtagRank(model);
//		}else {
//			returnPage = "redirect:/bro/index";
//		}
//		return returnPage;
//	}
//	@GetMapping(value = "/coffee/uncensoredAccusationManagerSearch")
//	public String uncensoredAccusationManagerSearch(HttpServletRequest req, Model model) {
//		String returnPage = "/coffee/uncensoredAccusationManagerSearch";
//		System.out.println("CoffeeController " +returnPage+ " start..");
//		// 관리자 검증
//		HttpSession session = req.getSession();
//		String manager = (String) session.getAttribute("manager");
//		String search = req.getParameter("search");
//		if (manager == null) manager = "9";
//		System.out.println("CoffeeController " +returnPage+ "search-> "+search);
//		System.out.println(returnPage+" manager->"+ manager );
//		getLoginUserInfo(session, model, returnPage);
//
//		if(manager == "0") {
//			List<ReportUser_infoBoard> list = rs.uncensoredList(search);
//			for (int i=0; i<list.size(); i++) {
//				if(list.get(i).getBattach()!= null) {
//					list.get(i).setBattachType(list.get(i).getBattach().substring(0, 5));
//					list.get(i).setBattachSrc(list.get(i).getBattach().substring(6));
//				}
//			}
//			model.addAttribute("ReportUser_infoBoardList", list);
//			model.addAttribute("search", search);
//			// 실시간 해시태그 순위
//			getHashtagRank(model);
//		}else {
//			returnPage = "redirect:/bro/index";
//		}
//		return returnPage;
//	}
	

}
