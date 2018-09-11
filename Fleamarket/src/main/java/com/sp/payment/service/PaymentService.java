package com.sp.payment.service;

import java.util.List;

import com.sp.payment.domain.DeliveryDTO_gy;
import com.sp.payment.domain.PuerchaseDTO_gy;


public interface PaymentService {
	
	public void regist()throws Exception;
	
	//구매 내역을 뽑는것
	public List<PuerchaseDTO_gy> listPuerchase(DeliveryDTO_gy dto) throws Exception;
	
	//판매내역
	
	public List<PuerchaseDTO_gy> listSell(DeliveryDTO_gy dto) throws Exception;
	
	public void changDeliverState(DeliveryDTO_gy dto) throws Exception;
	
	public void changDeliverState2(DeliveryDTO_gy dto) throws Exception;
	
}
