package com.spring.bom.controller;


import java.io.File;

import java.util.List;

import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.bom.model.bro.Interest;
import com.spring.bom.model.bro.User_info;

import com.spring.bom.service.bro.BomService;


@Controller
public class BroController {
	@Autowired
	private BomService bs;
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value="bro/email", method = RequestMethod.POST)
	public String emailPage(User_info ui, Model model, HttpServletRequest request) {
	
		return "bro/email";
	}
		
	@RequestMapping(value = "bro/login" , method=RequestMethod.POST)
	public String login(User_info ui, HttpServletRequest req, String uEmail, Model model) throws Exception{
		HttpSession session = req.getSession();
		User_info login = bs.loginCheck(ui);
		System.out.println("login data check -- login.ucode -> "+login.getuCode());
		System.out.println("login data check -- login.ustate -> "+login.getuState());
		if(login==null) {
			session.setAttribute("login", null);
			System.out.println("login off");
			System.out.println("controller uEamil-->"+uEmail);
			bs.logout(uEmail);
			model.addAttribute("uState", 1);
			return "bro/loginFail";
		}
		else if(login.getuCode()==0){
			session.setAttribute("login", login);
			System.out.println("관리자계정 로그인!!");
			return "redirect:/coffee/interceptor/censorBomManagerPage";
		}
		else {
			session.setAttribute("ucode", login.getuCode());
			System.out.println("login on");
			int a = bs.state(uEmail);
			int b = bs.loginCount(uEmail);
			System.out.println("controller ustate number-->"+ a);
			System.out.println("controller uLoginCount number-->"+ b);
			if(a == 0) {
				model.addAttribute("uState", 0);
				return "/right/UserdisabledPage";
			}else if(a == 2) {
				model.addAttribute("uState", 2);
				return "bro/loginFail";
			}
			System.out.println("callback");
			if(b >= 5) {
				model.addAttribute("uLoginCount", b);
				return "bro/loginFail";
			}
			bs.online(uEmail);
			bs.loginClear(uEmail);
			return "redirect:../iron/timeline";
		}
	}
	
	
	@RequestMapping(value = "bro/index")
     public String index() {
	 return "bro/login";
	}
	@RequestMapping(value ="bro/joinForm", method = RequestMethod.POST)
	public String joinForm(User_info ui, Model model, HttpServletRequest request, String uEmail) {
		System.out.println("controller join start");
		bs.join(ui);
		System.out.println("mailSending...");
		String tomail = uEmail;              // 받는 사람 이메일
		System.out.println(tomail);
		String setfrom = "wnddkdbom8787@gmail.com";
		String title = "mailTransport 입니다";                 // 제목
		try {
			// Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom);    // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail);       // 받는사람 이메일
			messageHelper.setSubject(title);   // 메일제목은 생략이 가능하다
			String tempPassword = (int) (Math.random() * 999999) + 1 + "";
			messageHelper.setText("인증번호입니다 : " + tempPassword); // 메일 내용
			System.out.println("인증번호입니다 : " + tempPassword);
			//DataSource dataSource = new FileDataSource("c:\\log\\jung1.jpg");
		    //messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), dataSource);
			mailSender.send(message);
			model.addAttribute("check", 1);   // 정상 전달
			model.addAttribute("tempPassword",tempPassword);
//			s.tempPw(u_id, tempPassword)  ;// db에 비밀번호를 임시비밀번호로 업데이트
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("check", 2);  // 메일 전달 실패
		}
		
		return "bro/mailResult";
	}
	@RequestMapping(value ="bro/join")
	public String join() {
		return "bro/joinForm";
	}

	 @RequestMapping(value = "/checkEmail", method= RequestMethod.GET)
     @ResponseBody	
	 public int checkEmail(@RequestParam("uEmail") String uEmail) {
		 System.out.println("checkEmail uEmail--"+uEmail);
		 int j = bs.checkEmail(uEmail);
		 return j;
	 }
	 @RequestMapping(value = "/checkAtid", method= RequestMethod.GET)
     @ResponseBody	
	 public int checkId(@RequestParam("uAtid") String uAtid) {
		 System.out.println("checkEmail uEmail--"+uAtid);
		 int j = bs.checkAtid(uAtid);
		 return j;
	 }
	 @GetMapping(value = "bro/upLoadFormStart")
	  public String upLoadFormStart(Model model) {
			System.out.println("upLoadFormStart Start");
		    return "bro/upLoadFormStart";
	  }
	 @PostMapping(value = "bro/uploadForm")
	  public String uploadForm( HttpServletRequest request, MultipartFile uImage, Model model ) 
			  throws Exception {
			String uploadPath = request.getSession().getServletContext().getRealPath("/profile_image/");
			System.out.println("uploadForm POST Start");
		    String savedName = uploadFile(uImage.getOriginalFilename(), uImage.getBytes(), uploadPath);
		    model.addAttribute("savedName", savedName);
	        return "uploadResult";
	  }
	 private String uploadFile(String originalName, byte[] fileData , String uploadPath) 
			  throws Exception {
		     UUID uid = UUID.randomUUID();
		    // requestPath = requestPath + "/resources/image";
		     System.out.println("uploadPath->"+uploadPath);
		     // Directory 생성 
		 	File fileDirectory = new File(uploadPath);
		 	if (!fileDirectory.exists()) {
		 		fileDirectory.mkdirs();
		 		System.out.println("업로드용 폴더 생성 : " + uploadPath);
		 	}
		     String savedName = uid.toString() + "_" + originalName;
		 
//		     String path1 = "C:\\spring\\springSrc39\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\sMybatis\\resources\\image";
		     File target = new File(uploadPath, savedName);
//		     File target = new File(requestPath, savedName);
		     FileCopyUtils.copy(fileData, target);   // org.springframework.util.FileCopyUtils
		     bs.fileName(savedName);
		     return savedName;		  
	  }
	 @RequestMapping(value = "bro/interestForm")
	  public String interestForm(Model model) {
			System.out.println("interestForm Start");
		    return "bro/interestForm";
	  }
	 @GetMapping(value = "bro/interestAction")
	 public String interestAction(Interest in ,HttpServletRequest request, Model model, @RequestParam(value="iCode", required = true) List<String> iCode ) {
		
		 for(String value : iCode)
			 System.out.println(value);
		 bs.interestAction(iCode);
		 
		 //interests.add(in);
		// bs.interestAction(interests);
		 return "bro/success";
	 }
	 @RequestMapping(value = "bro/email")
	 public String email() {
		 return "bro/email";
	 }
	 @RequestMapping(value = "bro/emailAu" , method = RequestMethod.POST)
	 public String emailAu(User_info ui, Model model, HttpServletRequest request, String uEmail ) {
		 System.out.println("controller findEmail start");
		 System.out.println("mailSending...");
		String tomail = uEmail;              // 받는 사람 이메일
		System.out.println(tomail);
		String setfrom = "wnddkd8787@gmail.com";
		String title = "mailTransport 입니다";          
		try {
			// Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setfrom);    // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail);       // 받는사람 이메일
			messageHelper.setSubject(title);   // 메일제목은 생략이 가능하다
			String tempPassword = (int) (Math.random() * 999999) + 1 + "";
			messageHelper.setText("인증번호입니다 : " + tempPassword); // 메일 내용
			System.out.println("인증번호입니다 : " + tempPassword);
			//DataSource dataSource = new FileDataSource("c:\\log\\jung1.jpg");
		    //messageHelper.addAttachment(MimeUtility.encodeText("airport.png", "UTF-8", "B"), dataSource);
			mailSender.send(message);
			model.addAttribute("check", 1);   // 정상 전달
			model.addAttribute("tempPassword",tempPassword);
			bs.findPw(uEmail, tempPassword)  ;// db에 비밀번호를 임시비밀번호로 업데이트
			
		} catch (Exception e) {
			System.out.println(e);
			model.addAttribute("check", 2);  // 메일 전달 실패
		}
		
		return "bro/login";
	 }

}
