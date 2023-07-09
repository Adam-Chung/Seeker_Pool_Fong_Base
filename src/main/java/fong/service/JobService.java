package fong.service;

import fong.vo.JobVo;
import fong.vo.PageBean;

public interface JobService {

	/**
	 * 透過頁數回傳資料
	 * @param memId
	 * @param currentPage
	 * @param pageSize
	 * @return page資料
	 */
	PageBean<JobVo> pageQuery(int memId, int currentPage, int pageSize);

	/**
	 * 刪除收藏文章
	 * @param arNo
	 * @param memId
	 */
	void deletColJobByMemIdAndArNo(Integer jobNo, Integer memId);

}
