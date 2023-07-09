package fong.service;

import fong.vo.ArticleVo;
import fong.vo.PageBean;

public interface ArticleService {

	/**
	 * 透過頁數獲取資料
	 * @param memId
	 * @param currentPage
	 * @param pageSize
	 * @return page資料
	 */
	PageBean<ArticleVo> pageQuery(int memId, int currentPage, int pageSize);

	/**
	 * 刪除收藏文章
	 * @param arNo
	 * @param memId
	 */
	void deletColArtByMemIdAndArNo(Integer arNo, Integer memId);
}
