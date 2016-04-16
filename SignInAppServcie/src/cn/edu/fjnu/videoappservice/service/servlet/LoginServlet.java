package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.service.bean.UserService;
import cn.edu.fjnu.videoappservice.util.CommonUtils;

public class LoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String rqUserName = request.getParameter("user_name");
		String rqPassword = request.getParameter("password");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//此处写入json数据
		JSONObject resultObject = new JSONObject();
		JSONObject headObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		//用于判断的对象
		User user = new User();
		user.setUser_name(rqUserName);
		user.setPassword(rqPassword);
		user.setPhone_number(rqUserName);
		UserService userService = new UserService();
		boolean isRight = userService.isExist(user);
		try {
			if(isRight){
				contentObject.put("user", CommonUtils.objectToJson(userService.getObjectByUserName(rqUserName)));
				headObject.put("succ", contentObject);
			}else{
				headObject.put("error", "学号/工号或密码错误");
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
