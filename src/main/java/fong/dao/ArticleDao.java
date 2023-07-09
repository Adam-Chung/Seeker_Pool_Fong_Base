package fong.dao;

import java.util.List;

import fong.vo.ArticleVo;

public interface ArticleDao {

	/**
	 * 獲取該會員全部收藏數量	
	 * @param memId
	 * @return 收藏數量
	 */
	int findTotalCount(int memId);

	/**
	 * 透過頁數獲取資料
	 * @param memId
	 * @param start
	 * @param pageSize
	 * @return page資料
	 */
	List<ArticleVo> findByPage(int memId, int start, int pageSize);

	/**
	 * 刪除收藏文章
	 * @param arNo
	 * @param memId
	 */
	void deletColArtByMemIdAndArNo(Integer arNo, Integer memId);

}
