package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import cn.edu.fjnu.videoappservice.domain.SignInfo;
import cn.edu.fjnu.videoappservice.service.bean.SignInfoService;
import cn.edu.fjnu.videoappservice.service.bean.UserService;

/**
 * 新建签到服务
 * @author Administrator
 *
 */
public class CreateSignInfoServlet extends HttpServlet {

	
	private String rqUserName;



	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String rqCourse = request.getParameter("course");
		int rqPersons = Integer.parseInt(request.getParameter("persons"));
		int rqStartTime = Integer.parseInt(request.getParameter("startTime")) ;
		int rqEndTime = Integer.parseInt(request.getParameter("endTime"));
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//此处写入json数据
		JSONObject resultObject = new JSONObject();
		JSONObject headObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		SignInfo signInfo = new SignInfo();
		signInfo.setCourse(rqCourse);
		signInfo.setPersons(rqPersons);
		signInfo.setStartTime(rqStartTime);
		signInfo.setEndTime(rqEndTime);
		try {
			SignInfoService signInfoService = new SignInfoService();
			signInfoService.save(signInfo);
			headObject.put("succ", contentObject);
			resultObject.put("result", headObject);
		} catch (Exception e) {
			e.printStackTrace();
			try{
				headObject.put("error", "服务器内部错误");
				resultObject.put("result", headObject);
			}catch (Exception e2){
				
			}
			
		}
		out.print(resultObject.toString());
		out.flush();
		out.close();
	}

}
