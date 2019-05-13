/**
 * 
 */
package example.admin.course;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import af.web.restful.AfRestfulApi;
import example.admin.MyC3P0Factory;
import example.admin.login.LoginCheck;

/**
 * @author 蜗牛
 *
 * @description (一句话描述该类)
 *
 * @date 2019年5月2日
 */
public class CourseEditApi extends AfRestfulApi
{
	static Logger logger = Logger.getLogger(CourseEditApi.class);

	@Override
	public Object execute(JSONObject jreq) throws Exception
	{
		// 权限检查
		new LoginCheck(httpReq).check();

		if (!jreq.has("id"))
		{
			throw new Exception("id is not null");
		}
		int id = jreq.getInt("id");
		String title = jreq.getString("title");

		String sql = String.format("update course set title='%s' where id=%d", title, id);
		MyC3P0Factory.execute(sql);
		logger.debug("修改课程：  " + id);
		return null;
	}

}
