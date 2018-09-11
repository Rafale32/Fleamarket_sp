package com.sp.payment.model;

import java.util.List;

import com.sp.payment.domain.DeliveryDTO_gy;
import com.sp.payment.domain.PuerchaseDTO_gy;

public interface PaymentDAO {
	
	public void create()throws Exception;
	
	//구매 내역을 뽑는것
	public List<PuerchaseDTO_gy> listPuerchase(DeliveryDTO_gy dto) throws Exception;
	
}
