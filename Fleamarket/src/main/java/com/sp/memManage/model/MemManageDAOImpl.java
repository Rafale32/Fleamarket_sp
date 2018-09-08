package com.sp.memManage.model;

import javax.inject.Inject;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemManageDAOImpl implements MemManageDAO{
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "MemManageMapper";
	
	@Override
	public void create() throws Exception {
		//session.insert(namespace + ".create", vo);
	}
	
}
