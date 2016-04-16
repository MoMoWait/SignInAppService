package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import cn.edu.fjnu.videoappservice.domain.SignRecord;
import cn.edu.fjnu.videoappservice.domain.SignUserRecord;
import cn.edu.fjnu.videoappservice.service.bean.SignRecordService;
import cn.edu.fjnu.videoappservice.util.CommonUtils;
/**
 * 获取签到记录的用户
 * @author GaoFei
 *
 */
public class GetSignRecordServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}


	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		int rqSid = -1;
		if(null != request.getParameter("sid"))
			rqSid = Integer.parseInt(request.getParameter("sid"));
		int rqUid= -1;
		if(null != request.getParameter("uid"))
			rqUid = Integer.parseInt(request.getParameter("uid"));
		System.out.println("rqsid = "  + rqSid);
		System.out.println("rqUid = " + rqUid);
		//此处写入json数据
		JSONObject resultObject = new JSONObject();
		JSONObject headObject = new JSONObject();
		JSONObject contentObject = new JSONObject();
		//用于判断的对象
		SignRecordService signRecordService = new SignRecordService();
		try{
			List<SignRecord> signInfos = new ArrayList<>();
			List<SignUserRecord> signUserRecords = new ArrayList<>();
			if(rqSid != - 1){
				signInfos  = signRecordService.getAllByISid(rqSid);
				if(signInfos == null || signInfos.size() == 0)
					headObject.put("error", "暂无签到记录");
				else{
					contentObject.put("signRecords", CommonUtils.listToJsonArray(signInfos));
					headObject.put("succ", contentObject);
				}
			}
			else{
				signUserRecords = signRecordService.getAllByUid(rqUid);
				if(signUserRecords == null || signUserRecords.size() == 0)
					headObject.put("error", "暂无签到记录");
				else{
					contentObject.put("signUserRecords", CommonUtils.listToJsonArray(signUserRecords));
					headObject.put("succ", contentObject);
				}
			}
			resultObject.put("result", headObject);
		}catch (Exception e){
			e.printStackTrace();
		}
		out.print(resultObject.toString());
		out.flush();
		out.close();
	}

}
