package com.sp.memManage.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sp.bean.Bean;
import com.sp.memManage.domain.MemManageDTO;
import com.sp.memManage.service.MemManageService;


@Controller
@RequestMapping("/memmanage/*")
public class MemManageController {
	
	@Inject
	private MemManageService service;
	
	//뷰단의 파일 이름 형식은 각각의 상단의 (상위 이름/하단의 메소드 벨류이름) 으로 결정됨 
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(Model model) {
		//컨트롤러에서 빈 파일에 필요한 객체 담은후 모델에 빈을 담아서 뷰에서 사용
		Bean bean = new Bean();
		
		model.addAttribute("bean", bean );
	}
	
	@RequestMapping(value = "/loginpost" , method = RequestMethod.POST)
	public String loginPOST(MemManageDTO dto , Model model, HttpSession session)throws Exception{
		
		MemManageDTO memManageDTO  = service.checkLogin(dto);
		
/*		if(member != null){
			return "redirect:/maindetail/mmain";
		}else{
			return "redirect:/memmanage/login";
		}*/
		
		if(memManageDTO == null){ return "redirect:/memmanage/login";	}
		
		model.addAttribute("memManageDTO", memManageDTO);
		
		return "redirect:/maindetail/mmain";
	}
		
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(MemManageDTO memManageDTO, Model model) throws Exception {
		//컨트롤러에서 빈 파일에 필요한 객체 담은후 모델에 빈을 담아서 뷰에서 사용
		
		Bean bean = new Bean();
		
		model.addAttribute("bean", bean );
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(MemManageDTO memManageDTO, Model model) throws Exception {
		//컨트롤러에서 빈 파일에 필요한 객체 담은후 모델에 빈을 담아서 뷰에서 사용
		Bean bean = new Bean();
		
		service.registerMember(memManageDTO);
		
		model.addAttribute("bean", bean );
		
		return "redirect:/memmanage/login";
	}
	
	@RequestMapping(value ="/detail", method = RequestMethod.GET)
	public void read(@RequestParam("member_email") String member_email, Model model) throws Exception{
		
		Bean bean = new Bean();
		
		model.addAttribute(service.detailMember(member_email));
		
		model.addAttribute("bean", bean);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET) 
	public void updateGET(@RequestParam("member_email") String member_email, Model model) throws Exception{
		
		model.addAttribute(service.detailMember(member_email));
		System.out.println("확인");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updatePOST(@RequestParam("member_email") String member_email, Model model, HttpSession session) throws Exception{
		 
//		Object obj = session.getAttribute("member");
//		MemManageDTO member = (MemManageDTO)obj;
//
//		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");

		Bean bean = new Bean();
		service.updateMember(member_email);
		
		model.addAttribute("bean", bean);
		
		System.out.println("업데이트");
		//return "redirect:/memmanage/detail?member_email="+"123@123";
		return "redirect:/memmanage/detail";
	}
	
	@RequestMapping(value ="/delete")
	public void  deletePOST(@RequestParam("member_email") String member_email, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		Bean bean = new Bean();
		
		service.deleteMember(member_email);
		
		rttr.addFlashAttribute("bean", bean);
		
		session.invalidate();
		
		//return "redirect:/maindetail/mmain";
		
		//return "redirect:/memmanage/delete";
	}
	
	
}

