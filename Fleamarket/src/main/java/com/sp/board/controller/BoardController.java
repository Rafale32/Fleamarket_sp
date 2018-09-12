package com.sp.board.controller;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sp.bean.Bean;
import com.sp.board.domain.BoardDTO;
import com.sp.board.service.BoardService;

import oracle.net.ano.Service;


@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService service;
	
//	@RequestMapping(value = "/example", method = RequestMethod.GET)
//	public void home(Locale locale, Model model) {
//		Bean bean = new Bean();
//		
//		bean.setBoard(board);
//		
//		model.addAttribute("bean", bean );
//	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardDTO board, Model model) throws Exception{
		System.out.println("register Get...........");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardDTO board, Model model) throws Exception{
		System.out.println("Regist Post..........");
		System.out.println(board.toString());
		
//		service.regist(board);
		
		model.addAttribute("result", "success");
		
		return "/board/success";
		
	}
	
	
	

}
