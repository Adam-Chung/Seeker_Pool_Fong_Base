package fong.dao;

import java.util.List;

import fong.vo.CompanyMemberShowVo;

public interface CompanyDao {

	/**
	 * 透過memId找到所有屏蔽公司
	 * @param memId
	 * @return List<CompanyMemberShowVo>
	 */
	List<CompanyMemberShowVo> findBlockComsByMemId(Integer memId);

	/**
	 * 透過公司名稱，找是否有該公司
	 * @param addCompanyName
	 * @return CompanyMemberShowVo
	 */
	CompanyMemberShowVo findComByName(String addCompanyName);

	/**
	 * 透過memId、comId確認是否已存在於屏蔽名單中
	 * @param memId
	 * @param comMemId
	 * @return 確認公司是否已存在於屏蔽名單中
	 */
	Boolean findBlockComByMemIdAndComMemId(Integer memId, Integer comMemId);

	/**
	 * 加入屏蔽名單
	 * @param memId
	 * @param comMemId
	 */
	void addBlockComBy(Integer memId, Integer comMemId);

	/**
	 * 刪除該屏蔽企業
	 * @param deleteBlockCom
	 */
	void deletBlockComByName(Integer memId, String deleteBlockCom);
}
