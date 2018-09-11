package com.sp.payment.service;

import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.sp.board.model.BoardDAO;
import com.sp.payment.domain.DeliveryDTO_gy;
import com.sp.payment.domain.PuerchaseDTO_gy;
import com.sp.payment.model.PaymentDAO;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Inject
	private PaymentDAO dao;
	

	@Override
	public void regist() throws Exception {
		// TODO Auto-generated method stub
		dao.create();
	}

	@Override
	public List<PuerchaseDTO_gy> listPuerchase(DeliveryDTO_gy dto) throws Exception {
		
		return dao.listPuerchase(dto);
	}


}
