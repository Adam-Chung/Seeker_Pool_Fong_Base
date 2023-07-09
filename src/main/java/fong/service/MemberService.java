package fong.service;

import fong.vo.MemberVo;

public interface MemberService {

	/**
	 * 註冊用戶
	 * @param member
	 * @return 是否註冊成功
	 */
	Boolean registerMember(MemberVo member);

	/**
	 * 用戶登入
	 * @param member
	 * @return MemberVo
	 */
	MemberVo loginMember(MemberVo member);
	

	/**
	 * 取得member
	 * @param id
	 * @return MemberVo
	 */
	MemberVo getMemberById(Integer id);

	/**
	 * 更新member訊息
	 * @param member
	 */
	void updateMember(MemberVo member);

	/**
	 * 寄信給註冊者
	 * @param member
	 * @param contextPath 
	 * @return CheckCode
	 */
	String sendCheckCode(MemberVo member, String contextPath);
}
