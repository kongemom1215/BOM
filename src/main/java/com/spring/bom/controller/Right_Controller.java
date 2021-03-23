package com.spring.bom.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.bom.model.iron.Follow;
import com.spring.bom.model.iron.HashTag;
import com.spring.bom.model.iron.User_Info;
import com.spring.bom.model.right.RBlock;
import com.spring.bom.model.right.RFollow;
import com.spring.bom.model.right.RInterest;
import com.spring.bom.model.right.RInterestDetail;
import com.spring.bom.model.right.Statis;
import com.spring.bom.model.right.RUser_Info;
import com.spring.bom.service.right.StatisService;
import com.spring.bom.service.iron.FollowService;
import com.spring.bom.service.iron.HashTagService;
import com.spring.bom.service.right.RFollowService;
import com.spring.bom.service.right.RUser_InfoService;

@Controller
public class Right_Controller {
	private static final Logger logger = LoggerFactory.getLogger(Right_Controller.class);

	// 회원 service
	@Autowired
	private RUser_InfoService us;

	// for mail
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private HashTagService hs;
	
	// 통계 service
	@Autowired
	private StatisService ss;

	// 팔로우 service
	@Autowired
	private RFollowService fs;

	// iron 팔로우 service
	@Autowired
	private FollowService ironfs;

	// 탈퇴회원 로그인시
	@RequestMapping(value = "/right/UserdisabledPage")
	public String UserdisabledPage(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		return "/right/UserdisabledPage";
	}

	// 복구버튼 클릭시
	@RequestMapping(value = "/right/userAble")
	public String userAble(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);
		int result1 = us.updateUstate1(ucode);
		int result2 = us.updateUstate2(ucode);
		model.addAttribute("result1", result1);
		model.addAttribute("result2", result2);

		return "/right/userAble";
	}
	/*
	 * @RequestMapping(value = "/right/userAbleResult") public String
	 * userAbleResult(HttpSession session, Model model) { int ucode =
	 * Integer.parseInt(session.getAttribute("ucode").toString());
	 * System.out.println("ucode -> "+ucode); model.addAttribute("ucode", ucode);
	 * int result1 = us.updateUstate1(ucode); int result2 = us.updateUstate2(ucode);
	 * model.addAttribute("result1", result1); model.addAttribute("result2",
	 * result2);
	 * 
	 * return "/right/main"; }
	 */

	// 더보기
	@RequestMapping(value = "right/moreSee")
	public String moreSee(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);
		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/moreSee";
	}

	// 회원수정
	@RequestMapping(value = "right/updateEv")
	public String updateEv(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);
		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/updateEv";
	}
	/*
	 * 웹브라우저를 켰음. 크롬 세션이 한개. 로그인 했음. 웹은 동작이 사용자의 요청 , 서버의 응답으로 구동이되요. HttpSession
	 * jungchurl = request.getSession(); jungchurl.setAttribute("banana",세션에저장할 값 or
	 * 변수 or 객체) jsp페이지상 -> ${banana} -> 이제 컨트롤러로 가자 why ? 기능수행을해야지.
	 * 
	 * 컨트롤러 왔음 -> HttpSession jungchurl, ~~~, ~~~
	 * 
	 */

	// 회원수정 전 비밀번호 입력
	@RequestMapping(value = "right/insertpw")
	public String insertpw(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/insertpw";
	}

	// 회원수정 edit
	@RequestMapping(value = "right/userInfoEditForm")
	public String userInfoEditForm(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/userInfoEditForm";
	}

	@PostMapping(value = "right/userInfoEdit")
	public String userInfoEdit(RUser_Info ui, Model model) {
		int result1 = us.changeinsert(ui);
		System.out.println("ui.ucode " + ui.getUcode());
		int result2 = us.changeupdate(ui);
		System.out.println("ui.ucode " + ui.getUcode());
		int result3 = us.edit(ui);
		System.out.println("ui.ucode " + ui.getUcode());
		model.addAttribute("result1", result1);
		model.addAttribute("result2", result2);
		model.addAttribute("result3", result3);

		return "/right/userInfoEdit";
	}

	// id 중복 체크 컨트롤러
	@RequestMapping(value = "right/idCheck", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String idCheck(String uatid) {
		System.out.println("uatid->" + uatid);
		int cnt = us.userIdCheck(uatid);

		System.out.println("idCheck " + cnt);

		return String.valueOf(cnt);
	}

	// 2단계 인증
	@RequestMapping(value = "right/doubleSecurity")
	public String doubleSecurity(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);

		RUser_Info ui = us.detail(ucode);

		model.addAttribute("ui", ui);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/doubleSecurity";
	}

	@GetMapping(value = "right/mailTransport")
	public String mailTransport(HttpSession session, Model model, String uemail) {

		System.out.println("uemail->" + uemail);

		System.out.println("mailSending...");
		String tomail = uemail; // 받는 사람 이메일
		System.out.println(tomail);
		String setfrom = "jo7021@gmail.com";
		String title = "Bom 2단계 인증 메일 입니다."; // 제목
		int checknum = (int) (Math.random() * 999999) + 1;
		try {
			// Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다

			messageHelper.setText("인증번호 : " + checknum); // 메일 내용
			System.out.println("인증번호 : " + checknum);
			mailSender.send(message);
			model.addAttribute("check", 1); // 정상 전달

		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("check", 2); // 메일 전달 실패
		}
		String num = String.valueOf(checknum);
		model.addAttribute("num", num);
		System.out.println("num => " + num);

		return "right/mailResult";
	}

	@PostMapping(value = "right/checknum")
	public String checknum(HttpSession session, @RequestParam String num, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		// String num = request.getParameter("num");
		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		System.out.println("num->" + num);
		model.addAttribute("num", num);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "right/checknum";
	}

	// 2단계인증 DB 0->1로 업데이트
	@RequestMapping(value = "right/updateDouble")
	public String updateDouble(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);
		int result = us.updouble(ucode);
		model.addAttribute("result", result);

		return "right/updateEv";
	}

	// 비번 변경 화면
	@RequestMapping(value = "right/changePw")
	public String changePw(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/changePw";
	}

	// pw 체크 컨트롤러
	@RequestMapping(value = "right/pwCheck", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String pwCheck(HttpSession session, String upassword, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);
		System.out.println("upassword->" + upassword);
		int cnt = us.userpwCheck(upassword, ucode);

		System.out.println("upassword " + cnt);

		return String.valueOf(cnt);
	}

	// 2단계 인증 0인지 1인지 판단
	@PostMapping(value = "right/changePwPro")
	public String changePwPro(HttpSession session, Model model, @RequestParam String pwd) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		System.out.println("[Right-Controller] changePwPro ui.getuPassword() -> " + ui.getUpassword());
		System.out.println("[Right-Controller] changePwPro ui.getuPwd() -> " + ui.getPwd());
		System.out.println("[Right-Controller] changePwPro  pwd => " + pwd);
		model.addAttribute("pwd", pwd);
		model.addAttribute("ui", ui);

		return "/right/changePwPro";
	}

	// 2단계 인증 아닌 자 비번 번경
	@PostMapping(value = "right/changePw1")
	public String changePw1(HttpSession session, Model model, @RequestParam String pwd) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);

		int result1 = us.changePwinsert(ucode, pwd);
		int result2 = us.changePwupdate(ucode, pwd);
		int result3 = us.editPw(ucode, pwd);
		model.addAttribute("result1", result1);
		model.addAttribute("result2", result2);
		model.addAttribute("result3", result3);

		return "/right/userInfoEdit";
	}

	// 2단계 인증 자 비번 번경
	// 메일
	@PostMapping(value = "right/changePw2Mail")
	public String changePw2Mail(HttpSession session, Model model, @RequestParam String pwd) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);

		System.out.println("mailSending...");
		String tomail = ui.getUemail(); // 받는 사람 이메일
		System.out.println(tomail);
		String setfrom = "jo7021@gmail.com";
		String title = "Bom 비밀번호 변경 인증 메일 입니다."; // 제목
		int checknum = (int) (Math.random() * 999999) + 1;
		try {
			// Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom); // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail); // 받는사람 이메일
			messageHelper.setSubject(title); // 메일제목은 생략이 가능하다

			messageHelper.setText("인증번호 : " + checknum); // 메일 내용
			System.out.println("인증번호 : " + checknum);
			mailSender.send(message);
			model.addAttribute("check", 1); // 정상 전달

		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("check", 2); // 메일 전달 실패
		}
		String num = String.valueOf(checknum);
		model.addAttribute("num", num);
		model.addAttribute("pwd", pwd);
		System.out.println("num => " + num);
		System.out.println("pwd => " + pwd);

		return "right/pwmailResult";
	}

	@PostMapping(value = "right/pwchecknum")
	public String pwchecknum(HttpSession session, @RequestParam String num, Model model, @RequestParam String pwd) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		// String num = request.getParameter("num");
		System.out.println("num->" + num);
		model.addAttribute("num", num);
		model.addAttribute("pwd", pwd);
		System.out.println("pwd => " + pwd);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "right/pwchecknum";
	}

	@PostMapping(value = "right/changePw2")
	public String changePw2(HttpSession session, Model model, @RequestParam String pwd) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);

		int result1 = us.changePwinsert(ucode, pwd);
		int result2 = us.changePwupdate(ucode, pwd);
		int result3 = us.editPw(ucode, pwd);
		model.addAttribute("result1", result1);
		model.addAttribute("result2", result2);
		model.addAttribute("result3", result3);

		return "/right/userInfoEdit";
	}

	// 관심사 변경
	// 조회
	@RequestMapping(value = "right/likeForm")
	public String likeForm(HttpSession session, Model model, RInterest interest, RInterestDetail interestd) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		List<RInterest> list_choice = us.itdetail(ucode);
		List<RInterestDetail> list = us.select(interestd);
		model.addAttribute("interest", interest);
		model.addAttribute("list_choice", list_choice);
		model.addAttribute("list", list);
		model.addAttribute("interestd", interestd);

		System.out.println("list ->" + list);
		System.out.println("list_choice ->" + list_choice);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/likeForm";
	}

	// 관심사 변경 결과
	@PostMapping(value = "right/likeResult")
	public String likeResult(HttpSession session, RInterest interest, Model model,
			@RequestParam ArrayList<Integer> hiddenValue) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		System.out.println("hiddenValue1 : " + hiddenValue);
		System.out.println("hiddenValueindex : " + hiddenValue.get(0));
		System.out.println("hiddenValueindex : " + hiddenValue.get(1));
		System.out.println("hiddenValueindex : " + hiddenValue.get(2));
		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		// hiddenValue.put("ucode", ucode);
		interest.setUcode(ucode);
		interest.setIcode1(hiddenValue.get(0));
		interest.setIcode2(hiddenValue.get(1));
		interest.setIcode3(hiddenValue.get(2));
		System.out.println("hiddenValue2 : " + hiddenValue);
		int result1 = us.deleteinterest(ucode);
		int result2 = us.changeinsert1(interest);
		int result3 = us.changeinsert2(interest);
		int result4 = us.changeinsert3(interest);
		System.out.println("interest.icontent " + interest.getIcontent());
		model.addAttribute("result2", result2);
		model.addAttribute("result1", result1);
		model.addAttribute("result3", result3);
		model.addAttribute("result4", result4);

		return "/right/likeResult";
	}

	// 차단
	// 계정
	@RequestMapping(value = "right/block")
	public String block(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		session.setAttribute("ucode", ucode);
		List<RBlock> bListP = us.blockListP(ucode);
		model.addAttribute("bListP", bListP);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);

		return "/right/block";
	}

	// 해시태그
	@RequestMapping(value = "right/blockhash")
	public String blockhash(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		session.setAttribute("ucode", ucode);
		List<RBlock> bList = us.blockList(ucode);
		model.addAttribute("bList", bList);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/blockhash";
	}

	// 해시태그 추가
	@RequestMapping(value = "right/plusBhash")
	public String plusBhash(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		session.setAttribute("ucode", ucode);
		List<RBlock> bList = us.blockList(ucode);
		model.addAttribute("bList", bList);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/plusBhash";
	}

	@PostMapping(value = "right/plusBhashRe")
	public String plusBhashRe(HttpSession session, Model model, @RequestParam String bhashtag) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		session.setAttribute("ucode", ucode);
		int result = us.addBHash(ucode, bhashtag);
		model.addAttribute("result", result);

		return "/right/plusBhashRe";
	}

	// 단어
	@RequestMapping(value = "right/blockword")
	public String blockword(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		session.setAttribute("ucode", ucode);
		List<RBlock> bList = us.blockList(ucode);
		model.addAttribute("bList", bList);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/blockword";
	}

	// 단어 추가
	@RequestMapping(value = "right/plusBword")
	public String plusBword(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		session.setAttribute("ucode", ucode);
		List<RBlock> bList = us.blockList(ucode);
		model.addAttribute("bList", bList);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/plusBword";
	}

	@PostMapping(value = "right/plusBwordRe")
	public String plusBwordRe(HttpSession session, Model model, @RequestParam String bword) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		session.setAttribute("ucode", ucode);
		int result = us.addBWord(ucode, bword);
		model.addAttribute("result", result);

		return "/right/plusBwordRe";
	}

	// 차단 해제 - 계정
	@RequestMapping(value = "right/blockdelete")
	public String blockdelete(Model model, @RequestParam int blcode) {

		int result = us.deleteblock(blcode);
		model.addAttribute("result", result);

		return "redirect:../right/block";
	}

	// 차단 해제 - 단어
	@RequestMapping(value = "right/blockdeleteword")
	public String blockdeleteword(Model model, @RequestParam int blcode) {

		int result = us.deleteblock(blcode);
		model.addAttribute("result", result);

		return "redirect:../right/blockword";
	}

	// 차단 해제 - hash
	@RequestMapping(value = "right/blockdeletehash")
	public String blockdeletehash(Model model, @RequestParam int blcode) {
		int result = us.deleteblock(blcode);
		model.addAttribute("result", result);

		return "redirect:../right/blockhash";
	}

	@RequestMapping(value = "right/blockdeleteResult")
	public String blockdeleteResult(HttpSession session, Model model) {
		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		session.setAttribute("ucode", ucode);

		return "right/blockdeleteResult";
	}

	// 탈퇴
	@RequestMapping(value = "right/userDisabled")
	public String userDisabled(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		session.setAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/userDisabled";
	}

	@PostMapping(value = "right/userDisabledPro")
	public String userDisabledPro(RUser_Info ui, Model model) {
		int result = us.changeinsertstate(ui);
		int result1 = us.statedDis(ui);
		int result2 = us.changeInfoState(ui);
		int result3 = us.boardBpermission(ui);
		model.addAttribute("result", result);
		model.addAttribute("result1", result1);
		model.addAttribute("result2", result2);
		model.addAttribute("result3", result3);

		return "redirect:../bro/index";
	}

	// 봄 통계 Statis
	@RequestMapping(value = "right/bomStatis")
	public String bomStatis(HttpSession session, Model model) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		session.setAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		List<Statis> aList1 = ss.getSearchRank1();
		for (int i = 0; i < aList1.size(); i++)
			aList1.get(i).setRank(i + 1);

		List<Statis> aList2 = ss.getSearchRank2();
		for (int i = 0; i < aList2.size(); i++)
			aList2.get(i).setRank(i + 1);

		List<Statis> aList3 = ss.getSearchRank3();
		for (int i = 0; i < aList3.size(); i++)
			aList3.get(i).setRank(i + 1);

		List<Statis> gListm = ss.getSearchRankm();
		for (int i = 0; i < gListm.size(); i++)
			gListm.get(i).setRank(i + 1);

		List<Statis> gListw = ss.getSearchRankw();
		for (int i = 0; i < gListw.size(); i++)
			gListw.get(i).setRank(i + 1);

		model.addAttribute("aList1", aList1);
		model.addAttribute("aList2", aList2);
		model.addAttribute("aList3", aList3);
		model.addAttribute("gListm", gListm);
		model.addAttribute("gListw", gListw);

		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "/right/bomStatis";
	}

	// 팔로잉&팔로워
	@RequestMapping(value = "/right/follower")
	public String follower(HttpSession session, Model model,@RequestParam String uatid) {
		User_Info user = (User_Info)session.getAttribute("user");
		model.addAttribute("user",user);
		
		System.out.println("RightController follower Start");
		String atid = uatid;
		System.out.println("Rightuatid ->"+ uatid);
		System.out.println("Rightatid ->"+ atid);
		
		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);
		
		// session ucode 현재 로그인 되어있는 계정의 팔로우/팔로워 List & 차단 List
		List<RFollow> followerList = fs.selectFollower(ucode);
		//차단한 리스트
		List<RFollow> followerBlockList = fs.selectBlockFollower(ucode);
		List<RFollow> followingList = fs.selectFollowing(ucode);
		//차단 당한 리스트
		List<RFollow> followingBlockList = fs.selectBlockFollowing(ucode);
		
		// 프로필 uatid 계정의 팔로우/팔로워 List & 차단 List
		List<RFollow> pfollowerList = fs.selectFollower_p(uatid);
		List<RFollow> pfollowerBlockList = fs.selectBlockFollower_p(uatid);
		List<RFollow> pfollowingList = fs.selectFollowing_p(uatid);
		List<RFollow> pfollowingBlockList = fs.selectBlockFollowing_p(uatid);
		
		model.addAttribute("followerList", followerList);
		model.addAttribute("followingList", followingList);
		model.addAttribute("followerBlockList", followerBlockList);
		model.addAttribute("followingBlockList", followingBlockList);
		model.addAttribute("pfollowerList", pfollowerList);
		model.addAttribute("pfollowingList", pfollowingList);
		model.addAttribute("pfollowerBlockList", pfollowerBlockList);
		model.addAttribute("pfollowingBlockList", pfollowingBlockList);
		System.out.println("RightController follower end");
		model.addAttribute("uatid", atid);
		
		
		
		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);
		
		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "right/follower";
	}

	@RequestMapping(value = "/right/following")
	public String following(HttpSession session, Model model,@RequestParam String uatid) {
		User_Info user = (User_Info)session.getAttribute("user");
		model.addAttribute("user",user);
		
		System.out.println("RightController follower Start");
		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		// session ucode 현재 로그인 되어있는 계정의 팔로우/팔로워 List & 차단 List
		List<RFollow> followerList = fs.selectFollower(ucode);
		List<RFollow> followerBlockList = fs.selectBlockFollower(ucode);
		List<RFollow> followingList = fs.selectFollowing(ucode);
		List<RFollow> followingBlockList = fs.selectBlockFollowing(ucode);
		
		String atid = uatid;
		System.out.println("Rightuatid ->"+ uatid);
		System.out.println("Rightatid ->"+ atid);
		
		// 프로필 uatid 계정의 팔로우/팔로워 List & 차단 List
		List<RFollow> pfollowerList = fs.selectFollower_p(uatid);
		List<RFollow> pfollowerBlockList = fs.selectBlockFollower_p(uatid);
		List<RFollow> pfollowingList = fs.selectFollowing_p(uatid);
		List<RFollow> pfollowingBlockList = fs.selectBlockFollowing_p(uatid);

		model.addAttribute("followerList", followerList);
		model.addAttribute("followingList", followingList);
		model.addAttribute("followerBlockList", followerBlockList);
		model.addAttribute("followingBlockList", followingBlockList);
		model.addAttribute("pfollowerList", pfollowerList);
		model.addAttribute("pfollowingList", pfollowingList);
		model.addAttribute("pfollowerBlockList", pfollowerBlockList);
		model.addAttribute("pfollowingBlockList", pfollowingBlockList);
		System.out.println("RightController follower end");
		model.addAttribute("uatid", atid);
		
		System.out.println("RightController Following List from profile -> " + pfollowingList);
		System.out.println("RightController followingList List from session -> " + followingList);
		
		// 팔로우 추천1 나와 관심사가 겹치는 유저를 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist1 = ironfs.getSuggestFollowList1(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist1.size());
		model.addAttribute("suggestFlist1_size", suggestFlist1.size());
		model.addAttribute("suggestFlist1", suggestFlist1);

		// 팔로우 추천2 나를 팔로우하는 유저 추천
		System.out.println("[JungChurlController] Do -> fs.getSuggestFollowList()");
		List<Follow> suggestFlist2 = ironfs.getSuggestFollowList2(user.getUcode());
		System.out.println("[JungChurlController] Result : listSize is " + suggestFlist2.size());
		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);

		// 리스트 suggestFlist2 에 있는 값들을 랜덤으로 돌림
		Collections.shuffle(suggestFlist2); // 팔로우 추천 랜덤

		model.addAttribute("suggestFlist2_size", suggestFlist2.size());
		model.addAttribute("suggestFlist2", suggestFlist2);
		
		
		// 실시간 해시태그 순위
		System.out.println("[JungChurlController] Do -> hts.getHashTagRanking()");
		List<HashTag> hashtagList = hs.getHashTagRanking();
		for (int i = 0; i < hashtagList.size(); i++)
			hashtagList.get(i).setHrank(i + 1);
		model.addAttribute("tag_list", hashtagList);
		
		return "right/following";
	}

	// 팔로잉 취소
	@RequestMapping(value = "/right/followerDelete", produces = "application/text;charset=UTF-8")
	public String followerDelete(HttpSession session, Model model, @RequestParam int fopcode,@RequestParam String uatid,RedirectAttributes redirect) {
		User_Info user = (User_Info)session.getAttribute("user");
		model.addAttribute("user",user);
		
		String atid = uatid;
		System.out.println("Right Delete uatid ->"+ uatid);
		System.out.println("Right Delete atid ->"+ atid);
		redirect.addAttribute("uatid", atid);	
		
		System.out.println("RightController followerDelete Start");
		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		int result = fs.deleteFollowing(ucode, fopcode);

		model.addAttribute("result", result);
		System.out.println("followerDelete result => " + result);
		System.out.println("RightController followerDelete end");
		return "redirect:../right/follower";
	}
	// 팔로잉 취소 -> following.jsp
	@RequestMapping(value = "/right/followerDeletetofollowing", produces = "application/text;charset=UTF-8")
	public String followerDeletetofollowing(HttpSession session, Model model, @RequestParam int fopcode,@RequestParam String uatid,RedirectAttributes redirect) {
		User_Info user = (User_Info)session.getAttribute("user");
		model.addAttribute("user",user);
		
		String atid = uatid;
		System.out.println("Right Delete uatid ->"+ uatid);
		System.out.println("Right Delete atid ->"+ atid);
		redirect.addAttribute("uatid", atid);	
		
		System.out.println("RightController followerDelete Start");
		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		int result = fs.deleteFollowing(ucode, fopcode);

		model.addAttribute("result", result);
		System.out.println("followerDelete result => " + result);
		System.out.println("RightController followerDelete end");
		return "redirect:../right/following";
	}	

	// 팔로우 하는 사람 팔로잉하기
	@RequestMapping(value = "/right/addfollowing", produces = "application/text;charset=UTF-8")
	public String addfollowing(HttpSession session, Model model, @RequestParam int fopcode,@RequestParam String uatid,RedirectAttributes redirect) {
		User_Info user = (User_Info)session.getAttribute("user");
		model.addAttribute("user",user);

		String atid = uatid;
		System.out.println("Right Delete uatid ->"+ uatid);
		System.out.println("Right Delete atid ->"+ atid);
		redirect.addAttribute("uatid", atid);		
		
		System.out.println("RightController addfollowing Start");
		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		int result = fs.addfollowing(ucode, fopcode);

		model.addAttribute("result", result);
		System.out.println("addfollowing result => " + result);
		System.out.println("RightController addfollowing end");
		return "redirect:../right/follower";
	}
	// 팔로우 하는 사람 팔로잉하기 -> following.jsp
	@RequestMapping(value = "/right/addfollowingtofollowing", produces = "application/text;charset=UTF-8")
	public String addfollowingtofollowing(HttpSession session, Model model, @RequestParam int fopcode,@RequestParam String uatid,RedirectAttributes redirect) {
		User_Info user = (User_Info)session.getAttribute("user");
		model.addAttribute("user",user);

		String atid = uatid;
		System.out.println("Right Delete uatid ->"+ uatid);
		System.out.println("Right Delete atid ->"+ atid);
		redirect.addAttribute("uatid", atid);		
		
		System.out.println("RightController addfollowing Start");
		int ucode = Integer.parseInt(session.getAttribute("ucode").toString());
		model.addAttribute("ucode", ucode);
		RUser_Info ui = us.detail(ucode);
		model.addAttribute("ui", ui);

		int result = fs.addfollowing(ucode, fopcode);

		model.addAttribute("result", result);
		System.out.println("addfollowing result => " + result);
		System.out.println("RightController addfollowing end");
		
		return "redirect:../right/following";
	}

}
