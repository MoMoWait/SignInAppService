package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.edu.fjnu.videoappservice.data.Const;
import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.service.bean.UserService;
/**
 * 用户注册服务
 * @author GaoFei
 *
 */
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String rqUserName = request.getParameter("user_name");
		String rqPassword = request.getParameter("password");
		String rqMail = request.getParameter("mail");
		String rqNickName = request.getParameter("nick_name");
		String rqPhoneNumber = request.getParameter("phone_number");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//此处写入json数据
		JSONObject resultObject = new JSONObject();
		JSONObject headObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		UserService userService = new UserService();
		//用于判断的对象
		User registerUser = userService.getObjectByUserName(rqUserName);
		//设置邮箱号唯一
		User mailUser = userService.getObjectByMail(rqMail);
		//判断手机号唯一
		User phoneUser = userService.getObjectByPhoneNumber(rqPhoneNumber);
		try {
			if(registerUser == null && mailUser == null){
				User user = new User();
				user.setMail(rqMail);
				user.setNick_name(rqNickName);
				user.setPassword(rqPassword);
				user.setType(Const.UserType.NORMAL);
				user.setUser_name(rqUserName);
				user.setPhone_number(rqPhoneNumber);
				new UserService().save(user);
				headObject.put("succ", contentObject);
			}else if(registerUser != null){
				headObject.put("error", "学号" + rqUserName + "已经被人注册了");
			}else if(mailUser != null){
				headObject.put("error", "邮箱号" + rqMail +"已经被人注册了");
			}else if(phoneUser != null){
				headObject.put("error", "手机号" + rqPhoneNumber + "已经被人注册过了");
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
