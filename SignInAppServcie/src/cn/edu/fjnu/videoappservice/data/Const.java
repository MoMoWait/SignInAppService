/**
 * 
 */
package cn.edu.fjnu.videoappservice.data;

/**
 * @author 常量数据
 *
 */
public class Const {
	
	public static final String SERVER_BASIC = "http://120.24.210.186:8080/SignInAppServcie/";
	//一天的秒数
	public static final int ONEDAY_SECOND = 24*60*60;
	public interface FileType{
		//图片
		public int PHOTO = 1;
		//视频
		public int VIDEO = 2;
	}
	
	/**
	 * 用户类型
	 * @author GaoFei
	 *
	 */
	public interface UserType{
		//普通用户
		public int NORMAL = 1;
		//管理员
		public int MANAGER = 2;
	}
	
    /**
     * 推送消息
     */
    public interface PushMessage{
        /**图片上传成功*/
        String PHOTO_UPLOAD = "photo_upload";
        /**用户请求上线*/
        String USER_RQ_ONLINE = "user_request_online";
        /**用户请求离线*/
        String USER_RQ_OFFLINE = "user_request_offline";
    }
}
