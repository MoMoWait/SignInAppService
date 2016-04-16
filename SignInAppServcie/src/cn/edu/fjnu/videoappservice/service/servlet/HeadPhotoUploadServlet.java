package cn.edu.fjnu.videoappservice.service.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;
import cn.edu.fjnu.videoappservice.data.Const;
import cn.edu.fjnu.videoappservice.domain.User;
import cn.edu.fjnu.videoappservice.service.bean.UserService;
import cn.edu.fjnu.videoappservice.util.CommonUtils;

public class HeadPhotoUploadServlet extends HttpServlet {

	private int id;
	private String file_name;
	private String url;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			String photoPath = request.getSession().getServletContext().getRealPath("/HeadPhotos");
			File photoDir = new File(photoPath);
			if (!photoDir.exists())
				photoDir.mkdirs();
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			try {
				List<FileItem> items = upload.parseRequest(request);
				for (FileItem item : items) {
					if (item.isFormField()) {
						String filedName = item.getFieldName();
						if(filedName.equals("id")){
							id=Integer.parseInt(item.getString());
						}
							
					} else {
						file_name = UUID.randomUUID().toString() + item.getName().substring(
								item.getName().lastIndexOf("."));
						url = Const.SERVER_BASIC + "HeadPhotos/" + file_name;
						item.write(new File(photoDir, file_name));
					}
				}
				//存储至数据库中
				UserService userService = new UserService();
				userService.changePersonData(id, "head_photo", url);
				JSONObject resultObject = new JSONObject();
				JSONObject succObject = new JSONObject();
				JSONObject contentObject = new JSONObject();
				User user = userService.getObjectById(id);
				contentObject.put("user", CommonUtils.objectToJson(user));
				succObject.put("succ", contentObject);
				resultObject.put("result", succObject);
				PrintWriter writer = response.getWriter();
				writer.write(resultObject.toString());
				writer.flush();
				writer.close();
				//发送推送消息
				
			} catch (Exception e) {
				e.printStackTrace();
				PrintWriter writer = response.getWriter();
				writer.write("{'result':{'error':'服务器内部错误'}}");
				writer.flush();
				writer.close();
			}

		} else {
			doGet(request, response);
		}
	
	}

}
