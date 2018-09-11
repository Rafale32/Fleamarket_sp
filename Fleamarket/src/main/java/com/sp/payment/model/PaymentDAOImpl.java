package com.sp.payment.model;

import java.util.List;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.sp.payment.domain.DeliveryDTO_gy;
import com.sp.payment.domain.PuerchaseDTO_gy;

@Repository
public class PaymentDAOImpl implements PaymentDAO{
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "PaymentMapper";
	
	
	@Override
	public void create() throws Exception {
		//session.insert(namespace + ".create", vo);
	}
	
	// 리스트로 오는 것
	@Override
	public List<PuerchaseDTO_gy> listPuerchase(DeliveryDTO_gy dto) throws Exception {
		
		return session.selectList(namespace+".puerchaseList",dto);
	}
	
}
