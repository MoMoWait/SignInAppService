package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.edu.fjnu.videoappservice.data.Const;
import cn.edu.fjnu.videoappservice.domain.SignRecord;
import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.service.bean.SignRecordService;
import cn.edu.fjnu.videoappservice.service.bean.UserService;

public class SignInRecordServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String rqPhoneNumber = request.getParameter("phone_number");
		int rqSignInoId = Integer.parseInt(request.getParameter("signinfo_id"));
		String rqMail = request.getParameter("mail");
		String rqNickName = request.getParameter("nick_name");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//此处写入json数据
		JSONObject resultObject = new JSONObject();
		JSONObject headObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		UserService userService = new UserService();
		SignRecordService signRecordService = new SignRecordService();
		//通过手机号获取用户信息
		User signUser = userService.getObjectByPhoneNumber(rqPhoneNumber);
		SignRecord signRecord = new SignRecord();
		signRecord.setUid(signUser.getId());
		signRecord.setSid(rqSignInoId);
		signRecord.setSignTime((int)(System.currentTimeMillis() / 1000));
		//设置邮箱号唯一
		boolean isSign = signRecordService.isExist(signRecord);
		try {
			if(isSign){
				headObject.put("error", "该学生已签到");
			}else{
				signRecordService.save(signRecord);
				headObject.put("succ", contentObject);
			}
			resultObject.put("result", headObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(resultObject.toString());
		out.flush();
		out.close();
	}

}
