package fong.dao.impl;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import common.util.HikariCPUtil;
import fong.dao.ComApplyRecordDao;
import fong.vo.CompanyMemberShowVo;

public class ComApplyRecordDaoImpl implements ComApplyRecordDao {
	private DataSource dataSource = HikariCPUtil.getDataSource();
	private JdbcTemplate template = new JdbcTemplate(dataSource);

	@Override
	public void addInterviewInvite(Integer memId, CompanyMemberShowVo company, Integer jobNo) {
		String sql = "INSERT INTO apply_record (COM_MEM_ID, mem_id, job_no, hire_status) VALUES ( ? , ? , ? ,3);";
		template.update(sql, company.getComMemId(), memId, jobNo);
	}

}
