package fong.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import fong.service.MemberService;
import fong.service.impl.MemberServiceImpl;
import fong.vo.MemberVo;

@WebServlet("/MemberShowInfoServlet")
public class MemberShowInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer memId = (Integer) req.getSession().getAttribute("memberLogin");
		MemberVo member = memberService.getMemberById(memId);

		// 轉為json寫回客戶端
		String jsonStr = new JSONObject(member).toString();
		resp.getWriter().write(jsonStr);

	}
}