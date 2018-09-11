package com.sp.safepay.model;

import com.sp.memManage.domain.MemManageDTO;
import com.sp.product.domain.ItemDTO;
import com.sp.safepay.domain.OorderDTO;

public interface SafepayDAO {
	
//	public MemManageDTO selectMember(Integer member_no)throws Exception;
//  test
	public MemManageDTO selectMember(String member_email)throws Exception;

	public ItemDTO selectItem(int item_no)throws Exception;
	
	public int selectOrderNo()throws Exception;
	
	public void insertOrder(OorderDTO oorderDTO)throws Exception;
	
}
