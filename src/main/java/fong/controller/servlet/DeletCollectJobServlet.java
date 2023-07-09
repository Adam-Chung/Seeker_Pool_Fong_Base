package fong.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fong.service.JobService;
import fong.service.impl.JobServiceImpl;

@WebServlet("/DeletCollectJobServlet")
public class DeletCollectJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JobService jobServiceImpl = new JobServiceImpl();

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer jobNo = Integer.valueOf(req.getParameter("jobNo").trim()) ;
		Integer memId = (Integer) req.getSession().getAttribute("memberLogin");
		
		
		jobServiceImpl.deletColJobByMemIdAndArNo(jobNo, memId);
	}
}
