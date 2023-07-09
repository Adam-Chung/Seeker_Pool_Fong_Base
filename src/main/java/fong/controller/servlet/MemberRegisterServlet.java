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
import org.json.JSONObject;

import fong.service.MemberService;
import fong.service.impl.MemberServiceImpl;
import fong.vo.MemberVo;
import fong.vo.ResultInfo;

@WebServlet("/MemberRegisterServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 1024 * 1024)
public class MemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 獲取數據
		Map<String, String[]> map = req.getParameterMap();

		// 封裝對象，導入工具類BeanUtils
		MemberVo member = new MemberVo();
		try {
			BeanUtils.populate(member, map); // 表單name要跟成員名稱一樣
		} catch (Exception e) {
			e.printStackTrace();
		}
		member.setMemAddress(req.getParameter("city")+req.getParameter("district"));

		
//		抓圖片並儲存於真實路徑
		Part filepart = req.getPart("memPic"); 
		String filename = filepart.getSubmittedFileName();
	
		String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄; 
		String realPath = getServletContext().getRealPath(saveDirectory);
		File fsaveDirectory = new File(realPath);
		if (!fsaveDirectory.exists()) {
			 fsaveDirectory.mkdirs(); 
			}
			
		File f = new File(fsaveDirectory, filename);  //真實路徑(阿飄路徑)(連動端，實際運作取用) 
		filepart.write(f.toString()); // part類別的方法write() 利用File物件, 寫入指定目地目錄,上傳成功
		
		//將路徑存入物件中
		member.setMemPic(req.getContextPath()+saveDirectory+"/"+filename);

		Boolean flag = memberService.registerMember(member);
		ResultInfo info = new ResultInfo(); // 用於封裝資訊回傳前端
		// 響應結果
		if (flag) {
			// 註冊成功
			info.setFlag(true);
			//註冊成功，也相當於登入
			MemberVo memberLogin = memberService.loginMember(member);	
			Integer memId = memberLogin.getMemId();
			
			req.getSession().setAttribute("memberLogin", memId);
			
		} else {
			// 註冊失敗
			info.setFlag(false);
			info.setMsg("註冊失敗，帳號重複");
		}
		
		//轉為json寫回客戶端
    	String jsonStr =  new JSONObject(info).toString();  
    	resp.getWriter().write(jsonStr);
	}
}