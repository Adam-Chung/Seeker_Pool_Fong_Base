package fong.controller.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import fong.service.MemberService;
import fong.service.impl.MemberServiceImpl;
import fong.vo.MemberVo;

@WebServlet("/MemberUpdateInfoServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 1024 * 1024)
public class MemberUpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		抓圖片並儲存於真實路徑
		Part filepart = null;
		String filename = null;
		Integer memId = (Integer) req.getSession().getAttribute("memberLogin");
		MemberVo orgMember = memberService.getMemberById(memId);

		try {
			filepart = req.getPart("memPic");
		} catch (Exception e) {
		} 


		if (filepart != null) { //有照片更新
			filename = filepart.getSubmittedFileName();
			
			String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄; 
			String realPath = getServletContext().getRealPath(saveDirectory);
			File fsaveDirectory = new File(realPath);
			if (!fsaveDirectory.exists()) {
				fsaveDirectory.mkdirs();
			} 

			File f = new File(fsaveDirectory, filename); // 建立一個檔案，用真實路徑
			filepart.write(f.toString()); // part類別的方法write() 利用File物件, 寫入指定目地目錄

			// 將路徑存入物件中
			orgMember.setMemPic(req.getContextPath() + saveDirectory + "/" + filename);
			memberService.updateMember(orgMember);

		} else { //沒照片更新
			MemberVo member = new MemberVo();
			Map<String, String[]> map = req.getParameterMap();
			try {
				BeanUtils.populate(member, map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			member.setMemPic(orgMember.getMemPic());
			member.setMemId(orgMember.getMemId());
			member.setMemStatus(orgMember.getMemStatus());
			memberService.updateMember(member);
		}

	}
}
