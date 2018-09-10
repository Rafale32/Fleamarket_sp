package com.sp.product.controller;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sp.bean.Bean;


@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	//뷰단의 파일 이름 형식은 각각의 상단의 (상위 이름/하단의 메소드 벨류이름) 으로 결정됨 
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public void home(Locale locale, Model model, HttpServletRequest request) {
		//컨트롤러에서 빈 파일에 필요한 객체 담은후 모델에 빈을 담아서 뷰에서 사용
		Bean bean = new Bean();
		
		/*		Bean requestBean = (Bean)request.getAttribute("requestBean");
		Bean bean = null;
		
		if(requestBean == null){
			System.out.println("빈이 생성됨");
			requestBean = new Bean();
			bean = requestBean;
			request.setAttribute("requestBean", requestBean);
		}else{
			bean = requestBean;
		}*/
		
		
		
		model.addAttribute("bean", bean );
		
	}

}
