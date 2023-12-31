package fong.service.impl;

import common.util.SendEmailLinkTextUtil;
import fong.dao.ComApplyRecordDao;
import fong.dao.JobDao;
import fong.dao.MemberDao;
import fong.dao.impl.ComApplyRecordDaoImpl;
import fong.dao.impl.JobDaoImpl;
import fong.dao.impl.MemberDaoImpl;
import fong.service.ComApplyRecordService;
import fong.vo.CompanyMemberShowVo;
import fong.vo.InterviewTimeShowVo;
import fong.vo.MemberVo;

public class ComApplyRecordServiceImpl implements ComApplyRecordService {
	private ComApplyRecordDao comApplyRecordDao = new ComApplyRecordDaoImpl();
	private MemberDao memberDao = new MemberDaoImpl();
	private JobDao jobDao = new JobDaoImpl();

	private String emailTo;
	private String emailSubject;
	private String messageText;

	@Override
	public boolean InterviewInvite(Integer memId, CompanyMemberShowVo company, Integer jobNo, String contextPath,
			InterviewTimeShowVo interviewTime1, InterviewTimeShowVo interviewTime2,
			InterviewTimeShowVo interviewTime3) {
		// 加入資料庫
		comApplyRecordDao.addInterviewInvite(memId, company, jobNo);

		// 寄"有連結"的信給該人才
		emailTo = interviewTime1.getMember().getMemEmail();
		emailSubject = "【Seeker's Pool】 您有一則面試邀約";
		String name = interviewTime1.getMember().getMemName();
		String jobName = jobDao.getJobNameByJobNo(jobNo);

		// ===========有表格的信件內容===========================
		messageText = name + " 您好， <br> 您有來自 " + company.getComName() + " 公司 " + jobName
				+ "的面試邀約，請確認您可以面試的一個時段，點擊該時段連結即可確認，請於3天內回覆，謝謝! <br> <table border='1'><tr><th></th> <th>可選時段</th></tr> <tr><td>時段一</td><td>  <a href=\'http://localhost:8080"
				+ contextPath + "/front-end/member/member/checkinterviewtime.html?checktime="
				+ interviewTime1.getCheckTimeKey() + "'>" + interviewTime1.getDateTime() + "</a>  </td></tr>";

		if (interviewTime2.getDateTime() != null) {
			messageText += "<tr><td>時段二</td><td> <a href=\'http://localhost:8080" + contextPath
					+ "/front-end/member/member/checkinterviewtime.html?checktime=" + interviewTime2.getCheckTimeKey()
					+ "'>" + interviewTime2.getDateTime() + "</a>  </td></tr>";
		}

		if (interviewTime3.getDateTime() != null) {
			messageText += "<tr><td>時段三</td><td> <a href=\'http://localhost:8080" + contextPath
					+ "/front-end/member/member/checkinterviewtime.html?checktime=" + interviewTime3.getCheckTimeKey()
					+ "'>" + interviewTime3.getDateTime() + "</a>  </td></tr>";
		}

		messageText += "</table>";

		// 寄出郵件
		boolean flag = SendEmailLinkTextUtil.sendMail(emailTo, emailSubject, messageText);
		return flag;

	}

	@Override
	public MemberVo getMemberById(Integer memId) {
		return memberDao.getMemberById(memId);
	}

}
