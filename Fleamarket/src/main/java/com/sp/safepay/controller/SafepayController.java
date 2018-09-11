package com.sp.safepay.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sp.bean.Bean;
import com.sp.memManage.domain.MemManageDTO;
import com.sp.safepay.domain.OorderDTO;
import com.sp.safepay.service.SafepayService;


@Controller
@RequestMapping("/safepay/*")
public class SafepayController {
  
  @Inject
  private SafepayService service;
	
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public void orderGET(@RequestParam("item_no") int item_no,HttpSession session, Locale locale, Model model) throws Exception {
	  System.out.println("orderGET");
		Bean bean = new Bean();
		//test user
//		bean.setMemManageDTO(service.selectMember("user00@user.com"));
		Object obj = session.getAttribute("member");
    MemManageDTO member = (MemManageDTO) obj;
    bean.setMemManageDTO(service.selectMember(member.getMember_email()));

		// 상품번호
		bean.setItemDTO(service.selectItem(item_no));
		
    // 주문번호
		OorderDTO oorderDTO = new OorderDTO();
		oorderDTO.setOorder_no(service.selectOrderNo());
		bean.setOorderDTO(oorderDTO);

		model.addAttribute("bean", bean );
		
	}
	
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public String orderPOST(OorderDTO oorderDTO,  Locale locale, Model model, HttpServletRequest request) {
	  System.out.println("orderPOST");
//	  Bean bean = new Bean();
	  // 주문정보입력
//    oorderDTO.setOorder_no(Integer.parseInt(request.getParameter("oorder_no")));
//    oorderDTO.setOorder_date(request.getParameter("oorder_date"));
//    oorderDTO.setMember_no(Integer.parseInt(request.getParameter("member_no")));
//    oorderDTO.setItem_no(Integer.parseInt(request.getParameter("item_No")));
//	  System.out.println(oorderDTO.getItem_no());
//	  System.out.println(oorderDTO.getMember_no());
//	  System.out.println(oorderDTO.getOorder_no());
//	  System.out.println(oorderDTO.getOorder_date());
//	  oorderDTO.setMember_no(member_no);
//    service.insertOrder(oorderDTO);
	  try {

    } catch (Exception e) {
      // TODO: handle exception
    }
    

	  // 결제정보입력

	  // 배송정보입력
	  
	  // 상품 배송진행상태 수정
	  
	  // 멤버 포인트 소모 및 적립 수정
	  
	  
	  
//	  model.addAttribute("bean", bean );
	  return "redirect:/safepay/order_detail";
	}
	
	
	
	
	
	@RequestMapping(value = "/order_detail", method = RequestMethod.GET)
	public void order_detail(Locale locale, Model model) {
	  Bean bean = new Bean();
	  
	  model.addAttribute("bean", bean );
	  
	}

}
