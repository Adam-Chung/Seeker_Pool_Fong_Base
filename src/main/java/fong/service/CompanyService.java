package fong.service;

import java.util.List;

import fong.vo.CompanyMemberShowVo;
import fong.vo.ResultInfo;

public interface CompanyService {

	/**
	 * 用memid找到他的屏蔽公司們
	 * @param memId
	 * @return 屏蔽公司們
	 */
	List<CompanyMemberShowVo> findBlockComsByMemId(Integer memId);

	/**
	 * 用公司名稱先去公司資料庫找是否存在此公司，再去加入屏蔽資料庫中
	 * @param memId 
	 * @param addCompanyName
	 * @return ResultInfo
	 */
	ResultInfo addBlockComByName(Integer memId, String addCompanyName);

	/**
	 * 透過公司名，刪除該屏蔽公司
	 * @param deleteBlockCom
	 */
	void deletBlockComByName(Integer memId, String deleteBlockCom);
}
