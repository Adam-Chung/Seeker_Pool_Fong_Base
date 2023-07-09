package fong.service;

import fong.vo.ApplyRecordShowVo;
import fong.vo.PageBean;

public interface ApplyRecordService {

	/**
	 * 用頁數、篩選條件獲得資料
	 * @param memId
	 * @param currentPage
	 * @param pageSize
	 * @param keyWord
	 * @param filterNum
	 * @return page資料
	 */
	PageBean<ApplyRecordShowVo> pageQuery(int memId, int currentPage, int pageSize, String keyWord, int filterNum);

	/**
	 * 取消面試
	 * @param arNo
	 * @param memId
	 * @param memName 
	 */
	void cancelInterview(String comName, String jobName, Integer memId, String memName);

	/**
	 * 修改應徵紀錄 面試時間+應徵狀態
	 * @param jobId
	 * @param memId
	 * @param dateTime
	 */
	void updateInterviewTime(Integer jobId, Integer memId, String dateTime);


}
