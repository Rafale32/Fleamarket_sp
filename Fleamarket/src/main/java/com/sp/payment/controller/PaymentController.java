package com.sp.payment.controller;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.sp.bean.Bean;
import com.sp.memManage.domain.MemManageDTO;
import com.sp.payment.domain.DeliveryDTO_gy;
import com.sp.payment.domain.PuerchaseDTO_gy;
import com.sp.payment.service.PaymentService;


@Controller
@RequestMapping("/payment/*")
public class PaymentController {
	
	
	@Inject
	private PaymentService service;
	
	//뷰단의 파일 이름 형식은 각각의 상단의 (상위 이름/하단의 메소드 벨류이름) 으로 결정됨 
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void home(Locale locale, Model model) {
		//컨트롤러에서 빈 파일에 필요한 객체 담은후 모델에 빈을 담아서 뷰에서 사용
		Bean bean = new Bean();
		
		model.addAttribute("bean", bean );
		
		System.out.println("들어왔나 확인");
		
		
	}
	
	@RequestMapping(value = "/buy_list_gy", method = RequestMethod.GET)
	public void buyList(Model model,HttpSession session) throws Exception {
		//컨트롤러에서 빈 파일에 필요한 객체 담은후 모델에 빈을 담아서 뷰에서 사용
		Bean bean = new Bean();
	
		model.addAttribute("bean", bean );
		System.out.println("옴?");  
		
		
		
		MemManageDTO dto = (MemManageDTO) session.getAttribute("member");
		System.out.println(dto.getMember_email() + " 이메일 확인");
		
		String loginEmail = dto.getMember_email();
		
		DeliveryDTO_gy dto2 = new DeliveryDTO_gy();
		dto2.setMember_email(loginEmail);
		List<PuerchaseDTO_gy> list	= service.listPuerchase(dto2);
		//쿼리로 감
		
		model.addAttribute("list", list);
		
		//return "/payment/buy_list_gy";
		
		
	}
	
	
/*	@RequestMapping(value = "/img/*", method = RequestMethod.GET)
	public void img(Model model,HttpSession session) throws Exception {
		System.out.println("정말 이쪽으로 옵니까?");
		
	}
*/
}
