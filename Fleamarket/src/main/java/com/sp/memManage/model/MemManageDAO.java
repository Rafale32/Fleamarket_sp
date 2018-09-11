package com.sp.memManage.model;

import com.sp.memManage.domain.MemManageDTO;

public interface MemManageDAO {
	
	//로그인 체크
	public MemManageDTO checkLogin(MemManageDTO dto)throws Exception;
	//회원 등록
	public void register(MemManageDTO memManageDTO)throws Exception;
	//회원 정보
	public MemManageDTO detail(Integer member_no)throws Exception;
	//회원 수정
	public void update(MemManageDTO memManageDTO)throws Exception;
//	//회원 삭제
//	public void delete(Integer member_no)throws Exception;
}
