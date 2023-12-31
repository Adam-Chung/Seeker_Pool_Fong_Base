package fong.service.impl;

import java.util.List;

import fong.dao.ArticleDao;
import fong.dao.impl.ArticleDaoImpl;
import fong.service.ArticleService;
import fong.vo.ArticleVo;
import fong.vo.PageBean;

public class ArticleServiceImpl implements ArticleService{
	private ArticleDao articleDao = new ArticleDaoImpl();

	@Override
	public PageBean<ArticleVo> pageQuery(int memId, int currentPage, int pageSize) {
		//封裝pageBean
        PageBean<ArticleVo> pb = new PageBean<ArticleVo>();
        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        //設置總記錄數
        int totalCount = articleDao.findTotalCount(memId);
        pb.setTotalCount(totalCount);

        //設置當前頁數據集合
        int start = (currentPage - 1) * pageSize;//開始的紀錄數
        List<ArticleVo> list = articleDao.findByPage(memId, start, pageSize);
        pb.setList(list);

        //設置總頁數
        int totalPage = (totalCount % pageSize)== 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);

        return pb;
	}

	@Override
	public void deletColArtByMemIdAndArNo(Integer arNo, Integer memId) {
		articleDao.deletColArtByMemIdAndArNo(arNo,  memId);
	}
}
