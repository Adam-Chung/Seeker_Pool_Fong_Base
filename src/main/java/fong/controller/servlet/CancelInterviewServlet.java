package fong.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fong.service.ApplyRecordService;
import fong.service.impl.ApplyRecordServiceImpl;
import fong.vo.MemberVo;
import fong.service.MemberService;
import fong.service.impl.MemberServiceImpl;

@WebServlet("/CancelInterviewServlet")
public class CancelInterviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplyRecordService applyRecordService = new ApplyRecordServiceImpl();
	private MemberService memberService = new MemberServiceImpl();

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String comName = req.getParameter("comName").trim() ;
		String jobName = req.getParameter("jobName").trim() ;
		Integer memId = (Integer) req.getSession().getAttribute("memberLogin");

		MemberVo member = memberService.getMemberById(memId);
		applyRecordService.cancelInterview(comName, jobName, memId, member.getMemName());
	}
}