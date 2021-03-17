package com.spring.bom.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.bom.model.god.JHBoard;
import com.spring.bom.model.god.JHVote;
import com.spring.bom.service.god.JHBoardService;
import com.spring.bom.service.god.JHUserService;
import com.spring.bom.service.god.JHVoteService;

@Controller
public class JuhyeController {
	@Autowired
	private JHUserService us;
	@Autowired
	private JHVoteService vs;
	@Autowired
	private JHBoardService bs;
	
	
	@RequestMapping(value = "god/getReserveNum", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String getReserveNum(int ucode, Model model) {
		System.out.println("start JuhyeController getReserveNum");
		return us.getReserveNum(ucode);
	}

	@RequestMapping(value = "god/getSaveNum", produces = "application/text;charset=UTF-8")
	@ResponseBody
	public String getSaveNum(int ucode, Model model) {
		System.out.println("start JuhyeController getSaveNum");
		return us.getSaveNum(ucode);
	}

	@RequestMapping(value = "god/getReserveList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<JHBoard> getReserveList(int ucode, Model model) {
		System.out.println("start JuhyeController getReserveList");
		System.out.println("ucode -> " + ucode);
		return us.getReserveList(ucode);
	}

	@RequestMapping(value = "god/getSaveList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<JHBoard> getSaveList(int ucode) {
		System.out.println("start JuhyeController getSaveList ucode -> " + ucode);
		List<JHBoard> list = us.getSaveList(ucode);
		System.out.println("list length -> " + list.size());
		return list;
	}

	@RequestMapping(value = "god/write", method = RequestMethod.POST)
	public String write(JHBoard board, JHVote vote, Model model, @RequestParam("attach") MultipartFile file,  HttpServletRequest request) {
		System.out.println("start JuhyeController write bcode -> "+board.getSavebcode());
		if (board.getBregdate().equals("1")) {
			String regdate = request.getParameter("year").substring(2, 4) + "/"
					+ request.getParameter("month").substring(0, 2) + "/" + request.getParameter("day").substring(0, 2)
					+ " " + request.getParameter("hours").substring(0, 2) + ":"
					+ request.getParameter("minute").substring(0, 2) + ":00";
			System.out.println("regdate -> " + regdate);
			board.setBregdate(regdate);
		}
		else
			board.setBregdate(null);
		if (request.getParameter("image").equals("1")) {
			try {
				String filename=System.currentTimeMillis()+file.getOriginalFilename();
				file.transferTo(new File(request.getServletContext().getRealPath("/image/")+filename));
				board.setBattach("image/"+filename);
			} catch (Exception e) {
				System.out.println("JuhyeController 글쓰기 이미지 오류 -> "+e.getMessage());
			}
		}
		else if(request.getParameter("video").equals("1")) {
			try {
				String filename=System.currentTimeMillis()+file.getOriginalFilename();
				file.transferTo(new File(request.getServletContext().getRealPath("/video/")+filename));
				board.setBattach("video/"+filename);
			} catch (Exception e) {
				System.out.println("JuhyeController 글쓰기 비디오 오류 -> "+e.getMessage());
			}
		}
		else
			board.setBattach(null);
		if(request.getParameter("vote").equals("1")) {
			//마감투표설정
			vote.setVendtime(vs.settingTime(vote));
			//중복투표설정
			if(request.getParameter("multipleChk")==null || request.getParameter("multipleChk").equals("")) {
				vote.setVmulti(0);
			}
			else if(request.getParameter("multipleChk").equals("on"))
				vote.setVmulti(1);
			//투표 등록
			int vcode=vs.insertVote(vote);
			System.out.println("vcode -> "+vcode);
			board.setBvotecode(vote.getVcode());
			int result=bs.insertVoteBoard(board);
		}
		else {
			//글등록하자
			int result=bs.insertBoard(board);
			System.out.println("result -> "+result);
		}
		return "redirect:/iron/timeline";
	}
	
	@RequestMapping(value="god/callSaveBoard", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public JHBoard callSaveBoard(int bcode) {
		System.out.println("start JuhyeController callSaveBoard");
		JHBoard board=bs.getBoard(bcode);
		
		return board;
	}

	@RequestMapping(value="god/deleteSaveWrite", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public int deleteSaveWrite(int[] bcodes) {
		System.out.println("start JuhyeController deleteSaveWrite");
		int result=bs.deleteSaveWrite(bcodes);
		
		return result;
	}
}
