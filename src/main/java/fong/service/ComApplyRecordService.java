package fong.service;

import fong.vo.CompanyMemberShowVo;
import fong.vo.InterviewTimeShowVo;
import fong.vo.MemberVo;

public interface ComApplyRecordService {

	/**
	 * 邀請面試，存入資料庫+寄信
	 * @param memId
	 * @param date1
	 * @param date2
	 * @param date3
	 * @param company
	 * @param jobId 
	 * @param contextPath 
	 * @param checktime 
	 * @param checktime3 
	 * @param checktime2 
	 * @return 是否寄信成功
	 */
	boolean InterviewInvite(Integer memId, CompanyMemberShowVo company, Integer jobNo, String contextPath, InterviewTimeShowVo interviewTime1, InterviewTimeShowVo interviewTime2, InterviewTimeShowVo interviewTime3);

	/**
	 * 透過memId獲取會員資料
	 * @param memId
	 * @return
	 */
	MemberVo getMemberById(Integer memId);
}
