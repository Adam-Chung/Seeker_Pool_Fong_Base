package fong.dao;

import fong.vo.MemberVo;

public interface MemberDao {
	
	/**
	 * 從會員id得到會員
	 * @param member
	 * @return MemberVo
	 */
	MemberVo getMemberById(int id);
	
	/**
	 * 一般會員註冊
	 * @param member
	 * @return 註冊是否成功
	 */
	boolean registerMember(MemberVo member);

	/**
	 * 是否存在此MemAccount
	 * @param memAccount
	 * @return 是否找到該會員帳號
	 */
	Boolean findByMemAccount(String memAccount);
	

	/**
	 * 透過帳號密碼找member
	 * @param memAccount
	 * @param memPassword
	 * @return MemberVo
	 */
	MemberVo findByAccountAndPassword(String memAccount, String memPassword);

	/**
	 * 更新會員資訊
	 * @param member
	 */
	void updateMember(MemberVo member);
}
