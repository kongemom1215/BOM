package com.spring.bom.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.bom.model.yeah.YeahBoard;
import com.spring.bom.model.iron.User_Info;
import com.spring.bom.model.yeah.UserBookmarkBoard;
import com.spring.bom.service.yeah.BookmarkService;

@Controller
public class Yeah_Controller {

	@Autowired
	private BookmarkService bms;

	@RequestMapping(value = "yeah/bookmark")
	public String bookmark(Model model, HttpSession session) {
		User_Info user = (User_Info) session.getAttribute("user");
		model.addAttribute("user", user);

		System.out.println("Yeah_Controller yeah/bookmark 조회 Start...");
		int int_ucode = user.getUcode();
		System.out.println("Yeah_Controller yeah/bookmark int_ucode -> " + int_ucode);
		System.out.println("Yeah_Controller bookmark Start...");
		System.out.println("Yeah_Controller bookmark ucode->" + int_ucode);

		List<UserBookmarkBoard> ubmBoardList = bms.ubmBoardList(int_ucode);

		System.out.println("Yeah_Controller ubmBoardList.size()" + ubmBoardList.size());
		model.addAttribute("ucode", int_ucode);
		model.addAttribute("ubmBoardList", ubmBoardList);

		for (int i = 0; i < ubmBoardList.size(); i++) {
			// ubmBoardList.set(i,
			// ubmBoardList.get(i)).setBattachSrc(ubmBoardList.get(i).getBattach().substring(6));
			if (ubmBoardList.get(i).getBattach() != null) {
				ubmBoardList.get(i).setBattachSrc(ubmBoardList.get(i).getBattach().substring(6));
				ubmBoardList.get(i).setBattachType(ubmBoardList.get(i).getBattach().substring(0, 5));
				System.out.println("setBattachSrc -> " + ubmBoardList.get(i).getBattachSrc());
				System.out.println("setBattachType -> " + ubmBoardList.get(i).getBattachType());
			}
		}

		return "yeah/bookmark";
	}

	@RequestMapping(value = "/yeah/delete", method = RequestMethod.GET)
	public String delete(@RequestParam("ucode") String ucode, @RequestParam("bcode") String bcode, Model model) {
		YeahBoard bd = new YeahBoard();
		bd.setUcode(Integer.parseInt(ucode));
		bd.setBcode(Integer.parseInt(bcode));
		System.out.println("bd.ucode -> " + bd.getUcode());
		System.out.println("bd.bcode -> " + bd.getBcode());
		int result = bms.delete(bd);
		System.out.println("Yeah_Controller delete result -> " + result);
		return "forward:bookmark"; // Controller간 data 전달시 활용(Model에 담아서 파라메타 이동)
	}

	@RequestMapping(value = "/yeah/deleteAll", method = RequestMethod.GET)
	public String deleteAll(@RequestParam("ucode") String ucode, Model model) {
		bms.deleteAll(ucode);

		return "yeah/bookmark";

	}

}
